package com.jishijiajiao.finance.service.impl;

import java.util.Date;
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
import com.jishijiajiao.finance.util.Config;
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
			for(DailySettleAccounts dsa: dailySettleAccounts){
				if(dsa.getSaveTime()==null) dsa.setSaveTime(new Date());
				MoneyTimer moneyTimer = moneyTimerDAO.queryMoneyTimerByOpenId(dsa.getSellOpenId());
				if(moneyTimer==null){
					log.info("该老师金额账户无信息 openid==="+dsa.getSellOpenId());
					continue;
				}
				if(moneyTimer.getVariableMoney()<dsa.getSettleMoney()){
					log.info("该用户可结算金额不足    openId=="+dsa.getSellOpenId());
					continue;
				}
				double settleMoney = Config.getDouble("percentage")*dsa.getSettleMoney();
				System.out.println("settleMoney=============="+settleMoney);
				moneyTimer.setVariableMoney(moneyTimer.getVariableMoney()-settleMoney);
				moneyTimer.setWithdrawalCash(moneyTimer.getWithdrawalCash()+settleMoney);
				moneyTimer.setTotalSettleMoney(moneyTimer.getTotalSettleMoney()+settleMoney);
				moneyTimer.setTotalMoney(moneyTimer.getVariableMoney()+moneyTimer.getWithdrawalCash());
				dsa.setSettleMoney(settleMoney);
				dailySettleAccountsDAO.insertDailySettleAccounts(dsa);
				moneyTimerDAO.updateMoneyTimer(moneyTimer);
			}
			this.resultBean.setSucResult("保存成功！");
			return this.resultBean;
		}catch(Exception e){
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.UNAUTHORIZED);
			return this.resultBean;
		}
	}

}
