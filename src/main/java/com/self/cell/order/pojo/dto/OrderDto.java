package com.self.cell.order.pojo.dto;

import com.self.cell.order.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信id
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal buyerAmount;

    /**
     * 订单状态,默认新下单
     */
    private Byte orderStatus;

    /**
     * 支付状态, 默认未支付
     */
    private Byte payStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    private List<OrderDetail> orderDetailList;

}
