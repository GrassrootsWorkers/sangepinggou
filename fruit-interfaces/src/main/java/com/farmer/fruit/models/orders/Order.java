package com.farmer.fruit.models.orders;

import com.farmer.fruit.models.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuzhi on 2016/10/12.
 */
public class Order extends BaseEntity {
   private Long id;
    private String orderNo;
    private String openId;
    private String mobile;
    private String grade;
    private BigDecimal price;
    private BigDecimal weight;
    private String ifPay;
    private Date payTime;
    private long userIp;
    private String orderStatus;
    private String city;
    private String address;
    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getIfPay() {
        return ifPay;
    }

    public void setIfPay(String ifPay) {
        this.ifPay = ifPay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public long getUserIp() {
        return userIp;
    }

    public void setUserIp(long userIp) {
        this.userIp = userIp;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
