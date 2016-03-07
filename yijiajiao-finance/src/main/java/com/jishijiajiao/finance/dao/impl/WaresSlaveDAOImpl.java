package com.jishijiajiao.finance.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.IWaresSlaveDAO;
import com.jishijiajiao.finance.entity.WaresSlave;
@Repository
public class WaresSlaveDAOImpl implements IWaresSlaveDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public void insertWaresSlave(WaresSlave waresSlave) {
		sessionTemplate.insert("com.jishijiajiao.finance.dao.IWaresSlaveDAO.insertWaresSlave", waresSlave);
	}

	@Override
	public void updateWaresSlave(WaresSlave waresSlave) {
		// TODO Auto-generated method stub

	}

}
