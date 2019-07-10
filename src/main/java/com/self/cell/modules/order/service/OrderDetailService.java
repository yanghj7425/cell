package com.self.cell.modules.order.service;

import com.self.cell.common.service.BaseService;
import com.self.cell.modules.order.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetail> {


    List<OrderDetail> queryOrderByBuyerOrderId(long orderId);
}
