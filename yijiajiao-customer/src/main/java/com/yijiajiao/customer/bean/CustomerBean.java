package com.yijiajiao.customer.bean;

import java.util.Date;

/**
 * 封装查询条件
 * 
 * @author Administrator
 * 
 */
public class CustomerBean {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 姓名
	 */
	private String user_name;

	/**
	 * 咨询内容
	 */
	private String counseling;

	/**
	 * 回复内容
	 */
	private String reply;

	/**
	 * 姓名 user_id
	 */
	private String open_id;

	/**
	 * 咨询时间(创建时间)
	 */
	private Date counseling_time;

	private String new_counseling_time;

	/**
	 * 操作人(更新人)
	 */
	private String operator;
	/**
	 * 操作时间(更新时间)
	 */
	private Date operator_date;

	private String new_operator_date;
	/**
	 * 操作时间 (更新时间)
	 */

	/**
	 * 处理结果
	 */
	private String result;

	/**
	 * 状态（已回复和未回复）
	 */
	private String status; // 0是未回复，1是已回复。

	/**
	 * 身份
	 */
	private String role_id;// 0学生1老师2家长
	/**
	 * 身份
	 */
	private String role_name;

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

	public CustomerBean() {

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCounseling() {
		return counseling;
	}

	public void setCounseling(String counseling) {
		this.counseling = counseling;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public Date getCounseling_time() {
		return counseling_time;
	}

	public void setCounseling_time(Date counseling_time) {
		this.counseling_time = counseling_time;
	}

	public String getNew_counseling_time() {
		return new_counseling_time;
	}

	public void setNew_counseling_time(String new_counseling_time) {
		this.new_counseling_time = new_counseling_time;
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

	public String getNew_operator_date() {
		return new_operator_date;
	}

	public void setNew_operator_date(String new_operator_date) {
		this.new_operator_date = new_operator_date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

}
