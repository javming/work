package com.jishijiajiao.finance.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.IDailySettleAccountsDAO;
import com.jishijiajiao.finance.entity.DailySettleAccounts;
@Repository
public class DailySettleAccountsDAOImpl implements IDailySettleAccountsDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public void insertDailySettleAccounts(
			DailySettleAccounts dailySettleAccounts) {
		sessionTemplate.insert("com.jishijiajiao.finance.dao.IDailySettleAccountsDAO.insertDailySettleAccounts", dailySettleAccounts);
	}

}
