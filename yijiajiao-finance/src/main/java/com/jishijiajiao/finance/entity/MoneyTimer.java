package com.jishijiajiao.finance.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @decription 教师账户余额
 */
public class MoneyTimer implements Serializable {
	private int id;
	private String openId;
	private double variableMoney;//浮动金额，可能退款
	private double withdrawalCash;//本期可提现金额
	private double totalMoney;//总金额
	private double totalSettleMoney;//收入总金额(包括已提现的和本期可提现的金额)
	private String updateTime;
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
	public double getVariableMoney() {
		return variableMoney;
	}
	public void setVariableMoney(double variableMoney) {
		this.variableMoney = variableMoney;
	}
	public double getWithdrawalCash() {
		return withdrawalCash;
	}
	public void setWithdrawalCash(double withdrawalCash) {
		this.withdrawalCash = withdrawalCash;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public double getTotalSettleMoney() {
		return totalSettleMoney;
	}
	public void setTotalSettleMoney(double totalSettleMoney) {
		this.totalSettleMoney = totalSettleMoney;
	}
	@Override
	public String toString() {
		return "MoneyTimer [id=" + id + ", openId=" + openId
				+ ", variableMoney=" + variableMoney + ", withdrawalCash="
				+ withdrawalCash + ", totalMoney=" + totalMoney
				+ ", totalSettleMoney=" + totalSettleMoney + ", updateTime="
				+ updateTime + "]";
	}
	
}
