package com.farmer.fruit.models.fruit;

import com.farmer.fruit.models.QueryDataEntity;

public class FruitInformationQuery extends QueryDataEntity {

    private Long id;

    private String type;

    private int growthPeriod;

    private String productionPlace;

    private String productionPlaceDesc;

    private String company;

    private String environmentDesc;

    private Long brandId;

    private Long varietyId;

    private Long farmerId;

    private String storageWay;

    private Byte destroyRate;

    private Byte waterRate;

    private Byte sugarRate;

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

    public String getEnvironmentDesc() {
        return environmentDesc;
    }

    public void setEnvironmentDesc(String environmentDesc) {
        this.environmentDesc = environmentDesc;
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

    public Byte getDestroyRate() {
        return destroyRate;
    }

    public void setDestroyRate(Byte destroyRate) {
        this.destroyRate = destroyRate;
    }

    public Byte getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Byte waterRate) {
        this.waterRate = waterRate;
    }

    public Byte getSugarRate() {
        return sugarRate;
    }

    public void setSugarRate(Byte sugarRate) {
        this.sugarRate = sugarRate;
    }
}