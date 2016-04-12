package com.jishijiajiao.finance.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.ISettleAccountsDAO;
import com.jishijiajiao.finance.entity.DailySettleAccounts;
import com.jishijiajiao.finance.entity.query.QueryParam;
@Repository
public class SettleAccountsDAOImpl implements ISettleAccountsDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public void insertDailySettleAccounts(
			DailySettleAccounts dailySettleAccounts) {
		sessionTemplate.insert("com.jishijiajiao.finance.dao.IDailySettleAccountsDAO.insertDailySettleAccounts", dailySettleAccounts);
	}
	@Override
	public double queryTeacherIncomeForLastMonth(String openId) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IDailySettleAccountsDAO.queryTeacherIncomeForLastMonth", openId);
	}
	@Override
	public double queryTeacherIncomeForThisMonth(String openId) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IDailySettleAccountsDAO.queryTeacherIncomeForThisMonth", openId);
	}
	@Override
	public double queryTeacherIncomeByMonth(QueryParam qp) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IDailySettleAccountsDAO.queryTeacherIncomeByMonth",qp);
	}
	
	
}
