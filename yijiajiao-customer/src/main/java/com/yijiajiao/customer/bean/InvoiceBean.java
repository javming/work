package com.yijiajiao.customer.bean;

import java.util.Date;

public class InvoiceBean {

	private Long id;

	private String order_number;

	private String contacts_name;

	private String contacts_phone;

	private String contacts_address;

	private String complainant_reason;

	private String operator;

	private Date operator_date;

	private String tracking_number;

	private String status;

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
	 * 分页附加字段
	 * 
	 * @return
	 */

	// 页号
	private Integer pageNum = 0;
	// 每页显示记录数
	private Integer pageSize = 0;
	// 开始行
	private Integer startRow = 0;

	// private long total; //总记录数
	// private int pageNum; // 第几页 当前页
	// private int pageSize; // 每页记录数
	// private int pages; // 总页数
	//
	public InvoiceBean() {

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

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
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

}
