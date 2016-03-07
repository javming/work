package com.jishijiajiao.finance.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jishijiajiao.finance.dao.IBatchPayDetailDAO;
import com.jishijiajiao.finance.entity.BatchPayDetail;
@Component
public class IBatchPayDetailDAOImpl implements IBatchPayDetailDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public void insertBatchPayDetail(BatchPayDetail batchPayDetail) {
		sessionTemplate.insert("com.jishijiajiao.finance.dao.IBatchPayDetailDAO.insertBatchPayDetail", batchPayDetail);
	}

}
