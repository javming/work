package com.yijiajiao.customer.model;

import java.util.Date;

/**
 * 投诉举报 表结构
 * 
 * @author TF1
 * 
 */
public class Complain {

	private Long id;// 主键

	private String order_number;// 订单号

	private String complainant_number;// 投诉人账号

	private String the_complainant;// 被投诉人

	private String order_info;// 订单详情

	private Date trading_time;// 交易时间(创建时间)

	private String new_trading_time;// 交易时间(创建时间)

	private String trading_person;// 操作人(创建人)

	private String complainant_reason;// 投诉原因

	private String operator_person;// 更新人

	private String new_operator_date;// 更新日期

	private Date operator_date;// 更新日期

	private String status; // 状态

	private String reply;// 回复内容

	public Complain() {

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

	public String getOrder_info() {
		return order_info;
	}

	public void setOrder_info(String order_info) {
		this.order_info = order_info;
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

}
