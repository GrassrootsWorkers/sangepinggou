package com.farmer.fruit.models;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;	// 备注

	protected Date createTime;	// 创建日期
	
	protected Date updateTime;	// 更新日期
	
	protected String deleteFlag;// 删除标识（0：未删除，1：删除）
	
	protected boolean isNewRecord;
	/**
	 * 记录的状态
	 */
	private String status;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isNewRecord() {
		return isNewRecord;
	}
	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
	
	
	
}
