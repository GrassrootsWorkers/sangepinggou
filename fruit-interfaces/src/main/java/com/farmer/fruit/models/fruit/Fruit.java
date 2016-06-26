package com.farmer.fruit.models.fruit;

import java.io.Serializable;
import java.util.Date;

public class Fruit implements Serializable {
    private Long id;

    private String fruitCode;

    private Integer baseCode;

    private Double weight;

    private Double height;

    private Double width;

    private Double high;

    private Byte maturingStatus;

    private Date harvestTime;

    private Double marketPrice;

    private Date addTime;

    private Integer addUserId;

    private Integer checkoutUserId;

    private Date checkoutTime;

    private String deleteFlag;

    private String priceFlag;

    private String lotteryFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFruitCode() {
        return fruitCode;
    }

    public void setFruitCode(String fruitCode) {
        this.fruitCode = fruitCode == null ? null : fruitCode.trim();
    }

    public Integer getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(Integer baseCode) {
        this.baseCode = baseCode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Byte getMaturingStatus() {
        return maturingStatus;
    }

    public void setMaturingStatus(Byte maturingStatus) {
        this.maturingStatus = maturingStatus;
    }

    public Date getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(Date harvestTime) {
        this.harvestTime = harvestTime;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getCheckoutUserId() {
        return checkoutUserId;
    }

    public void setCheckoutUserId(Integer checkoutUserId) {
        this.checkoutUserId = checkoutUserId;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public String getPriceFlag() {
        return priceFlag;
    }

    public void setPriceFlag(String priceFlag) {
        this.priceFlag = priceFlag == null ? null : priceFlag.trim();
    }

    public String getLotteryFlag() {
        return lotteryFlag;
    }

    public void setLotteryFlag(String lotteryFlag) {
        this.lotteryFlag = lotteryFlag == null ? null : lotteryFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fruitCode=").append(fruitCode);
        sb.append(", baseCode=").append(baseCode);
        sb.append(", weight=").append(weight);
        sb.append(", height=").append(height);
        sb.append(", width=").append(width);
        sb.append(", high=").append(high);
        sb.append(", maturingStatus=").append(maturingStatus);
        sb.append(", harvestTime=").append(harvestTime);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", addTime=").append(addTime);
        sb.append(", addUserId=").append(addUserId);
        sb.append(", checkoutUserId=").append(checkoutUserId);
        sb.append(", checkoutTime=").append(checkoutTime);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", priceFlag=").append(priceFlag);
        sb.append(", lotteryFlag=").append(lotteryFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}