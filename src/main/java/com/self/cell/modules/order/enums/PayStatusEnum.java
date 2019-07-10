package com.self.cell.modules.order.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),;

    /**
     * 状态
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;


    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
