package com.self.sell.modules.seller.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "seller_info")
public class SellerInfo implements Serializable {
    @Column(name = "seller_id")
    private Long sellerId;

    /**
     * 用户名
     */
    @Column(name = "seller_name")
    private String sellerName;

    /**
     * 密码
     */
    @Column(name = "seller_passwd")
    private String sellerPasswd;

    /**
     * 微信openId
     */
    private String openid;

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
     * @return seller_id
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     * @param sellerId
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取用户名
     *
     * @return seller_name - 用户名
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * 设置用户名
     *
     * @param sellerName 用户名
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    /**
     * 获取密码
     *
     * @return seller_passwd - 密码
     */
    public String getSellerPasswd() {
        return sellerPasswd;
    }

    /**
     * 设置密码
     *
     * @param sellerPasswd 密码
     */
    public void setSellerPasswd(String sellerPasswd) {
        this.sellerPasswd = sellerPasswd == null ? null : sellerPasswd.trim();
    }

    /**
     * 获取微信openId
     *
     * @return openid - 微信openId
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openId
     *
     * @param openid 微信openId
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
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