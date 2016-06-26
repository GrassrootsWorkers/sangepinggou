package com.farmer.fruit.models.store;

import java.io.Serializable;

public class Price implements Serializable {
    private Integer id;

    private Integer supermarketId;

    private Integer userId;

    private String type;

    private Integer brandId;

    private Double price;

    private Integer address;

    private Integer lastPriceId;

    private String auditFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupermarketId() {
        return supermarketId;
    }

    public void setSupermarketId(Integer supermarketId) {
        this.supermarketId = supermarketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getLastPriceId() {
        return lastPriceId;
    }

    public void setLastPriceId(Integer lastPriceId) {
        this.lastPriceId = lastPriceId;
    }

    public String getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag == null ? null : auditFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supermarketId=").append(supermarketId);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", brandId=").append(brandId);
        sb.append(", price=").append(price);
        sb.append(", address=").append(address);
        sb.append(", lastPriceId=").append(lastPriceId);
        sb.append(", auditFlag=").append(auditFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}