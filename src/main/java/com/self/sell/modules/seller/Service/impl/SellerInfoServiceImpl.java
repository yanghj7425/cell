package com.self.sell.modules.seller.Service.impl;

import com.self.sell.common.enums.ResultEnum;
import com.self.sell.common.exception.SellException;
import com.self.sell.common.service.impl.AbstractBaseService;
import com.self.sell.modules.seller.Service.SellerInfoService;
import com.self.sell.modules.seller.dao.SellerInfoMapper;
import com.self.sell.modules.seller.entity.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SellerInfoServiceImpl extends AbstractBaseService<SellerInfo, SellerInfoMapper> implements SellerInfoService {


    @Resource
    private SellerInfoMapper sellerInfoMapper;


    @Override
    public SellerInfo queryByOpenId(String openId) {
        SellerInfo sellerInfo = sellerInfoMapper.queryByOpenId(openId);
        if (sellerInfo == null) {
            log.error("【 查询卖家用户】 根据 openId = {}, 查不到用户 ");
            throw new SellException(ResultEnum.SELLER_INFO_NOT_EXIST);
        }
        return sellerInfo;
    }
}
