package com.farmer.fruit.models.fruit;

import com.farmer.fruit.models.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Fruit extends BaseEntity {
    private Long id;

    private Long farmerId;

    private String fruitCode;

    private Long baseCode;

    /**
     * 申请记录的id
     */
    private Long reservedId;
    /**
     * 基础信息块的url
     */
    private String baseInfoPath;
    /**
     * 水果种类
     */
    private String type;
    /**
     * 水果种类名称
     */
    private String typeName;

    private Long brandId;

    private String brandName;

    private Long varietyId;

    private String varietyName;

    private Double weight;

    private Double height;

    private Double width;

    private Double high;

    private Byte maturingStatus;

    private Date harvestTime;

    private Double marketPrice;

    private Date addTime;

    private Long addUserId;

    private Long checkoutUserId;

    private Date checkoutTime;


    private String priceFlag;

    private String lotteryFlag;
    /**
     * 主图
     */
    private String image;
    /**
     * 小图
     */
    private String smallImage;
    /**
     * 二维码
     */
    private String qrPath;
    /**
     * 标记是否出售
     */
    private String ifSale;
    /**
     * 图片的原始路径
     */
    private String origImage;
    /**
     * 静态文件的路径
     */
    private String filePath;
    /**
     * 扫描次数
     */
    private int clickTimes;
    /**
     * 称重单位
     */
    private String unit;
    /**
     * 售价
     */
    private BigDecimal salePrice;
    /**
     * 单个苹果的总价
     */
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        if(salePrice == null) salePrice = new BigDecimal(marketPrice);
        totalPrice = new BigDecimal(weight).divide(new BigDecimal(1000)).multiply(salePrice);
        return totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
    public String getImage() {
        return "http://p.sangepg.com/images/fruit/"+id /10000 +"/"+id+ "/center.jpg";
    }

    public String getSmallImage() {
        return "http://p.sangepg.com/images/fruit/"+id /10000 +"/"+id+ "/small.jpg";
    }
    public String getQrPath() {
        return "http://p.sangepg.com/images/qr/"+getFarmerId() +"/"+reservedId + "/" + fruitCode + ".png";
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Long getFarmerId() {
        return addUserId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getFilePath() {
        filePath= filePath + fruitCode + ".html";
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBaseInfoPath() {
        return baseInfoPath;
    }

    public void setBaseInfoPath(String baseInfoPath) {
        this.baseInfoPath = baseInfoPath;
    }

    private static final long serialVersionUID = 1L;

    public String getOrigImage() {
        return origImage;
    }

    public void setOrigImage(String origImage) {
        this.origImage = origImage;
    }

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

    public Long getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(Long baseCode) {
        this.baseCode = baseCode;
    }

    public BigDecimal getWeight() {
        return new BigDecimal(weight).setScale(0,BigDecimal.ROUND_HALF_UP);
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

    public Long getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Long addUserId) {
        this.addUserId = addUserId;
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

    public void setImage(String image) {
        this.image = image;
    }


    public void setQrPath(String qrPath) {
        this.qrPath = qrPath;
    }

    public String getIfSale() {
        return ifSale;
    }

    public void setIfSale(String ifSale) {
        this.ifSale = ifSale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(Long varietyId) {
        this.varietyId = varietyId;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public int getClickTimes() {
        return clickTimes;
    }

    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }

    public Long getReservedId() {
        return reservedId;
    }

    public void setReservedId(Long reservedId) {
        this.reservedId = reservedId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", farmerId=" + farmerId +
                ", fruitCode='" + fruitCode + '\'' +
                ", baseCode=" + baseCode +
                ", baseInfoPath='" + baseInfoPath + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", varietyId=" + varietyId +
                ", varietyName='" + varietyName + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", width=" + width +
                ", high=" + high +
                ", maturingStatus=" + maturingStatus +
                ", marketPrice=" + marketPrice +
                ", priceFlag='" + priceFlag + '\'' +
                ", lotteryFlag='" + lotteryFlag + '\'' +
                ", qrPath='" + qrPath + '\'' +
                ", ifSale='" + ifSale + '\'' +
                ", addUserId=" + addUserId +
                '}';
    }
}