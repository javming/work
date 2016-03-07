package com.jishijiajiao.finance.entity;

public class BatchPayDetail {
	private int id;
	private String running_no;//流水号
	private String proceeds_account;//收款方账号
	private String proceeds_name;//收款账号姓名
	private double proceeds_fee;//付款金额
	private String remark;//备注说明
	private String batch_no;//批次号
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRunning_no() {
		return running_no;
	}

	public void setRunning_no(String running_no) {
		this.running_no = running_no;
	}

	public String getProceeds_account() {
		return proceeds_account;
	}

	public void setProceeds_account(String proceeds_account) {
		this.proceeds_account = proceeds_account;
	}

	public String getProceeds_name() {
		return proceeds_name;
	}

	public void setProceeds_name(String proceeds_name) {
		this.proceeds_name = proceeds_name;
	}
	

	public double getProceeds_fee() {
		return proceeds_fee;
	}

	public void setProceeds_fee(double proceeds_fee) {
		this.proceeds_fee = proceeds_fee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getPayString(){
		return running_no+"^"+proceeds_account+"^"+proceeds_name+"^"+proceeds_fee+"^"+remark;
	}
}
