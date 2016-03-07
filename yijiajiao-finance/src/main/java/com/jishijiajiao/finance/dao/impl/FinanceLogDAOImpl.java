package com.jishijiajiao.finance.dao.impl;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.IFinanceLogDAO;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.query.FinanceLogQuery;

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

}
