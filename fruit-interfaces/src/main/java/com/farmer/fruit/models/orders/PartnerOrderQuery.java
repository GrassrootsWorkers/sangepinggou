package com.farmer.fruit.models.orders;

import com.farmer.fruit.models.BaseEntity;
import com.farmer.fruit.models.QueryDataEntity;

import java.util.Date;

/**
 * Created by liuzhi on 2016/11/27.
 */
public class PartnerOrderQuery extends QueryDataEntity {
    private long id;
    private String orderNo;
    private String tradeNo;
    private String openId;
    private int totalFee;
    private Date payTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
