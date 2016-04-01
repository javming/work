package com.jishijiajiao.finance.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jishijiajiao.finance.dao.IBatchPayDetailDAO;
import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.query.BatchPayDetailQuery;
@Component
public class IBatchPayDetailDAOImpl implements IBatchPayDetailDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public void insertBatchPayDetail(BatchPayDetail batchPayDetail) {
		sessionTemplate.insert("com.jishijiajiao.finance.dao.IBatchPayDetailDAO.insertBatchPayDetail", batchPayDetail);
	}
	@Override
	public void updateIsDispose(String batch_no) {
		sessionTemplate.update("com.jishijiajiao.finance.dao.IBatchPayDetailDAO.updateIsDispose", batch_no);
	}
	@Override
	public List<BatchPayDetail> queryByBatch_no(String batch_no) {
		return sessionTemplate.selectList("com.jishijiajiao.finance.dao.IBatchPayDetailDAO.queryByBatch_no", batch_no);
	}
	@Override
	public List<BatchPayDetail> getBatchPayDetailsByConditions(
			BatchPayDetailQuery batchPayDetailQuery) {
		return sessionTemplate.selectList("com.jishijiajiao.finance.dao.IBatchPayDetailDAO.getBatchPayDetailsByConditions", batchPayDetailQuery);
	}
	@Override
	public int getCountByConditions(BatchPayDetailQuery batchPayDetailQuery) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IBatchPayDetailDAO.getCountByConditions", batchPayDetailQuery);
	}
	@Override
	public void updateNumber(BatchPayDetail bpd) {
		sessionTemplate.update("com.jishijiajiao.finance.dao.IBatchPayDetailDAO.updateNumber", bpd);
	}

}
