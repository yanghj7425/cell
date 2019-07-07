package com.self.cell.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.self.cell.common.pojo.bo.PageParam;
import com.self.cell.common.service.impl.AbstractBaseService;
import com.self.cell.order.entity.OrderMaster;
import com.self.cell.order.service.OrderMasterService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.Arrays;

@Service
public class OrderMasterServiceImpl extends AbstractBaseService<OrderMaster, Mapper<OrderMaster>> implements OrderMasterService {

    @Override
    public PageInfo<OrderMaster> queryOrderByBuyerOpenId(PageParam pageParam, String openId) {
        return doQueryForPage(pageParam, map -> queryListByProperty(OrderMaster.class, "buyerOpenid", Arrays.asList(openId)));
    }


}
