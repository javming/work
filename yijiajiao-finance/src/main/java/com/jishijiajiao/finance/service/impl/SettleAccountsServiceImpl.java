package com.jishijiajiao.finance.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.ISettleAccountsDAO;
import com.jishijiajiao.finance.dao.IMoneyTimerDAO;
import com.jishijiajiao.finance.entity.DailySettleAccounts;
import com.jishijiajiao.finance.entity.MoneyTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.service.ISettleAccountsService;
import com.jishijiajiao.finance.util.Arith;
import com.jishijiajiao.finance.util.Config;
import com.jishijiajiao.finance.util.DateUtil;
@Service
public class SettleAccountsServiceImpl implements
		ISettleAccountsService {
	protected static Logger log = Logger
			.getLogger(SettleAccountsServiceImpl.class);
	private ResultMapper resultBean = new ResultMapper();
	@Autowired
	private ISettleAccountsDAO dailySettleAccountsDAO;
	@Autowired
	private IMoneyTimerDAO moneyTimerDAO;
	@Override
	public ResultMapper addDailySettleAccounts(List<DailySettleAccounts> dailySettleAccounts) {
		try{
			System.out.println("请求数量==="+dailySettleAccounts.size());
			for(DailySettleAccounts dsa: dailySettleAccounts){
				if(dsa.getSaveTime()==null) dsa.setSaveTime(DateUtil.getNowTime());
				MoneyTimer moneyTimer = moneyTimerDAO.queryMoneyTimerByOpenId(dsa.getSellOpenId());
				double settleMoney =Arith.mul(Config.getDouble("percentage"),dsa.getSettleMoney());
				System.out.println("settleMoney=============="+settleMoney);
				if(moneyTimer == null) moneyTimer = new MoneyTimer();
				moneyTimer.setVariableMoney(Arith.sub(moneyTimer.getVariableMoney(),settleMoney));
				moneyTimer.setWithdrawalCash(Arith.add(settleMoney,moneyTimer.getWithdrawalCash()));
				moneyTimer.setTotalSettleMoney(Arith.add(settleMoney,moneyTimer.getTotalSettleMoney()));
				moneyTimer.setTotalMoney(Arith.add(moneyTimer.getVariableMoney(),moneyTimer.getWithdrawalCash()));
				moneyTimer.setUpdateTime(DateUtil.getNowTime());
				dsa.setSettleMoney(settleMoney);
				dailySettleAccountsDAO.insertDailySettleAccounts(dsa);
				if(moneyTimer.getOpenId() == null){
					moneyTimer.setOpenId(dsa.getSellOpenId());
					moneyTimerDAO.saveMoneyTimer(moneyTimer);
				}else{
					moneyTimerDAO.updateMoneyTimer(moneyTimer);
				}
				System.out.println("修改的数据："+moneyTimer);
			}
			this.resultBean.setSucResult("保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}

}
