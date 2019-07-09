package com.self.cell.order.pay.service.impl;

import com.self.cell.order.pay.service.OrderPayService;
import com.self.cell.order.pojo.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderPayServiceImpl implements OrderPayService {


    @Override
    public void create(OrderDto orderDto) {
        // TODO: 调用支付平台支付
    }
}
