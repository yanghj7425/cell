package com.self.cell.modules.product.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品ID
 */
@Data
public class ProductInfoVo {


    @JsonProperty("id")
    private long productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price'")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;


}

