package com.jishijiajiao.finance.entity;

import java.io.Serializable;
import java.util.Date;

public class DailySettleAccounts implements Serializable {
	private int id;
	private String openId;
	private String sellOpenId;
	private String orderNumber;
	private String commodityId;
	private Integer waresId;
	private Integer slaveId;
	private double settleMoney;
	private Date startTime;
	private Date endTime;
	private String curriculumInfo;
	private Date saveTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Integer getWaresId() {
		return waresId;
	}
	public void setWaresId(Integer waresId) {
		this.waresId = waresId;
	}
	public Integer getSlaveId() {
		return slaveId;
	}
	public void setSlaveId(Integer slaveId) {
		this.slaveId = slaveId;
	}
	public double getSettleMoney() {
		return settleMoney;
	}
	public void setSettleMoney(double settleMoney) {
		this.settleMoney = settleMoney;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCurriculumInfo() {
		return curriculumInfo;
	}
	public void setCurriculumInfo(String curriculumInfo) {
		this.curriculumInfo = curriculumInfo;
	}
	public Date getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}
	@Override
	public String toString() {
		return "DailySettleAccounts [id=" + id + ", openId=" + openId
				+ ", sellOpenId=" + sellOpenId + ", orderNumber=" + orderNumber
				+ ", commodityId=" + commodityId + ", waresId=" + waresId
				+ ", slaveId=" + slaveId + ", settleMoney=" + settleMoney
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", curriculumInfo=" + curriculumInfo + ", saveTime="
				+ saveTime + "]";
	}
	
	
}
