package com.farmer.fruit.models.pageview;

import com.farmer.fruit.models.BaseEntity;

/**
 * Created by liuzhi on 2016/10/7.
 */
public class PageView extends BaseEntity {
    private Long id;
    private Long userId;
    private long ip;
    private String fruitCode;
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getIp() {
        return ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    public String getFruitCode() {
        return fruitCode;
    }

    public void setFruitCode(String fruitCode) {
        this.fruitCode = fruitCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
