package com.self.sell.modules.seller.Service.impl;

import com.self.sell.common.util.KeyUtils;
import com.self.sell.modules.seller.Service.SellerInfoService;
import com.self.sell.modules.seller.entity.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Test
    public void insertOne() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtils.genUniqueKey());
        sellerInfo.setOpenid("8888");
        sellerInfo.setSellerName("admin");
        sellerInfo.setSellerPasswd("admin");
        sellerInfoService.insertOne(sellerInfo);
    }


    @Test
    public void queryByOpenId() {
        String openId = "8888";
        SellerInfo sellerInfo = sellerInfoService.queryByOpenId(openId);
        Assert.isTrue(sellerInfo.getOpenid().equals(openId), "卖家不存在");
    }

}