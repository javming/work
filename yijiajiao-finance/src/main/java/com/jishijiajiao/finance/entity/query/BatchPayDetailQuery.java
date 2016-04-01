package com.jishijiajiao.finance.entity.query;

import com.jishijiajiao.finance.entity.BatchPayDetail;

public class BatchPayDetailQuery extends BatchPayDetail{
	private int pageNum;
	private int pageSize;
	private int startRow;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		// 计算开始行
		if (pageNum != 0) {
			this.startRow = (pageNum - 1) * pageSize;
			this.pageNum = pageNum;
		} else {
			this.pageNum = pageNum;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		// 计算开始行
		if (pageSize != 0) {
			this.startRow = (pageNum - 1) * pageSize;
			this.pageSize = pageSize;
		} else {
			this.pageSize = pageSize;
		}
	}
	
	
}
