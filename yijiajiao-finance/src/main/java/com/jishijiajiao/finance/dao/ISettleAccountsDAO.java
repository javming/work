package com.jishijiajiao.finance.dao;

import com.jishijiajiao.finance.entity.DailySettleAccounts;
import com.jishijiajiao.finance.entity.query.QueryParam;

public interface ISettleAccountsDAO {
	void insertDailySettleAccounts(DailySettleAccounts dailySettleAccounts);
	double queryTeacherIncomeForLastMonth(String openId);
	double queryTeacherIncomeForThisMonth(String openId);
	double queryTeacherIncomeByMonth(QueryParam qp);
}
