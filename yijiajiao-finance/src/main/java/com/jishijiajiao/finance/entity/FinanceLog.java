package com.jishijiajiao.finance.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinanceLog implements Serializable {
	private Integer id;
	private String openId;
	private String sellOpenId;// 被购买的用户主键(教师提现时的openId也存于此字段)
	private String teacherPhoneNum;//教师手机号
	private String studentPhoneNum;//学生手机号
	private String tradeTime;// 交易时间（包括购买时间，学生答疑消费时间，教师转出金额时间）
	private String orderNumber;// 订单号
	private String trade_no;//支付宝交易号
	private String commodityId;// 商品号
	private Integer commodityType;// 商品类型(1课程 2答疑 3密卷 4诊断)
	private String curriculumName;// 商品名称
	private Integer curriculumType;// 课程类型(0直播课(小班课) 1一对一课 2 视频课)
	private String curriculumInfo;//课程描述
	private Integer teachMode;// 表示一对一教学方式(0线上 1线下)
	private Double totalPrice;// 金额
	private Integer tradeType;// 交易类型 支付宝 微信 网银
	private String accountNumber;// 交易账户 包括支付、退款和提现
	private Double timeChange = 0.0;// 学生购买或者消费的答疑时长
	private int solutionId;//消耗答疑记录的id
	private Double teacherIncome = 0.0;// 教师收入
	private Double systemIncome = 0.0;// 平台收入
	private Double variableMoneyChange = 0.0;// 可变的金额变化值(主要涉及到退款的部分)
	private Double withdrawalCashChange = 0.0;// 可提现的金额变化值
	private Double teacherOutput = 0.0;// 老师转出的金额
	private int financeLogsType;// 财务记录类型(1订单 2退款  3消费答疑 4账户提现(每月15日发上个月工资))
	private Integer openIdIsdel = 0;// 消费者（主要学生）删除 1表示删除 0表示显示
	private Integer sellOpenIdIsdel = 0;// 销售者（主要是教师）是否删除 0否 1删除
	private List<WaresSlave> waresSlaves = new ArrayList<WaresSlave>();//主要用于接收退款时每小节课的信息
	
	public String getTeacherPhoneNum() {
		return teacherPhoneNum;
	}

	public void setTeacherPhoneNum(String teacherPhoneNum) {
		this.teacherPhoneNum = teacherPhoneNum;
	}

	public String getStudentPhoneNum() {
		return studentPhoneNum;
	}

	public void setStudentPhoneNum(String studentPhoneNum) {
		this.studentPhoneNum = studentPhoneNum;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSellOpenId() {
		return sellOpenId;
	}

	public void setSellOpenId(String sellOpenId) {
		this.sellOpenId = sellOpenId;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(Integer commodityType) {
		this.commodityType = commodityType;
	}

	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	public Integer getCurriculumType() {
		return curriculumType;
	}

	public void setCurriculumType(Integer curriculumType) {
		this.curriculumType = curriculumType;
	}

	public String getCurriculumInfo() {
		return curriculumInfo;
	}

	public void setCurriculumInfo(String curriculumInfo) {
		this.curriculumInfo = curriculumInfo;
	}

	public Integer getTeachMode() {
		return teachMode;
	}

	public void setTeachMode(Integer teachMode) {
		this.teachMode = teachMode;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		if (totalPrice == null)
			totalPrice = 0.0;
		this.totalPrice = totalPrice;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Double getTimeChange() {
		return timeChange;
	}

	public void setTimeChange(Double timeChange) {
		if (timeChange == null)
			timeChange = 0.0;
		this.timeChange = timeChange;
	}

	public int getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}

	public Double getTeacherIncome() {
		return teacherIncome;
	}

	public void setTeacherIncome(Double teacherIncome) {
		if (teacherIncome == null)
			teacherIncome = 0.0;
		this.teacherIncome = teacherIncome;
	}

	public Double getSystemIncome() {
		return systemIncome;
	}

	public void setSystemIncome(Double systemIncome) {
		if (systemIncome == null)
			systemIncome = 0.0;
		this.systemIncome = systemIncome;
	}

	public Double getVariableMoneyChange() {
		return variableMoneyChange;
	}

	public void setVariableMoneyChange(Double variableMoneyChange) {
		if (variableMoneyChange == null)
			variableMoneyChange = 0.0;
		this.variableMoneyChange = variableMoneyChange;
	}

	public Double getWithdrawalCashChange() {
		return withdrawalCashChange;
	}

	public void setWithdrawalCashChange(Double withdrawalCashChange) {
		if (withdrawalCashChange == null)
			withdrawalCashChange = 0.0;
		this.withdrawalCashChange = withdrawalCashChange;
	}

	public Double getTeacherOutput() {
		return teacherOutput;
	}

	public void setTeacherOutput(Double teacherOutput) {
		if (teacherOutput == null)
			teacherOutput = 0.0;
		this.teacherOutput = teacherOutput;
	}

	public int getFinanceLogsType() {
		return financeLogsType;
	}

	public void setFinanceLogsType(int financeLogsType) {
		this.financeLogsType = financeLogsType;
	}

	public Integer getOpenIdIsdel() {
		return openIdIsdel;
	}

	public void setOpenIdIsdel(Integer openIdIsdel) {
		if (openIdIsdel == null)
			openIdIsdel = 0;
		this.openIdIsdel = openIdIsdel;
	}

	public Integer getSellOpenIdIsdel() {
		return sellOpenIdIsdel;
	}

	public void setSellOpenIdIsdel(Integer sellOpenIdIsdel) {
		if (sellOpenIdIsdel == null)
			sellOpenIdIsdel = 0;
		this.sellOpenIdIsdel = sellOpenIdIsdel;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<WaresSlave> getWaresSlaves() {
		return waresSlaves;
	}

	public void setWaresSlaves(List<WaresSlave> waresSlaves) {
		this.waresSlaves = waresSlaves;
	}

	@Override
	public String toString() {
		return "FinanceLog [id=" + id + ", openId=" + openId + ", sellOpenId="
				+ sellOpenId + ", tradeTime=" + tradeTime + ", orderNumber="
				+ orderNumber + ", commodityId=" + commodityId
				+ ", commodityType=" + commodityType + ", curriculumName="
				+ curriculumName + ", curriculumType=" + curriculumType
				+ ", teachMode=" + teachMode
				+ ", totalPrice=" + totalPrice + ", tradeType=" + tradeType
				+ ", accountNumber=" + accountNumber + ", timeChange="
				+ timeChange + ", teacherIncome=" + teacherIncome
				+ ", systemIncome=" + systemIncome + ", variableMoneyChange="
				+ variableMoneyChange + ", withdrawalCashChange="
				+ withdrawalCashChange + ", teacherOutput=" + teacherOutput
				+ ", financeLogsType=" + financeLogsType + ", openIdIsdel="
				+ openIdIsdel + ", sellOpenIdIsdel=" + sellOpenIdIsdel
				+ ", waresSlaves=" + waresSlaves + "]";
	}

}
