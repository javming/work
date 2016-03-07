package com.jishijiajiao.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.BatchPayment;

@Service
public interface IBatchPayService {
	/**
	 *@description 批量支付
	 *@date 2016-2-2
	 *@return ResultMapper
	 *@return
	 */
	ResultMapper batchPay(List<BatchPayDetail> batchPayDetails);
	
	/**
	 *@description 通过批次号 查询批量转账信息
	 *@date 2016-2-18
	 *@return BatchPayment
	 *@param batch_no
	 */
	BatchPayment getBatchPaymentByBatch_no(String batch_no);

	void modBatchPayment(BatchPayment batchPayment);
}
