package com.farmer.fruit.models.farmer;

import com.farmer.fruit.models.BaseEntity;

import java.util.Date;

public class Farmer  extends BaseEntity {
	
    private Long id;

    private String mobile;

    private String name;

    private String password;

    private String idCard;

    private String auditFlag;

    /**
     * 家庭住址
     */
    private String homeTown;

    private String currentAddress;

    private String growthAddress;

    private Byte experienceAge;

    private Date registerTime;

    private Date lastLoginTime;

    private String token;

    private static final long serialVersionUID = 1L;

    public String getGrowthAddress() {
        return growthAddress;
    }

    public void setGrowthAddress(String growthAddress) {
        this.growthAddress = growthAddress;
    }

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
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag == null ? null : auditFlag.trim();
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown == null ? null : homeTown.trim();
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress == null ? null : currentAddress.trim();
    }


    public Byte getExperienceAge() {
        return experienceAge;
    }

    public void setExperienceAge(Byte experienceAge) {
        this.experienceAge = experienceAge;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobile=").append(mobile);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", idCard=").append(idCard);
        sb.append(", auditFlag=").append(auditFlag);
        sb.append(", homeTown=").append(homeTown);
        sb.append(", currentAddress=").append(currentAddress);
        sb.append(", growthAddress=").append(growthAddress);
        sb.append(", experienceAge=").append(experienceAge);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", token=").append(token);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}