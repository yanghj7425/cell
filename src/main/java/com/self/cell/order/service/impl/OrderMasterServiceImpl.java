package com.self.cell.order.service.impl;

import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.order.entity.OrderMaster;
import com.self.cell.order.service.OrderMasterService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class OrderMasterServiceImpl extends AbstractBaseService<OrderMaster, Mapper<OrderMaster>> implements OrderMasterService {
}
