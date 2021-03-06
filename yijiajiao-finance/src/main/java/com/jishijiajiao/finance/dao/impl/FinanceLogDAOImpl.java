package com.jishijiajiao.finance.dao.impl;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.IFinanceLogDAO;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.query.FinanceLogQuery;
import com.jishijiajiao.finance.entity.query.QueryParam;

@Repository("financeLogDAO")
public class FinanceLogDAOImpl implements IFinanceLogDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Override
	public FinanceLog queryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFinanceLog(FinanceLog financeLog) {
		sessionTemplate.insert(
				"com.jishijiajiao.finance.dao.IFinanceLogDAO.saveFinanceLog",
				financeLog);
	}

	@Override
	public List<FinanceLog> queryFinanceLogsByOpenId(String openId) {
		return null;
	}

	@Override
	public List<FinanceLog> queryFinanceLogsByConditions(FinanceLogQuery financeLog) {
		return sessionTemplate.selectList(
				"com.jishijiajiao.finance.dao.IFinanceLogDAO.queryFinanceLogsByConditions", 
				financeLog);
	}

	@Override
	public FinanceLog queryFinanceLogByConditions(FinanceLog financeLog) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IFinanceLogDAO.queryFinanceLogByConditions", financeLog);
	}

	@Override
	public double queryTeacherIncomeForLastMonth(String openId) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IFinanceLogDAO.queryTeacherIncomeForLastMonth", openId);
	}

	@Override
	public List<FinanceLog> queryTeacherTradeLogs(FinanceLogQuery flq) {
		return sessionTemplate.selectList("com.jishijiajiao.finance.dao.IFinanceLogDAO.queryTeacherTradeLogs", flq);
	}

	@Override
	public double queryTeacherIncomeForThisMonth(String openId) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IFinanceLogDAO.queryTeacherIncomeForThisMonth", openId);
	}

	@Override
	public FinanceLog queryFinanceLogByOrderNum(String orderNumber) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IFinanceLogDAO.queryFinanceLogByOrderNum", orderNumber);
	}

	@Override
	public double queryTeacherIncomeByMonth(QueryParam qp) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IFinanceLogDAO.queryTeacherIncomeByMonth", qp);
	}

}
