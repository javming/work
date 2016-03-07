package com.jishijiajiao.finance.dao;

import com.jishijiajiao.finance.entity.MoneyTimer;

public interface IMoneyTimerDAO {
	/**
	 * 
	 * @description
	 * @date 2015-12-25
	 * @return MoneyTimer
	 * @param openId
	 */
	MoneyTimer queryMoneyTimerByOpenId(String openId);

	/**
	 * @description
	 * @date 2015-12-25
	 * @return void
	 * @param moneyTimer
	 */
	void saveMoneyTimer(MoneyTimer moneyTimer);

	/**
	 * @description
	 * @date 2015-12-25
	 * @return void
	 * @param moneyTimer
	 */
	void updateMoneyTimer(MoneyTimer moneyTimer);
}
