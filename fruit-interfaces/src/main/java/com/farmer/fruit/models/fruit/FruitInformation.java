package com.farmer.fruit.models.fruit;

import com.farmer.fruit.models.BaseEntity;

public class FruitInformation extends BaseEntity{

    private Long id;

    private String type;

    private String typeName;

    private int growthPeriod;

    private String productionPlace;

    private String productionPlaceDesc;

    private String company;

    private String FarmerDesc;

    private Long brandId;

    private String brandName;

    private Long varietyId;

    private String varietyName;

    private Long farmerId;

    private String storageWay;

    private int destroyRate;

    private int waterRate;

    private int sugarRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGrowthPeriod() {
        return growthPeriod;
    }

    public void setGrowthPeriod(int growthPeriod) {
        this.growthPeriod = growthPeriod;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getProductionPlaceDesc() {
        return productionPlaceDesc;
    }

    public void setProductionPlaceDesc(String productionPlaceDesc) {
        this.productionPlaceDesc = productionPlaceDesc;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFarmerDesc() {
        return FarmerDesc;
    }

    public void setFarmerDesc(String farmerDesc) {
        FarmerDesc = farmerDesc;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(Long varietyId) {
        this.varietyId = varietyId;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getStorageWay() {
        return storageWay;
    }

    public void setStorageWay(String storageWay) {
        this.storageWay = storageWay;
    }

    public int getDestroyRate() {
        return destroyRate;
    }

    public void setDestroyRate(int destroyRate) {
        this.destroyRate = destroyRate;
    }

    public int getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(int waterRate) {
        this.waterRate = waterRate;
    }

    public int getSugarRate() {
        return sugarRate;
    }

    public void setSugarRate(int sugarRate) {
        this.sugarRate = sugarRate;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }
}