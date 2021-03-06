package com.jishijiajiao.finance.service;

import java.util.List;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.query.FinanceLogQuery;

public interface IFinanceLogService {
	/**
	 * @description 添加订单财务记录
	 * @date 2015-12-29
	 * @return ResultMapper
	 * @param financeLog
	 */
	ResultMapper addOrderFinanceLog(FinanceLog financeLog);

	/**
	 * @description 每日结算，主要涉及可变化金额到可提现金额的转换
	 * @date 2016-1-8
	 * @return ResultMapper
	 * @param financeLogs
	 
	ResultMapper insertDailySettleAccount(List<FinanceLog> financeLogs);*/

	/**
	 * @description 退款
	 * @date 2016-1-8
	 * @return ResultMapper
	 * @param financeLog
	 */
	ResultMapper addRefundMoneyLog(FinanceLog financeLog);

	/**
	 * @description 消费答疑
	 * @date 2016-1-8
	 * @return ResultMapper
	 * @param financeLog
	 */
	ResultMapper addConsumeAnswerTimeLog(FinanceLog financeLog);

	/**
	 * @description 账户金额提现
	 * @date 2016-1-11
	 * @return ResultMapper
	 * @param financeLog
	 * @return
	 */
	//ResultMapper addWithdrawsCashLog(FinanceLog financeLog);
	
	/**
	 *@description 获取个人账单
	 *@date 2016-1-14
	 *@return ResultMapper
	 *@param openId,month,pageSize,pageNum 
	 */
	//ResultMapper getPersonalBills(String openId, int month, int pageNum, int pageSize);
	/**
	 *@description 添加教师提现记录（批量发工资）
	 *@date 2016-3-16
	 *@return ResultMapper
	 *@param financeLog
	 *@return
	 */
	ResultMapper addMonthSettleAccounts(List<FinanceLog> financeLogs);
	
	List<FinanceLog> queryTeacherIncomeLastMonth(List<String> openIds);
	/**
	 *@description	通过条件查询教师本月的交易记录
	 *@date 2016-3-30
	 *@return FinanceLog
	 *@param flq
	 *@return
	 */
	ResultMapper queryTeacherTradeLogs(FinanceLogQuery flq);
}
