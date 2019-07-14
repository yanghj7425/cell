package com.self.sell.modules.product.enums;

import com.self.sell.common.enums.CodeEnum;
import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(1, "在架"),
    DOWN(0, "下架");

    private Integer code;
    private String message;

    ProductStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
