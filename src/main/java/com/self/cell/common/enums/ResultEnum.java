package com.self.cell.common.enums;


import lombok.Getter;

@Getter
public enum ResultEnum {

    STOCK_ERROR(11, "库存错误"),

    PRODUCT_NOT_EXIST(12, "商品不存在"),

    ORDER_NOT_EXIST(13, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(14, "订单详情不存在"),

    ORDER_STATUS_ERROR(15, "订单状态不正确"),

    ORDER_UPDATE_ERROR(16, "订单更新失败"),

    ORDER_DETAIL_EMPTY(17, "订单详情为空");


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
