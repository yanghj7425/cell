package com.self.sell.modules.buyer.controller;

import cn.hutool.core.map.MapUtil;
import com.github.pagehelper.PageInfo;
import com.self.sell.common.enums.ResultEnum;
import com.self.sell.common.exception.SellException;
import com.self.sell.common.pojo.bo.PageParam;
import com.self.sell.common.pojo.vo.ResultVo;
import com.self.sell.common.util.ResultVoUtils;
import com.self.sell.modules.buyer.service.BuyerService;
import com.self.sell.modules.order.convert.OrderForm2OrderDtoConvert;
import com.self.sell.modules.order.form.OrderFrom;
import com.self.sell.modules.order.pojo.dto.OrderDto;
import com.self.sell.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private BuyerService buyerService;

    /**
     * @param orderForm     {
     *                      name:Don.Sin
     *                      phone:10293984954
     *                      address:五角大楼
     *                      openId:jhsdkshasdhfajkhjfsdfjksdfsd
     *                      items:[{productId:2,productQuantity:3},{productId:4,productQuantity:3}]
     *                      }
     * @param bindingResult 表达验证错误
     * @return orderId
     */
    // 创建订单
    @PostMapping("create")
    public ResultVo<Map<String, String>> create(@Validated OrderFrom orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【 创建订单 】 参数不正确 orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDtoConvert.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【 创建订单 】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY_ERROR);
        }

        OrderDto retOrderDto = orderService.create(orderDto);
        Map<String, String> result = MapUtil.newHashMap();
        result.put("orderId", retOrderDto.getOrderId().toString());
        return ResultVoUtils.success(result);
    }

    // 查询订单列表
    @GetMapping("list")
    public ResultVo<List<OrderDto>> list(String openId, HttpServletRequest request) {
        if (StringUtils.isEmpty(openId)) {
            log.error("【 查询订单列表 】 openId 不能为空, openId = {}", openId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageParam pageParam = PageParam.builder(request).build();
        PageInfo<OrderDto> orderDtoPageInfo = orderService.queryOrderList(openId, pageParam);
        return ResultVoUtils.success(orderDtoPageInfo.getList());
    }


    // 查询订单详情
    @GetMapping("detail")
    public ResultVo<OrderDto> detail(long orderId, String openId) {
        OrderDto orderDto = buyerService.queryOrderOne(orderId, openId);
        return ResultVoUtils.success(orderDto);
    }

    // 取消订单
    @PostMapping("cancel")
    public ResultVo cancel(long orderId, String openId) {
        buyerService.cancelOrder(orderId, openId);
        return ResultVoUtils.success();
    }

}
