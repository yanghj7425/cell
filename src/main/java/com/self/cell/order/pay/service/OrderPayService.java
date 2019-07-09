package com.self.cell.order.pay.service;

import com.self.cell.order.pojo.dto.OrderDto;

public interface OrderPayService {

    void create(OrderDto orderDto);


    void asyncNotify(String notifyData);


    void refund(OrderDto orderDto);
}
