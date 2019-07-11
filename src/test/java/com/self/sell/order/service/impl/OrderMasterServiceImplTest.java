package com.self.sell.order.service.impl;

import com.github.pagehelper.PageInfo;
import com.self.sell.common.pojo.bo.PageParam;
import com.self.sell.modules.order.entity.OrderDetail;
import com.self.sell.modules.order.pojo.dto.OrderDto;
import com.self.sell.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "this_is_open_id_str";


    private final long ORDER_ID = 1562551090758L;

    @Test
    public void create() {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("Yang.J");
        orderDto.setBuyerPhone("141242343");
        orderDto.setBuyerAddress("YN");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> list = new ArrayList<>();

        OrderDetail detail = new OrderDetail();

        detail.setProductId(1L);
        detail.setProductQuantity(3);


        OrderDetail detail1 = new OrderDetail();

        detail1.setProductId(2L);
        detail1.setProductQuantity(3);

        OrderDetail detail2 = new OrderDetail();

        detail2.setProductId(3L);
        detail2.setProductQuantity(3);


        list.add(detail);
        list.add(detail1);
        list.add(detail2);

        orderDto.setOrderDetailList(list);

        orderDto = orderService.create(orderDto);

        Assert.assertNotNull(orderDto);
    }


    @Test
    public void queryOne() {
        OrderDto orderDto = orderService.queryOne(ORDER_ID);
        log.debug("[订单详情]  {}", orderDto.toString());
    }


    @Test
    public void cancel() {
        OrderDto orderDto = orderService.queryOne(ORDER_ID);
        OrderDto cancel = orderService.cancel(orderDto);
        orderDto = orderService.queryOne(ORDER_ID);
        Assert.assertEquals(orderDto.getOrderStatus(), cancel.getOrderStatus());
    }


    @Test
    public void finish() {
        OrderDto orderDto = orderService.queryOne(ORDER_ID);
        OrderDto cancel = orderService.finish(orderDto);
        Assert.assertEquals(orderDto.getOrderStatus(), cancel.getOrderStatus());

    }


    @Test
    public void paid() {
        OrderDto orderDto = orderService.queryOne(ORDER_ID);
        OrderDto cancel = orderService.paid(orderDto);
        log.info(cancel.toString());
    }


    @Test
    public void queryOrderByBuyerOpenId() {
        PageParam pageParam = PageParam.builder().build();
        pageParam.setPageNum(1);
        PageInfo<OrderDto> orderMasterPageInfo = orderService.queryOrderList(BUYER_OPENID, pageParam);
        Assert.assertNotEquals(0, orderMasterPageInfo.getPageSize());
        orderMasterPageInfo.getList().forEach(e -> log.debug(e.toString()));
    }
}