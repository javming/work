package com.jishijiajiao.finance.dao;

import java.util.List;

import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.query.FinanceLogQuery;

/**
 * @description IFinanceLogDAO
 * @author zhaoming
 * @date 2016-1-5
 */
public interface IFinanceLogDAO {
	/**
	 * @description 根据id查询单条记录
	 * @date 2015-12-29
	 * @return FinanceLog
	 * @param id
	 */
	FinanceLog queryById(int id);

	/**
	 * @description 添加财务记录
	 * @date 2015-12-29
	 * @return void
	 * @param financeLog
	 */
	void saveFinanceLog(FinanceLog financeLog);

	/**
	 * @description 通过openId查询个人财务记录
	 * @date 2015-12-29
	 * @return List<FinanceLog>
	 * @param openId
	 */
	List<FinanceLog> queryFinanceLogsByOpenId(String openId);
	
	/**
	 *@description 通过条件查询财务记录，返回单条记录
	 *@date 2016-1-27
	 *@return FinanceLog
	 *@param financeLog
	 */
	FinanceLog queryFinanceLogByConditions(FinanceLog financeLog);

	/**
	 * @description 通过条件查询财务记录，返回多条记录
	 * @date 2016-1-5
	 * @return List<FinanceLog>
	 * @param FinanceLogQuery
	 */
	List<FinanceLog> queryFinanceLogsByConditions(FinanceLogQuery financeLog);

}
