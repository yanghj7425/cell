package com.self.sell.modules.product.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品ID
 */
@Data
public class ProductInfoVo implements Serializable {

    private static final long serialVersionUID = 8097962386185589491L;

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

