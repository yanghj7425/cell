package com.self.sell.modules.order.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.self.sell.common.enums.ResultEnum;
import com.self.sell.common.exception.SellException;
import com.self.sell.modules.order.entity.OrderDetail;
import com.self.sell.modules.order.form.OrderFrom;
import com.self.sell.modules.order.pojo.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderForm2OrderDtoConvert {

    public static OrderDto convert(OrderFrom orderFrom) {
        OrderDto orderDto = new OrderDto();
        Gson gson = new Gson();

        orderDto.setBuyerName(orderFrom.getName());
        orderDto.setBuyerPhone(orderFrom.getPhone());
        orderDto.setBuyerAddress(orderFrom.getAddress());
        orderDto.setBuyerOpenid(orderFrom.getOpenId());

        List<OrderDetail> orderDetailList;

        try {
            orderDetailList = gson.fromJson(orderFrom.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());

        } catch (Exception e) {
            log.error("【 对象转换 】 错误, string = {}", orderFrom.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;

    }


}
