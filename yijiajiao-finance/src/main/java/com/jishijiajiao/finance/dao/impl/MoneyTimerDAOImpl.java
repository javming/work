package com.jishijiajiao.finance.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.IMoneyTimerDAO;
import com.jishijiajiao.finance.entity.MoneyTimer;

@Repository("moneyTimerDAO")
public class MoneyTimerDAOImpl implements IMoneyTimerDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Override
	public MoneyTimer queryMoneyTimerByOpenId(String openId) {
		return sessionTemplate
				.selectOne(
						"com.jishijiajiao.finance.dao.IMoneyTimerDAO.queryMoneyTimerByOpenId",
						openId);
	}

	@Override
	public void saveMoneyTimer(MoneyTimer moneyTimer) {
		sessionTemplate.insert(
				"com.jishijiajiao.finance.dao.IMoneyTimerDAO.saveMoneyTimer",
				moneyTimer);
	}

	@Override
	public void updateMoneyTimer(MoneyTimer moneyTimer) {
		sessionTemplate.update(
				"com.jishijiajiao.finance.dao.IMoneyTimerDAO.updateMoneyTimer",
				moneyTimer);
	}

}
