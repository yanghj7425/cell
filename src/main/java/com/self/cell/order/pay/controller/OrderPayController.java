package com.self.cell.order.pay.controller;


import cn.hutool.core.map.MapUtil;
import com.self.cell.common.enums.ResultEnum;
import com.self.cell.common.exception.CellException;
import com.self.cell.order.pay.service.OrderPayService;
import com.self.cell.order.pojo.dto.OrderDto;
import com.self.cell.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("order/pay")
public class OrderPayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderPayService orderPayService;

    /**
     * @param orderId   订单ID
     * @param returnUrl 返回url
     * @return
     */
    @GetMapping("create")
    public ModelAndView create(String orderId, String returnUrl) {
        // 查询订单

        OrderDto orderDto = orderService.queryOne(new Long(orderId));

        orderDto = Optional.of(orderDto).orElseThrow(() -> new CellException(ResultEnum.ORDER_NOT_EXIST));

        // 发起支付
        orderPayService.create(orderDto);


        Map<String, String> result = MapUtil.newHashMap();
        result.put("orderId", orderId);

        return new ModelAndView("order/pay/create", result);
    }

}
