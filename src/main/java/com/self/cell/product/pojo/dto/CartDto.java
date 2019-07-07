package com.self.cell.product.pojo.dto;


import lombok.Data;

@Data
public class CartDto {

    /**
     * 订单ID
     */
    private long productId;

    /**
     * 购买数量
     */
    private Integer productQuantity;

    public CartDto(long productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
