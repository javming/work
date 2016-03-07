package com.jishijiajiao.finance.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jishijiajiao.finance.dao.IBatchPaymentDAO;
import com.jishijiajiao.finance.entity.BatchPayment;
@Component
public class IBatchPaymentDAOImpl implements IBatchPaymentDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public void insertBatchPayment(BatchPayment batchPayment) {
		sessionTemplate.insert("com.jishijiajiao.finance.dao.IBatchPaymentDAO.insertBatchPayment", batchPayment);
	}
	@Override
	public void updateBatchPayment(BatchPayment batchPayment) {
		sessionTemplate.update("com.jishijiajiao.finance.dao.IBatchPaymentDAO.updateBatchPayment", batchPayment);
	}
	@Override
	public BatchPayment selectBatchPaymentByBatch_no(String batch_no) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IBatchPaymentDAO.selectBatchPaymentByBatch_no", batch_no);
	}

}
