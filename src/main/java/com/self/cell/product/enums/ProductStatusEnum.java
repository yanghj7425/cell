package com.self.cell.product.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP(1, "在架"),
    DOWN(0, "下架");

    private int code;
    private String message;

    ProductStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
