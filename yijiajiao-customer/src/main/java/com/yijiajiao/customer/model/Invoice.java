package com.yijiajiao.customer.model;

import java.util.Date;

/**
 * 发票管理
 * 
 * @author TF1
 *
 */
public class Invoice {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 订单号
	 */
	private String order_number;

	/**
	 * 联系人姓名
	 */
	private String contacts_name;

	private String contacts_phone;

	private String contacts_address;

	private String complainant_reason;

	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 操作时间
	 */
	private Date operator_date;

	private String new_operator_date;

	private String tracking_number;

	/**
	 * 状态
	 */
	private String status;// 0是未回复，1是已回复。

	/**
	 * 快递公司
	 */
	private String express_company;
	/**
	 * 快递单号
	 */
	private String express_number;

	/**
	 * 发票种类
	 */
	private String invoice_type;

	/**
	 * 开票时间
	 */
	private Date invoice_date;

	private String new_invoice_date;

	/**
	 * 抬头种类
	 */
	private String invoice_cnyorpeson;

	/**
	 * 金额
	 */
	private String money;

	/**
	 * 商品名称
	 */
	private String product_name;

	/**
	 * 邮编
	 */
	private String postcode;

	public Invoice() {

	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getContacts_name() {
		return contacts_name;
	}

	public void setContacts_name(String contacts_name) {
		this.contacts_name = contacts_name;
	}

	public String getContacts_phone() {
		return contacts_phone;
	}

	public void setContacts_phone(String contacts_phone) {
		this.contacts_phone = contacts_phone;
	}

	public String getContacts_address() {
		return contacts_address;
	}

	public void setContacts_address(String contacts_address) {
		this.contacts_address = contacts_address;
	}

	public String getComplainant_reason() {
		return complainant_reason;
	}

	public void setComplainant_reason(String complainant_reason) {
		this.complainant_reason = complainant_reason;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperator_date() {
		return operator_date;
	}

	public void setOperator_date(Date operator_date) {
		this.operator_date = operator_date;
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpress_company() {
		return express_company;
	}

	public void setExpress_company(String express_company) {
		this.express_company = express_company;
	}

	public String getExpress_number() {
		return express_number;
	}

	public void setExpress_number(String express_number) {
		this.express_number = express_number;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getInvoice_cnyorpeson() {
		return invoice_cnyorpeson;
	}

	public void setInvoice_cnyorpeson(String invoice_cnyorpeson) {
		this.invoice_cnyorpeson = invoice_cnyorpeson;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNew_operator_date() {
		return new_operator_date;
	}

	public void setNew_operator_date(String new_operator_date) {
		this.new_operator_date = new_operator_date;
	}

	public String getNew_invoice_date() {
		return new_invoice_date;
	}

	public void setNew_invoice_date(String new_invoice_date) {
		this.new_invoice_date = new_invoice_date;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}
