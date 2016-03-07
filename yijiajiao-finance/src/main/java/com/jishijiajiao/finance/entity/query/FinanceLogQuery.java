package com.jishijiajiao.finance.entity.query;

import com.jishijiajiao.finance.entity.FinanceLog;

public class FinanceLogQuery extends FinanceLog{
	//查询2011的数据：select * from  表 where year(date)='2011';
	//	查找月份为12的数据：select * from 表 where month(date)='12';
	//查找天数为本年第二天的数据：select * from 表 where dayofyear(date)='2';
	private int month;//标识要查询几月份的数据
	private int begin;
	private int size;
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
