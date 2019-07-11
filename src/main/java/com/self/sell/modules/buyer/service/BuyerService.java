package com.self.sell.modules.buyer.service;

import com.self.sell.modules.order.pojo.dto.OrderDto;

public interface BuyerService {

    OrderDto queryOrderOne(long orderId, String openId);


    void cancelOrder(long orderId, String openId);

}
