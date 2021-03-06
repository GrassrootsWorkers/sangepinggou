package com.farmer.fruit.models.farmer;

import com.farmer.fruit.models.BaseEntity;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reserved extends BaseEntity {
    public  static Map<String,String> statusNameMap;

    public static final String P_FLAGE = "Y";
    static {
        //第一期用0-->1-->5-->6-->7
        statusNameMap = new HashMap<String,String>();
        statusNameMap.put("0","已经保存");
        statusNameMap.put("1","正在审核");
        //statusNameMap.put("2","等待付款");
        statusNameMap.put("2","审核通过");
        statusNameMap.put("3","审核未通过");
        statusNameMap.put("4","等待发货");
        //statusNameMap.put("5","已经发货");
        statusNameMap.put("5","等待打印");
        //statusNameMap.put("6","等待确认收货");
        statusNameMap.put("6","等待添加水果");
        statusNameMap.put("7","已经完成");
    }
    private Long id;

    private Long farmerId;

    private int begin;

    private int end;

    private String status;

    private String statusName;

    private String token;

    private Integer applyCount;

    private Date applyTime;

    /**
     * 水果类型 苹果，梨
     */
    private String type;
    /**
     * 水果类型名称
     */
    private String typeName;
    /**
     * 品牌Id
     */
    private Long brandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品种Id
     */
    private Long varietyId;
    /**
     * 系列名称
     */
    private String varietyName;
    /**
     * 审核描述
     */
    private String auditDesc;
    /**
     * 申请留言
     */
    private String applyDesc;
    /**
     * 上传后的文件路径
     */
    private String filePath;
    /**
     * 上传后的图片路径
     */
    private String picturePath;
    /**
     * 验证token是否正确
     */
    private String testUrl;
    /**
     * 市场价格
     */
    private Double marketPrice;
    /**
     * 采摘日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS" )
    private Date harvestTime;
    /**
     * 标记是否完成
     */
    private String finishFlag;
    /**
     * 标记是否共用同一张图片
     */
    private String pictureFlag;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 成熟度
     */
    private String maturingStatus;

    public String getMaturingStatus() {
        return maturingStatus;
    }

    public void setMaturingStatus(String maturingStatus) {
        this.maturingStatus = maturingStatus;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    private static final long serialVersionUID = 1L;

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
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

    public String getStatusName() {
        String name = statusNameMap.get(status);
        return name;
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

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getApplyDesc() {
        return applyDesc;
    }

    public void setApplyDesc(String applyDesc) {
        this.applyDesc = applyDesc;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Date getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(Date harvestTime) {
        this.harvestTime = harvestTime;
    }

    public String getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag;
    }

    public String getPictureFlag() {
        return pictureFlag;
    }

    public void setPictureFlag(String pictureFlag) {
        this.pictureFlag = pictureFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", farmerId=").append(farmerId);
        sb.append(", begin=").append(begin);
        sb.append(", end=").append(end);
        sb.append(", status=").append(status);
        sb.append(", token=").append(token);
        sb.append(", count=").append(applyCount);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}