package com.jishijiajiao.finance.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IMoneyTimerDAO;
import com.jishijiajiao.finance.entity.MoneyTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.service.IMoneyTimerService;
import com.jishijiajiao.finance.util.DateUtil;

@Service("moneyTimerService")
public class IMoneyTimerServiceImpl implements IMoneyTimerService {
	private static Logger log = Logger.getLogger(IMoneyTimerServiceImpl.class);
	@Autowired
	private IMoneyTimerDAO moneyTimerDAO;
	private ResultMapper resultMapper = new ResultMapper();

	@Override
	@Transactional
	public int saveOrUpdateMoneyTimer(MoneyTimer moneyTimer) {
		try {
			/*
			 * MoneyTimer timer =
			 * moneyTimerDAO.queryMoneyTimerByOpenId(moneyTimer.getOpenId());
			 * if(timer != null){
			 * 
			 * if( moneyTimer.getIsIncome()== 1){//表示收入
			 * moneyTimer.setRemainMoney
			 * (timer.getRemainMoney()+moneyTimer.getMoneyChange()); }else{//转出
			 * moneyTimer
			 * .setRemainMoney(timer.getRemainMoney()-moneyTimer.getMoneyChange
			 * ()); } moneyTimerDAO.updateMoneyTimer(moneyTimer); }else{
			 * moneyTimer.setRemainMoney(moneyTimer.getMoneyChange());
			 * moneyTimerDAO.saveMoneyTimer(moneyTimer); }
			 */
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	public ResultMapper getRemainMoney(String openId) {
		try {
			MoneyTimer moneyTimer = moneyTimerDAO
					.queryMoneyTimerByOpenId(openId);
			if (moneyTimer == null) {
				moneyTimer=new MoneyTimer();
				moneyTimer.setOpenId(openId);
				moneyTimer.setId(0);
				moneyTimer.setUpdateTime(DateUtil.getNowTime());
			}
			this.resultMapper.setSucResult(moneyTimer);
			log.info("   resultMapper ==" + this.resultMapper);
			return this.resultMapper;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultMapper.setFailMsg(SystemStatus.SERVER_ERROR);
			return this.resultMapper;
		}

	}

	@Override
	public Double getRemainMoneyByOpenId(String openId) {
		return null;
	}

}
