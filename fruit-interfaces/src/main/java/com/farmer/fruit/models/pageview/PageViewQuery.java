package com.farmer.fruit.models.pageview;

import com.farmer.fruit.models.QueryDataEntity;

/**
 * Created by liuzhi on 2016/10/7.
 */
public class PageViewQuery extends QueryDataEntity {
    private Long id;
    private Long userId;
    private long ip;
    private String fruitCode;

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
}
