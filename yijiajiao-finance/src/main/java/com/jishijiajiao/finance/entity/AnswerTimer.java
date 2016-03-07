package com.jishijiajiao.finance.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 学生答疑计费器
 */
public class AnswerTimer implements Serializable {
	private int id;
	private String openId;
	private double remainTime = 0;// 答疑所剩时长
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

	public double getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(double remainTime) {
		this.remainTime = remainTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "AnswerTimer [id=" + id + ", openId=" + openId + ", remainTime="
				+ remainTime + ", updateTime=" + updateTime + "]";
	}

}
