package com.self.sell.order.dao;

import com.self.sell.config.MybatisConfig;
import com.self.sell.modules.order.dao.OrderMasterMapper;
import com.self.sell.modules.order.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MybatisConfig.class)
public class OrderMasterMapperTest {


    @Resource
    private OrderMasterMapper orderMasterMapper;


    @Test
    public void insertTest() {

        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId((long) 1);
        orderMaster.setBuyerName("猪头");
        orderMaster.setBuyerPhone("123123123");
        orderMaster.setBuyerAddress("海王星");
        orderMaster.setBuyerOpenid("this is open id");
        orderMaster.setBuyerAmount(new BigDecimal(1231));
        orderMaster.setOrderStatus((byte) 0);
        orderMaster.setPayStatus((byte) 0);


        orderMasterMapper.insert(orderMaster);


        List<OrderMaster> orderMasters = orderMasterMapper.selectAll();

        orderMasters.stream().forEach(System.out::println);

    }


}