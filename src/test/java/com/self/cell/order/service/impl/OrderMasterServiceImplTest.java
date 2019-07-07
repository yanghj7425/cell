package com.self.cell.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.self.cell.common.pojo.bo.PageParam;
import com.self.cell.order.entity.OrderMaster;
import com.self.cell.order.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
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
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void insertOne() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(111111211111L);
        orderMaster.setBuyerAddress("地球");
        orderMaster.setBuyerName("Dom,Yang");
        orderMaster.setBuyerAmount(new BigDecimal(3.4));
        orderMaster.setBuyerOpenid("101001010101");
        orderMaster.setBuyerPhone("10293894785");
        Integer integer = orderMasterService.insertOne(orderMaster);
        Assert.assertNotNull(integer);
    }


    @Test
    public void InsertList() {


        List<OrderMaster> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            OrderMaster orderMaster = new OrderMaster();
            orderMaster.setOrderId((long) i);
            orderMaster.setBuyerAddress("地球");
            orderMaster.setBuyerName("Dom,Yang");
            orderMaster.setBuyerAmount(new BigDecimal(3.4));
            orderMaster.setBuyerOpenid("101001010101");
            orderMaster.setBuyerPhone("10293894785");
            list.add(orderMaster);
        }
        orderMasterService.insertList(list);
    }


    @Test
    public void queryOrderByBuyerOpenId() {
        PageParam pageParam = PageParam.builder().build();
        pageParam.setPageNum(3);
        PageInfo<OrderMaster> orderMasterPageInfo = orderMasterService.queryOrderByBuyerOpenId(pageParam, "101001010101");
        Assert.assertNotEquals(0, orderMasterPageInfo.getPageSize());
        orderMasterPageInfo.getList().forEach(e -> log.debug(e.toString()));
    }
}