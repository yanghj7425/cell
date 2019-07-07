package com.self.cell.order.service.impl;

import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.order.entity.OrderDetail;
import com.self.cell.order.service.OrderDetailService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class OrderDetailServiceImpl extends AbstractBaseService<OrderDetail, Mapper<OrderDetail>> implements OrderDetailService {
}
