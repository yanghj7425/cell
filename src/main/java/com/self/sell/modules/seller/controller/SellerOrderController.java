package com.self.sell.modules.seller.controller;

import cn.hutool.core.map.MapUtil;
import com.github.pagehelper.PageInfo;
import com.self.sell.common.enums.ResultEnum;
import com.self.sell.common.pojo.bo.PageParam;
import com.self.sell.modules.order.pojo.dto.OrderDto;
import com.self.sell.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.LongValue;
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
        return new ModelAndView("seller/order/list", map);
    }


    @GetMapping("cancel")
    public ModelAndView cancel(long orderId) {
        Map<String, Object> map = MapUtil.newHashMap();
        map.put("url", "/sell/seller/order/list");
        try {
            OrderDto orderDto = orderService.queryOne(orderId);
            orderService.cancel(orderDto);
            map.put("msg", "订单取消成功");
            return new ModelAndView("common/success", map);
        } catch (Exception e) {
            log.error("【订单取消】 查询不到订单");
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMsg());
            return new ModelAndView("common/error", map);
        }
    }


    @GetMapping("detail")
    public ModelAndView detail(long orderId) {
        Map<String, Object> map = MapUtil.newHashMap();
        try {
            OrderDto orderDto = orderService.queryOne(orderId);
            map.put("order", orderDto);
            return new ModelAndView("seller/order/detail", map);
        } catch (Exception e) {
            log.error("【订单详情】 查询不到订单");
            map.put("url", "/sell/seller/order/list");
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMsg());
            return new ModelAndView("common/error", map);
        }
    }


    @GetMapping("finish")
    public ModelAndView finish(long orderId) {
        Map<String, Object> map = MapUtil.newHashMap();
        map.put("url", "/sell/seller/order/list");
        try {
            OrderDto orderDto = orderService.queryOne(orderId);
            orderService.finish(orderDto);
            map.put("msg", "订单完结成功");
            return new ModelAndView("common/success", map);
        } catch (Exception e) {
            log.error(e.getMessage());

            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }


    }


}
