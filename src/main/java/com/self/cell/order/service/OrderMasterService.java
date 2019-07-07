package com.self.cell.order.service;

import com.github.pagehelper.PageInfo;
import com.self.cell.common.pojo.bo.PageParam;
import com.self.cell.common.service.BaseService;
import com.self.cell.order.entity.OrderMaster;

public interface OrderMasterService extends BaseService<OrderMaster> {

    PageInfo<OrderMaster> queryOrderByBuyerOpenId(PageParam pageParam, String openId);

}
