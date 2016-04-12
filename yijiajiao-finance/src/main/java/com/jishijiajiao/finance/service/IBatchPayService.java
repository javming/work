package com.jishijiajiao.finance.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.BatchPayment;
import com.jishijiajiao.finance.entity.query.BatchPayDetailQuery;
import com.jishijiajiao.finance.page.Pagination;

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
	/**
	 *@description 按条件分页查询上个月教师收入列表
	 *@date 2016-3-21
	 *@return void
	 * @throws ParseException 
	 */
	Pagination querySettleInfo(BatchPayDetailQuery batchPayDetailQuery) throws ParseException;
	/**
	 *@description	将表batch0_pay_detail字段is_dispose改为1 表示已处理
	 *								同时修改money_timer中可提现金额的值
	 *@date 2016-3-22
	 *@return void
	 */
	void modBatchPayDetailAndMoneyTiemer(String batch_no);
	
	/**
	 *@description	计算所有教师上月收入并保存
	 *@date 2016-4-1
	 *@return int
	 *@return
	 *@throws ParseException
	 */
	int getAndSaveSalary() throws ParseException;
	/**
	 *@description  通过日期几手机号获得教师工资对账单（分页查询）
	 *@date 2016-4-6
	 *@return ResultMapper
	 *@param batchPayDetailQuery
	 *@return
	 */
	ResultMapper querySettleInfoByDate(BatchPayDetailQuery batchPayDetailQuery);
	/**
	 *@description 通过日期几手机号获得教师工资对账单(不分页，查所有列表)
	 *@date 2016-4-11
	 *@return ResultMapper
	 *@param queryDate
	 *@return
	 */
	ResultMapper querySettleInfoByDate(String queryDate);
}
