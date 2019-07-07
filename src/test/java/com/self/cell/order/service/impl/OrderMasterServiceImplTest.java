package com.self.cell.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.self.cell.common.pojo.bo.PageParam;
import com.self.cell.order.entity.OrderDetail;
import com.self.cell.order.entity.OrderMaster;
import com.self.cell.order.pojo.dto.OrderDto;
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

    private final String BUYER_OPENID = "this_is_open_id_str";


    private final long ORDER_ID = 1562508865153L;

    @Test
    public void create() {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("Yang.J");
        orderDto.setBuyerPhone("141242343");
        orderDto.setBuyerAddress("YN");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> list = new ArrayList<>();

        OrderDetail detail = new OrderDetail();

        detail.setProductId(85L);
        detail.setProductQuantity(3);


        OrderDetail detail1 = new OrderDetail();

        detail1.setProductId(82L);
        detail1.setProductQuantity(3);

        OrderDetail detail2 = new OrderDetail();

        detail2.setProductId(83L);
        detail2.setProductQuantity(3);


        list.add(detail);
        list.add(detail1);
        list.add(detail2);

        orderDto.setOrderDetailList(list);

        orderDto = orderMasterService.create(orderDto);

        Assert.assertNotNull(orderDto);

    }


    @Test
    public void queryOne() {
        OrderDto orderDto = orderMasterService.queryOne(ORDER_ID);
        log.debug("[订单详情]  {}", orderDto.toString());
    }

    @Test
    public void queryOrderByBuyerOpenId() {
        PageParam pageParam = PageParam.builder().build();
        pageParam.setPageNum(1);
        PageInfo<OrderDto> orderMasterPageInfo = orderMasterService.queryOrderList(pageParam, BUYER_OPENID);
        Assert.assertNotEquals(0, orderMasterPageInfo.getPageSize());
        orderMasterPageInfo.getList().forEach(e -> log.debug(e.toString()));
    }
}