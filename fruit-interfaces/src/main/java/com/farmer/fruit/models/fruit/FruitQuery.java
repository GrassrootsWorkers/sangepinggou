package com.farmer.fruit.models.fruit;

import com.farmer.fruit.models.QueryDataEntity;

import java.util.Date;

public class FruitQuery extends QueryDataEntity {
    private Long id;
    private Long farmerId;
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

    private Long addFarmerId;

    private Long checkoutUserId;

    private Date checkoutTime;

    private String deleteFlag;

    private String priceFlag;

    private String lotteryFlag;

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
        this.fruitCode = fruitCode;
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

    public Long getAddFarmerId() {
        return addFarmerId;
    }

    public void setAddFarmerId(Long addFarmerId) {
        this.addFarmerId = addFarmerId;
    }

    public Long getCheckoutUserId() {
        return checkoutUserId;
    }

    public void setCheckoutUserId(Long checkoutUserId) {
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
        this.deleteFlag = deleteFlag;
    }

    public String getPriceFlag() {
        return priceFlag;
    }

    public void setPriceFlag(String priceFlag) {
        this.priceFlag = priceFlag;
    }

    public String getLotteryFlag() {
        return lotteryFlag;
    }

    public void setLotteryFlag(String lotteryFlag) {
        this.lotteryFlag = lotteryFlag;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }
}