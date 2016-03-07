package com.jishijiajiao.finance.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.IAnswerTimerDAO;
import com.jishijiajiao.finance.entity.AnswerTimer;

@Repository("answerTimer")
public class AnswerTimerDAOImpl implements IAnswerTimerDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Override
	public AnswerTimer queryAnswerTimerByOpenId(String openId) {
		return sessionTemplate
				.selectOne(
						"com.jishijiajiao.finance.dao.IAnswerTimerDAO.queryAnswerTimerByOpenId",
						openId);
	}

	@Override
	public void saveAnswerTimer(AnswerTimer answerTimer) {
		sessionTemplate.insert(
				"com.jishijiajiao.finance.dao.IAnswerTimerDAO.saveAnswerTimer",
				answerTimer);
	}

	@Override
	public void updateAnswerTimer(AnswerTimer answerTimer) {
		sessionTemplate
				.update("com.jishijiajiao.finance.dao.IAnswerTimerDAO.updateAnswerTimer",
						answerTimer);
	}

}
