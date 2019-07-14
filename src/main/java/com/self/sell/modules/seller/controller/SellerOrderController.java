package com.self.sell.modules.seller.controller;

import cn.hutool.core.map.MapUtil;
import com.github.pagehelper.PageInfo;
import com.self.sell.common.pojo.bo.PageParam;
import com.self.sell.modules.order.pojo.dto.OrderDto;
import com.self.sell.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("list")
    public ModelAndView list(HttpServletRequest request) {
        PageParam pageParam = PageParam.builder(request).build();


        Map<String, Object> map = MapUtil.newHashMap();
        PageInfo<OrderDto> orderPage = orderService.queryOrderList(pageParam);
        map.put("orderPage", orderPage);


        long count = orderPage.getList().stream().map(e -> {
            log.info("【Id 】  id = {}", e.getOrderId());
            return e;
        }).count();
        log.info("【 查询 】 count = {}", count);
        return new ModelAndView("seller/order/list", map);
    }

    @GetMapping("list1")
    @ResponseBody
    public Map<String, Object> list1(HttpServletRequest request) {
        PageParam pageParam = PageParam.builder(request).build();
        Map<String, Object> map = MapUtil.newHashMap();
        PageInfo<OrderDto> orderPage = orderService.queryOrderList(pageParam);
        map.put("orderPage", orderPage);
        long count = orderPage.getList().stream().map(e -> {
            log.info("【Id 】  id = {}", e.getOrderId());
            return e;
        }).count();
        log.info("【 查询 】 count = {}", count);
        return map;
    }

}
