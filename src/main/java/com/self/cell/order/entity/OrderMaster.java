package com.self.cell.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_master")
public class OrderMaster implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 买家姓名
     */
    @Column(name = "buyer_name")
    private String buyerName;

    /**
     * 买家电话
     */
    @Column(name = "buyer_phone")
    private String buyerPhone;

    /**
     * 买家地址
     */
    @Column(name = "buyer_address")
    private String buyerAddress;

    /**
     * 买家微信id
     */
    @Column(name = "buyer_openid")
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    @Column(name = "buyer_amount")
    private BigDecimal buyerAmount;

    /**
     * 订单状态,默认新下单
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 支付状态, 默认未支付
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取买家姓名
     *
     * @return buyer_name - 买家姓名
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * 设置买家姓名
     *
     * @param buyerName 买家姓名
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    /**
     * 获取买家电话
     *
     * @return buyer_phone - 买家电话
     */
    public String getBuyerPhone() {
        return buyerPhone;
    }

    /**
     * 设置买家电话
     *
     * @param buyerPhone 买家电话
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone == null ? null : buyerPhone.trim();
    }

    /**
     * 获取买家地址
     *
     * @return buyer_address - 买家地址
     */
    public String getBuyerAddress() {
        return buyerAddress;
    }

    /**
     * 设置买家地址
     *
     * @param buyerAddress 买家地址
     */
    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress == null ? null : buyerAddress.trim();
    }

    /**
     * 获取买家微信id
     *
     * @return buyer_openid - 买家微信id
     */
    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    /**
     * 设置买家微信id
     *
     * @param buyerOpenid 买家微信id
     */
    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid == null ? null : buyerOpenid.trim();
    }

    /**
     * 获取订单总金额
     *
     * @return buyer_amount - 订单总金额
     */
    public BigDecimal getBuyerAmount() {
        return buyerAmount;
    }

    /**
     * 设置订单总金额
     *
     * @param buyerAmount 订单总金额
     */
    public void setBuyerAmount(BigDecimal buyerAmount) {
        this.buyerAmount = buyerAmount;
    }

    /**
     * 获取订单状态,默认新下单
     *
     * @return order_status - 订单状态,默认新下单
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态,默认新下单
     *
     * @param orderStatus 订单状态,默认新下单
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取支付状态, 默认未支付
     *
     * @return pay_status - 支付状态, 默认未支付
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态, 默认未支付
     *
     * @param payStatus 支付状态, 默认未支付
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}