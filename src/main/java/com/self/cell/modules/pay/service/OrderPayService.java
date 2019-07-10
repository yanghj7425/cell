package com.self.cell.modules.pay.service;

import com.self.cell.modules.order.pojo.dto.OrderDto;

public interface OrderPayService {

    void create(OrderDto orderDto);


    void asyncNotify(String notifyData);


    void refund(OrderDto orderDto);
}
