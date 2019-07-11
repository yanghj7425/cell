package com.self.sell.modules.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0, "新下单"),
    FINISHED(1, "已完结"),
    CANCEL(2, "已取消");


    /**
     * 状态
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    OrderStatusEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
