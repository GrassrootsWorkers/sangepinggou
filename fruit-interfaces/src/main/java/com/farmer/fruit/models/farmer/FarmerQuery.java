package com.farmer.fruit.models.farmer;

import java.util.Date;

import com.farmer.fruit.models.QueryDataEntity;

public class FarmerQuery extends QueryDataEntity {

	private static final long serialVersionUID = 481441946191855172L;

	private Integer farmerId;

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

	public Integer getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Integer farmerId) {
		this.farmerId = farmerId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getGrowthAddress() {
		return growthAddress;
	}

	public void setGrowthAddress(String growthAddress) {
		this.growthAddress = growthAddress;
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
		this.token = token;
	}

}