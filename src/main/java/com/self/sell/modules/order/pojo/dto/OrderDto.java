package com.self.sell.modules.order.pojo.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.self.sell.common.util.EnumUtils;
import com.self.sell.common.util.serializer.Date2LongSerializer;
import com.self.sell.modules.order.entity.OrderDetail;
import com.self.sell.modules.order.enums.OrderStatusEnum;
import com.self.sell.modules.order.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
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
//    @JsonSerialize(using = Date2LongSerializer.class)

    @JSONField(name = "a", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JSONField(serializeUsing = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;


    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtils.getByCode(payStatus.intValue(), PayStatusEnum.class);
    }

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtils.getByCode(orderStatus.intValue(), OrderStatusEnum.class);
    }


}
