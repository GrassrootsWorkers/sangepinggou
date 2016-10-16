package com.farmer.fruit.models.review;

import com.farmer.fruit.models.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Review  extends BaseEntity {
    private Long id;

    private Long userId;

    private String content;

    private Byte score;

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

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
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
        sb.append(", userIp=").append(userIp);
        sb.append(", replayFlag=").append(replayFlag);
        sb.append(", fruitCode=").append(fruitCode);
        sb.append("]");
        return sb.toString();
    }
}