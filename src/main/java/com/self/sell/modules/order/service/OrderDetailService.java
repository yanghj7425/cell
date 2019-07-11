package com.self.sell.modules.order.service;

import com.self.sell.common.service.BaseService;
import com.self.sell.modules.order.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetail> {


    List<OrderDetail> queryOrderByBuyerOrderId(long orderId);
}
