package com.jishijiajiao.finance.dao;

import com.jishijiajiao.finance.entity.DailySettleAccounts;

public interface ISettleAccountsDAO {
	void insertDailySettleAccounts(DailySettleAccounts dailySettleAccounts);
	double queryTeacherIncomeForLastMonth(String openId);
	double queryTeacherIncomeForThisMonth(String openId);
}
