package com.jishijiajiao.finance.dao;

import java.util.List;

import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.query.BatchPayDetailQuery;

public interface IBatchPayDetailDAO {
	void insertBatchPayDetail(BatchPayDetail batchPayDetail);
	/**
	 *@description	将每个教师账户的该批次的付款改为已处理，表示转账成功
	 *@date 2016-3-21
	 *@return void
	 *@param batch_no
	 */
	void updateIsDispose(String batch_no);
	List<BatchPayDetail> queryByBatch_no(String batch_no);
	List<BatchPayDetail> getBatchPayDetailsByConditions(
			BatchPayDetailQuery batchPayDetailQuery);
	int getCountByConditions(BatchPayDetailQuery batchPayDetailQuery);
	/**
	 *@description	增加批次号和流水号
	 *@date 2016-4-1
	 *@return void
	 *@param bpd
	 */
	void updateNumber(BatchPayDetail bpd);
}
