package com.self.sell.common.enums;


import lombok.Getter;

@Getter
public enum ResultEnum implements CodeEnum {


    CART_EMPTY_ERROR(19, "购物车不能为空"),

    SELLER_INFO_NOT_EXIST(401, "卖家信息不存在"),


    ORDER_PAY_AMOUNT_ERROR(307, "订单支付状态不正确"),

    ORDER_OWNER__ERROR(306, "该订单不属于当前用户"),

    ORDER_PAY_STATUS_ERROR(305, "订单支付状态不正确"),

    ORDER_DETAIL_EMPTY(304, "订单详情为空"),

    ORDER_UPDATE_ERROR(303, "订单更新失败"),

    ORDER_STATUS_ERROR(302, "订单状态不正确"),

    ORDER_DETAIL_NOT_EXIST(301, "订单详情不存在"),

    ORDER_NOT_EXIST(300, "订单不存在"),

    PRODUCT_NOT_EXIST(200, "商品不存在"),

    STOCK_ERROR(100, "库存错误"),

    PARAM_ERROR(1, "参数不正确");


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
