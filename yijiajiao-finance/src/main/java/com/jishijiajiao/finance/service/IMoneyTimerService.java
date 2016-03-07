package com.jishijiajiao.finance.service;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.MoneyTimer;

public interface IMoneyTimerService {
	/**
	 * 
	 * @description 添加新用户账户金额 或者更改账户金额
	 * @date 2015-12-25
	 * @return int 0表示成功 1表示失败
	 * @param moneyTimer
	 */
	int saveOrUpdateMoneyTimer(MoneyTimer moneyTimer);

	/**
	 * @description 通过用户openid获取账户金额信息，返回ResultMapper实体
	 * @date 2015-12-25
	 * @return ResultMapper
	 * @param openId
	 */
	ResultMapper getRemainMoney(String openId);

	/**
	 * @description 通过用户openid获取账户金额信息，返回具体金额
	 * @date 2015-12-29
	 * @return Double
	 * @param openId
	 */
	Double getRemainMoneyByOpenId(String openId);

}
