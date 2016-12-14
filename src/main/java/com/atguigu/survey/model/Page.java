package com.atguigu.survey.model;

import java.util.List;

/**
 * 带分页的类
 * @author shuai xu 2016年10月18日 下午4:41:51
 */
public class Page<T> {
	private List<T> list;
	private int pageNo;
	private int pageSize = 5;
	private int totalRecordNo;// 总记录数
	private int totalPageNo;// 总页数

	public Page(String pageNoStr, int totalRecordNo) {
		this.totalRecordNo = totalRecordNo;
		this.totalPageNo = totalRecordNo / pageSize
				+ ((totalRecordNo % pageSize == 0) ? 0 : 1);
		this.pageNo = 1;
		try {
			this.pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		if (pageNo > totalPageNo) {
			pageNo = totalPageNo;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
	}

	public List<T> getList() {
		return list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalRecordNo() {
		return totalRecordNo;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public boolean isHasPrev() {
		return pageNo > 1;
	}

	public boolean isHasNext() {
		return pageNo < totalPageNo;
	}

	public int getPrev() {
		return pageNo - 1;
	}

	public int getNext() {
		return pageNo + 1;
	}
}
