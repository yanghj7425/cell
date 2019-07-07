package com.self.cell.product.service.impl;

import com.self.cell.product.entity.ProductInfo;
import com.self.cell.product.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoServiceImplTest {


    @Autowired
    private ProductInfoService productInfoService;


    @Test
    public void queryAllOnSaleProducts() {
        List<ProductInfo> list = productInfoService.queryAllOnSaleProducts();
        Assert.assertNotEquals(0, list.size());
        list.forEach(e -> log.debug(e.toString()));

    }

    @Test
    public void insertList() {
        List<ProductInfo> list = new ArrayList<>();
        int start = 0;

        for (int i = start; i < start + 100; i++) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId((long) i);
            productInfo.setProductName("商品名称" + i + "号");
            productInfo.setProductPrice(new BigDecimal(3.4));
            productInfo.setProductStatus((byte) (i & 1));
            productInfo.setCategoryType(i % 5);
            productInfo.setProductDescription("鱿鱼三明治好不好吃");
            productInfo.setProductStock(i);
            productInfo.setProductIcon(i + ".png");
            list.add(productInfo);

        }
        productInfoService.insertList(list);

    }

}