package com.farmer.fruit.models.review;

import com.farmer.fruit.models.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Review  extends BaseEntity {
    private Long id;

    private Long userId;

    private String content;
    /**
     * 口感评分
     */
    private int tasteScore;

    /**
     * 口感平均分
     */
    private double avgTasteScore;
    /**
     * 含水量评分
     */
    private int waterScore;
    /**
     * 含水量平均分
     */
    private double avgWaterScore;
    /**
     * 含糖量评分
     */
    private int sugarScore;
    /**
     * 含糖量两平均分
     */
    private double avgSugarScore;
    /**
     * 总体得分
     */
    private int starLevel;

    /**
     * 总体评分
     */
    private double avgStarLevel;
    /**
     * 评论人数
     */
    private int reviewCount;

    private long userIp;

    private String replayFlag;

    private String fruitCode;

    private long farmerId;

    private String mobile;

    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(long farmerId) {
        this.farmerId = farmerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public int getTasteScore() {
        return tasteScore;
    }

    public void setTasteScore(int tasteScore) {
        this.tasteScore = tasteScore;
    }

    public double getAvgTasteScore() {
        return avgTasteScore;
    }

    public int getWaterScore() {
        return waterScore;
    }

    public void setWaterScore(int waterScore) {
        this.waterScore = waterScore;
    }

    public int getSugarScore() {
        return sugarScore;
    }

    public void setSugarScore(int sugarScore) {
        this.sugarScore = sugarScore;
    }

    public long getUserIp() {
        return userIp;
    }

    public void setUserIp(long userIp) {
        this.userIp = userIp;
    }

    public String getReplayFlag() {
        return replayFlag;
    }

    public void setReplayFlag(String replayFlag) {
        this.replayFlag = replayFlag == null ? null : replayFlag.trim();
    }

    public String getFruitCode() {
        return fruitCode;
    }

    public void setFruitCode(String fruitCode) {
        this.fruitCode = fruitCode == null ? null : fruitCode.trim();
    }

    public double getAvgStarLevel() {
        return avgStarLevel;
    }

    public void setAvgStarLevel(double avgStarLevel) {
        this.avgStarLevel = avgStarLevel;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setAvgTasteScore(double avgTasteScore) {
        this.avgTasteScore = avgTasteScore;
    }

    public double getAvgWaterScore() {
        return avgWaterScore;
    }

    public void setAvgWaterScore(double avgWaterScore) {
        this.avgWaterScore = avgWaterScore;
    }

    public double getAvgSugarScore() {
        return avgSugarScore;
    }

    public void setAvgSugarScore(double avgSugarScore) {
        this.avgSugarScore = avgSugarScore;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", score=").append(starLevel);
        sb.append(", userIp=").append(userIp);
        sb.append(", replayFlag=").append(replayFlag);
        sb.append(", fruitCode=").append(fruitCode);
        sb.append("]");
        return sb.toString();
    }
}