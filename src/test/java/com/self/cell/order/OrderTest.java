package com.self.cell.order;

import com.self.cell.config.MybatisConfig;
import com.self.cell.order.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({MybatisConfig.class})
public class OrderTest {

    @Resource
    private OrderDetail orderDetail;

    @Test
    public void insert() {


    }

}
