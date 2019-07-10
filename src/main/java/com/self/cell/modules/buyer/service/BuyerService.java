package com.self.cell.modules.buyer.service;

import com.self.cell.modules.order.pojo.dto.OrderDto;

public interface BuyerService {

    OrderDto queryOrderOne(long orderId, String openId);


    void cancelOrder(long orderId, String openId);

}
