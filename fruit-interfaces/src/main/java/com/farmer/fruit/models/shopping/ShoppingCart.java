package com.farmer.fruit.models.shopping;

import java.io.Serializable;
import java.util.Date;

public class ShoppingCart implements Serializable {
    private Integer id;

    private String fruitCode;

    private Integer userId;

    private String userFlag;

    private String buyFlag;

    private Date addTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFruitCode() {
        return fruitCode;
    }

    public void setFruitCode(String fruitCode) {
        this.fruitCode = fruitCode == null ? null : fruitCode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag == null ? null : userFlag.trim();
    }

    public String getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(String buyFlag) {
        this.buyFlag = buyFlag == null ? null : buyFlag.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fruitCode=").append(fruitCode);
        sb.append(", userId=").append(userId);
        sb.append(", userFlag=").append(userFlag);
        sb.append(", buyFlag=").append(buyFlag);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}