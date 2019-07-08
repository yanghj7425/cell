package com.self.cell.order.buyer.service;

import com.self.cell.order.pojo.dto.OrderDto;

public interface BuyerService {

    OrderDto queryOrderOne(long orderId, String openId);


    void cancelOrder(long orderId, String openId);

}
