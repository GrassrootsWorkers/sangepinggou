package com.farmer.fruit.models.review;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    private Integer id;

    private Integer userId;

    private String content;

    private Byte score;

    private Date addTime;

    private String userIp;

    private String replayFlag;

    private String fruitCode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        this.content = content == null ? null : content.trim();
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
        this.userIp = userIp == null ? null : userIp.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", score=").append(score);
        sb.append(", addTime=").append(addTime);
        sb.append(", userIp=").append(userIp);
        sb.append(", replayFlag=").append(replayFlag);
        sb.append(", fruitCode=").append(fruitCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}