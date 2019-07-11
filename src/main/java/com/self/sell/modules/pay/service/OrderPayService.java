package com.self.sell.modules.pay.service;

import com.self.sell.modules.order.pojo.dto.OrderDto;

public interface OrderPayService {

    void create(OrderDto orderDto);


    void asyncNotify(String notifyData);


    void refund(OrderDto orderDto);
}
