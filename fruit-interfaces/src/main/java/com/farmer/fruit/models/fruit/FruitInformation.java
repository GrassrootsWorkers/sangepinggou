package com.farmer.fruit.models.fruit;

import java.io.Serializable;

public class FruitInformation implements Serializable {
    private Integer id;

    private Byte type;

    private Byte growthPeriod;

    private String productionPlace;

    private String productionPlaceDesc;

    private String company;

    private String environmentDesc;

    private Integer brandId;

    private Integer varietyId;

    private Integer farmerId;

    private String storageWay;

    private Byte deratingRate;

    private Byte waterRate;

    private Byte sugerRate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getGrowthPeriod() {
        return growthPeriod;
    }

    public void setGrowthPeriod(Byte growthPeriod) {
        this.growthPeriod = growthPeriod;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace == null ? null : productionPlace.trim();
    }

    public String getProductionPlaceDesc() {
        return productionPlaceDesc;
    }

    public void setProductionPlaceDesc(String productionPlaceDesc) {
        this.productionPlaceDesc = productionPlaceDesc == null ? null : productionPlaceDesc.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getEnvironmentDesc() {
        return environmentDesc;
    }

    public void setEnvironmentDesc(String environmentDesc) {
        this.environmentDesc = environmentDesc == null ? null : environmentDesc.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(Integer varietyId) {
        this.varietyId = varietyId;
    }

    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
    }

    public String getStorageWay() {
        return storageWay;
    }

    public void setStorageWay(String storageWay) {
        this.storageWay = storageWay == null ? null : storageWay.trim();
    }

    public Byte getDeratingRate() {
        return deratingRate;
    }

    public void setDeratingRate(Byte deratingRate) {
        this.deratingRate = deratingRate;
    }

    public Byte getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Byte waterRate) {
        this.waterRate = waterRate;
    }

    public Byte getSugerRate() {
        return sugerRate;
    }

    public void setSugerRate(Byte sugerRate) {
        this.sugerRate = sugerRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", growthPeriod=").append(growthPeriod);
        sb.append(", productionPlace=").append(productionPlace);
        sb.append(", productionPlaceDesc=").append(productionPlaceDesc);
        sb.append(", company=").append(company);
        sb.append(", environmentDesc=").append(environmentDesc);
        sb.append(", brandId=").append(brandId);
        sb.append(", varietyId=").append(varietyId);
        sb.append(", farmerId=").append(farmerId);
        sb.append(", storageWay=").append(storageWay);
        sb.append(", deratingRate=").append(deratingRate);
        sb.append(", waterRate=").append(waterRate);
        sb.append(", sugerRate=").append(sugerRate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}