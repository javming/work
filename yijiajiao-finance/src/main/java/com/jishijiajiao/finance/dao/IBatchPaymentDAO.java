package com.jishijiajiao.finance.dao;

import com.jishijiajiao.finance.entity.BatchPayment;

public interface IBatchPaymentDAO {
	void insertBatchPayment(BatchPayment batchPayment);
	void updateBatchPayment(BatchPayment batchPayment);
	BatchPayment selectBatchPaymentByBatch_no(String batch_no);
}
