package com.jishijiajiao.finance.service;

import java.util.List;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.DailySettleAccounts;

public interface ISettleAccountsService {
	ResultMapper addDailySettleAccounts(List<DailySettleAccounts> dailySettleAccounts);
}
