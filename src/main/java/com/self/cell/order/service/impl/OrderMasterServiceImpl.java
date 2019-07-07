package com.self.cell.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.self.cell.common.enums.ResultEnum;
import com.self.cell.common.exception.CellException;
import com.self.cell.common.pojo.bo.PageParam;
import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.common.util.KeyUtils;
import com.self.cell.order.convert.OrderMaster2OrderDtoConvert;
import com.self.cell.order.entity.OrderDetail;
import com.self.cell.order.entity.OrderMaster;
import com.self.cell.order.enums.OrderStatusEnum;
import com.self.cell.order.enums.PayStatusEnum;
import com.self.cell.order.pojo.dto.OrderDto;
import com.self.cell.order.service.OrderDetailService;
import com.self.cell.order.service.OrderMasterService;
import com.self.cell.product.entity.ProductInfo;
import com.self.cell.product.pojo.dto.CartDto;
import com.self.cell.product.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderMasterServiceImpl extends AbstractBaseService<OrderMaster, Mapper<OrderMaster>> implements OrderMasterService {


    @Autowired
    private ProductInfoService productInfoService;


    @Autowired
    private OrderDetailService orderDetailService;


    @Override
    public OrderDto create(OrderDto orderDto) {
        // 查询商品 （数量，价格）

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        long orderId = KeyUtils.genUniqueKey();


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

        orderMaster.setOrderId(orderId);
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


        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
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
    public PageInfo<OrderDto> queryOrderList(PageParam pageParam, String openId) {
        PageInfo<OrderDto> result = new PageInfo<>();
        PageInfo<OrderMaster> pageInfo = doQueryForPage(pageParam, map -> queryListByProperty(OrderMaster.class, "buyerOpenid", Arrays.asList(openId)));

        List<OrderDto> list = OrderMaster2OrderDtoConvert.convert(pageInfo.getList());
        pageInfo.setList(null);

        BeanUtils.copyProperties(pageInfo, result);
        result.setList(list);

        return result;
    }


}
