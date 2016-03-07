package com.jishijiajiao.finance.dao;

import com.jishijiajiao.finance.entity.AnswerTimer;

public interface IAnswerTimerDAO {
	/**
	 * @description 根据openid查询用户答疑信息
	 * @param openId
	 * @return 计时器实体
	 */
	AnswerTimer queryAnswerTimerByOpenId(String openId);

	/**
	 * @description 添加用户答疑计时信息
	 * @param answerTimer
	 * @return void
	 */
	void saveAnswerTimer(AnswerTimer answerTimer);

	/**
	 * @description 修改用户答疑计时信息
	 * @param answerTimer
	 * @return void
	 */
	void updateAnswerTimer(AnswerTimer answerTimer);
}
