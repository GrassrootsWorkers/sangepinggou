package com.farmer.fruit.models.user;

import java.util.Date;

import com.farmer.fruit.models.BaseEntity;

public class User extends BaseEntity {
	
	private static final long serialVersionUID = -2139464441482874862L;
    public  static final String NORMAL_USER = "1";
    public  static final String SHOPPER_USER = "2";
	private Long id;

    private String mobile;

    private String name;

    private String password;

    private Date registerTime;

    private Date lastLoginTime;

    private String userType;

    private long ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public long getIp() {
        return ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}