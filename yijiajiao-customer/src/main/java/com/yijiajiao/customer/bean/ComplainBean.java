package com.yijiajiao.customer.bean;

import java.util.Date;

/**
 * 投诉举报 表结构
 * 
 * @author TF1
 * 
 */
public class ComplainBean {

	private Long id;// 主键

	private String order_number;// 订单号

	private String complainant_number;// 投诉人账号

	private String the_complainant;// 被投诉人

	private String order;// 订单详情

	private Date trading_time;// 交易时间(创建时间)

	private String new_trading_time;// 交易时间(创建时间)

	private String trading_person;// 操作人(创建人)

	private String complainant_reason;// 投诉原因

	private String operator_person;// 更新人

	private String new_operator_date;// 更新日期

	private Date operator_date;// 更新日期

	private String status; // 状态

	private String reply;// 回复内容

	// 页号
	private Integer pageNum = 0;
	// 每页显示记录数
	private Integer pageSize = 0;
	// 开始行
	private Integer startRow = 0;

	public ComplainBean() {

	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		// 计算开始行
		if (pageNum != 0) {
			this.startRow = (pageNum - 1) * pageSize;
			this.pageNum = pageNum;
		} else {
			this.pageNum = pageNum;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		// 计算开始行
		if (pageSize != 0) {
			this.startRow = (pageNum - 1) * pageSize;
			this.pageSize = pageSize;
		} else {
			this.pageSize = pageSize;
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getComplainant_number() {
		return complainant_number;
	}

	public void setComplainant_number(String complainant_number) {
		this.complainant_number = complainant_number;
	}

	public String getThe_complainant() {
		return the_complainant;
	}

	public void setThe_complainant(String the_complainant) {
		this.the_complainant = the_complainant;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Date getTrading_time() {
		return trading_time;
	}

	public void setTrading_time(Date trading_time) {
		this.trading_time = trading_time;
	}

	public String getNew_trading_time() {
		return new_trading_time;
	}

	public void setNew_trading_time(String new_trading_time) {
		this.new_trading_time = new_trading_time;
	}

	public String getTrading_person() {
		return trading_person;
	}

	public void setTrading_person(String trading_person) {
		this.trading_person = trading_person;
	}

	public String getComplainant_reason() {
		return complainant_reason;
	}

	public void setComplainant_reason(String complainant_reason) {
		this.complainant_reason = complainant_reason;
	}

	public String getOperator_person() {
		return operator_person;
	}

	public void setOperator_person(String operator_person) {
		this.operator_person = operator_person;
	}

	public String getNew_operator_date() {
		return new_operator_date;
	}

	public void setNew_operator_date(String new_operator_date) {
		this.new_operator_date = new_operator_date;
	}

	public Date getOperator_date() {
		return operator_date;
	}

	public void setOperator_date(Date operator_date) {
		this.operator_date = operator_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	@Override
	public String toString() {
		return "ComplainBean [id=" + id + ", order_number=" + order_number
				+ ", complainant_number=" + complainant_number
				+ ", the_complainant=" + the_complainant + ", order=" + order
				+ ", trading_time=" + trading_time + ", new_trading_time="
				+ new_trading_time + ", trading_person=" + trading_person
				+ ", complainant_reason=" + complainant_reason
				+ ", operator_person=" + operator_person
				+ ", new_operator_date=" + new_operator_date
				+ ", operator_date=" + operator_date + ", status=" + status
				+ ", reply=" + reply + ", pageNum=" + pageNum + ", pageSize="
				+ pageSize + ", startRow=" + startRow + "]";
	}
	

}
