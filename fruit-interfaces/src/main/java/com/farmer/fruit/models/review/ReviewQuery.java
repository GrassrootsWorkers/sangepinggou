package com.farmer.fruit.models.review;

import com.farmer.fruit.models.QueryDataEntity;

import java.util.Date;

public class ReviewQuery extends QueryDataEntity{
    private Long id;

    private Integer userId;

    private String content;

    private Byte score;

    private Date addTime;

    private String userIp;

    private String replayFlag;

    private String fruitCode;

    private int starLevel;

    private long farmerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getReplayFlag() {
        return replayFlag;
    }

    public void setReplayFlag(String replayFlag) {
        this.replayFlag = replayFlag;
    }

    public String getFruitCode() {
        return fruitCode;
    }

    public void setFruitCode(String fruitCode) {
        this.fruitCode = fruitCode;
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
}