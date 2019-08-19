package com.self.sell.aop.db.datasource;

import com.self.sell.modules.product.entity.ProductInfo;
import com.self.sell.modules.product.service.ProductInfoService;
import com.self.sell.modules.user.entity.SysUser;
import com.self.sell.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MulDbTest {


    @Autowired
    private UserService userService;


    @Autowired
    private ProductInfoService productInfoService;


    @Test
    public void test() {
        String aTestString = userService.getATestString();
        log.info(aTestString);
        List<ProductInfo> productInfos = productInfoService.queryAllOnSaleProducts();
        productInfos.forEach(System.out::println);


        List<SysUser> sysUsers = userService.queryAll();

        sysUsers.stream().forEach(m -> log.info(m.toString()));
    }


}
