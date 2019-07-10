package com.self.cell.modules.order.form;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderFrom {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;


    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;


    /**
     * 买家 openId
     */
    @NotEmpty(message = "openId 必填")
    private String openId;

    /**
     * 买家购物车信息
     */
    @NotEmpty(message = "购物车信息")
    private String items;

}
