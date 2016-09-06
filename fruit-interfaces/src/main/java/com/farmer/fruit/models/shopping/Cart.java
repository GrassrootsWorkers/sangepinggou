package com.farmer.fruit.models.shopping;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {
    private Integer id;

    private Integer userId;

    private long userIp;

    private Date addTime;
    
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

    public long getUserIp() {
        return userIp;
    }

    public void setUserIp(long userIp) {
        this.userIp = userIp;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}