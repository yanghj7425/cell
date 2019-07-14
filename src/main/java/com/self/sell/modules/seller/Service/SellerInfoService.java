package com.self.sell.modules.seller.Service;

import com.self.sell.common.service.BaseService;
import com.self.sell.modules.seller.entity.SellerInfo;

public interface SellerInfoService  extends BaseService<SellerInfo>{

    SellerInfo queryByOpenId(String openId);

}
