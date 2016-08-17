package com.farmer.fruit.sgpg.models.vo;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FarmerVo{

	private Long farmerId;
	/**
	 * 手机号
	 */
	
	private String mobile;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户确认的密码
	 */
	private String confirmPassword;
	/**
	 * 用户的身份证
	 */
	private String idCard;
	/**
	 * 用户是否审核
	 */
	private String auditFlag;
	/**
	 * 用户的家乡
	 */
	private String homeTown;
	/**
	 * 农户现在住址
	 */
	private String currentAddress;
	/**
	 * 植物生长的地址（产地）
	 */
	private String growthAddress;
	/**
	 * 种植经验值
	 */
	private int experienceAge;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 
	 */
	private String lastLoginTime;
	/**
	 * 验证码
	 */
	private String validateCode;

	public boolean validateMobile(String mobile){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public int getExperienceAge() {
		return experienceAge;
	}

	public void setExperienceAge(int experienceAge) {
		this.experienceAge = experienceAge;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
}
