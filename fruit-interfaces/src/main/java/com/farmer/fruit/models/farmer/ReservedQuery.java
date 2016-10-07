package com.farmer.fruit.models.farmer;

import com.farmer.fruit.models.QueryDataEntity;

import java.util.Date;

public class ReservedQuery extends QueryDataEntity {
    public static String SEND_OUT = "5";
    public static String WAIT_PRINT = "5";
    public static String PRINT = "6";
    public static String USED = "7";
    public static String APPLIED = "2";
    private Long id;

    private Long farmerId;

    private String begin;

    private String end;

    private String status;

    private String token;

    private Integer applyCount;

    private Date applyTime;

    /**
     * 水果类型 苹果，梨
     */
    private String type;
    /**
     * 品牌Id
     */
    private Integer brandId;
    /**
     * 品种Id
     */
    private Integer varietyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}