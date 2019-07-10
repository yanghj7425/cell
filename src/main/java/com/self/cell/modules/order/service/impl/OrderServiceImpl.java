package com.self.cell.modules.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.self.cell.common.enums.ResultEnum;
import com.self.cell.common.exception.CellException;
import com.self.cell.common.pojo.bo.PageParam;
import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.common.util.KeyUtils;
import com.self.cell.modules.order.service.OrderDetailService;
import com.self.cell.modules.order.convert.OrderMaster2OrderDtoConvert;
import com.self.cell.modules.order.entity.OrderDetail;
import com.self.cell.modules.order.entity.OrderMaster;
import com.self.cell.modules.order.enums.OrderStatusEnum;
import com.self.cell.modules.order.enums.PayStatusEnum;
import com.self.cell.modules.order.pojo.dto.OrderDto;
import com.self.cell.modules.order.service.OrderService;
import com.self.cell.modules.product.entity.ProductInfo;
import com.self.cell.modules.product.pojo.dto.CartDto;
import com.self.cell.modules.product.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl extends AbstractBaseService<OrderMaster, Mapper<OrderMaster>> implements OrderService {


    @Autowired
    private ProductInfoService productInfoService;


    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public OrderDto create(OrderDto orderDto) {
        // 查询商品 （数量，价格）

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        long orderId = KeyUtils.genUniqueKey();
        orderDto.setOrderId(orderId);

        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.queryOneById(orderDetail.getProductId());
            if (productInfo == null) {
                throw new CellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // orderDetail 入库
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailService.insertOne(orderDetail);
        }

        // 写入 orderMaster
        OrderMaster orderMaster = new OrderMaster();

        BeanUtils.copyProperties(orderDto, orderMaster);

        orderMaster.setBuyerAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode().byteValue());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode().byteValue());

        insertOne(orderMaster);

        // 减库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        // 判断状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode().byteValue())) {
            log.error("【 支付订单 】 订单状态不正确, orderId = {}, orderStatus = {}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new CellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode().byteValue())) {
            log.error("【 支付订单 】 订单状态不正确, orderId = {}, payStatus = {}", orderDto.getOrderId(), orderDto.getPayStatus());
            throw new CellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode().byteValue());
        BeanUtils.copyProperties(orderDto, orderMaster);

        Integer integer = updateSelectiveById(orderMaster);
        if (integer == null || integer == 0) {
            log.error("【支付订单】 支付状态修改失败, orderId = {}, payStatus = {}", orderDto.getOrderId(), orderDto.getPayStatus());
            throw new CellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        return orderDto;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        // 判断状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode().byteValue())) {
            log.error("【 取消订单】 订单状态不正确, orderId = {}, orderStatus = {}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new CellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode().byteValue());

        BeanUtils.copyProperties(orderDto, orderMaster);

        // 修改状态
        Integer integer = updateSelectiveById(orderMaster);
        if (integer == null || integer == 0) {
            log.error("【取消订单】 更新失败, orderId = {}, orderStatus = {}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new CellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        // 返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】 更新失败, orderId = {}, orderStatus = {}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new CellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productInfoService.increaseStock(cartDtoList);

        // 退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode().byteValue())) {
            // TODO: 退款

        }

        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode().byteValue())) {
            log.error("【 完结订单 】 订单状态不正确, orderId = {}, orderStatus = {}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new CellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改状态
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode().byteValue());
        BeanUtils.copyProperties(orderDto, orderMaster);


        Integer integer = updateSelectiveById(orderMaster);
        if (integer == null || integer == 0) {
            log.error("【完结订单】 更新失败, orderId = {}, orderStatus = {}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new CellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }

    @Override
    public OrderDto queryOne(long orderId) {
        OrderMaster orderMaster = queryOneById(orderId);
        if (orderMaster == null) {
            throw new CellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> list = orderDetailService.queryOrderByBuyerOrderId(orderId);

        if (list == null) {
            throw new CellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);

        orderDto.setOrderDetailList(list);

        return orderDto;
    }

    @Override
    public PageInfo<OrderDto> queryOrderList(String openId, PageParam pageParam) {
        PageInfo<OrderDto> result = new PageInfo<>();
        PageInfo<OrderMaster> pageInfo = doQueryForPage(pageParam, map -> queryListByProperty(OrderMaster.class, "buyerOpenid", Arrays.asList(openId)));

        List<OrderDto> list = OrderMaster2OrderDtoConvert.convert(pageInfo.getList());
        pageInfo.setList(null);

        BeanUtils.copyProperties(pageInfo, result);
        result.setList(list);

        return result;
    }


}
