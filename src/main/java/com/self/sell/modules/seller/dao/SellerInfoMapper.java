package com.self.sell.modules.seller.dao;

import com.self.sell.modules.seller.entity.SellerInfo;
import tk.mybatis.mapper.common.Mapper;

public interface SellerInfoMapper extends Mapper<SellerInfo> {

    SellerInfo queryByOpenId(String openId);
}