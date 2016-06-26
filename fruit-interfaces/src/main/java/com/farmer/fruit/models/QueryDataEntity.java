package com.farmer.fruit.models;

import java.io.Serializable;
import java.util.Date;

public abstract class QueryDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 开始日期
	 */
	private Date startDate;
	/**
	 * 结束日期
	 */
	private Date endDate;
	/**
	 * 第几条记录
	 */
	private int pageNo;
	/**
	 * 默认20条记录
	 */
	private int pageSize = 20;
	/**
	 * 页码
	 */
	private int pageIndex;
	/**
	 * 总记录页数
	 */
	private int totalPage;
	/**
	 * 总记录数
	 */
	private int count;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPageNo() {
		pageIndex = pageIndex == 0 ? 1 : pageIndex;
		return pageSize * (pageIndex - 1);
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex =pageIndex + 1;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPage() {
		pageSize = pageSize == 0 ? 1:pageSize;
		int mode = count % pageSize;
		totalPage = count / pageSize;
		if (mode > 0) {
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
