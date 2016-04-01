package com.jishijiajiao.finance.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IFinanceLogDAO;
import com.jishijiajiao.finance.dao.IMoneyTimerDAO;
import com.jishijiajiao.finance.dao.ISettleAccountsDAO;
import com.jishijiajiao.finance.entity.MoneyTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.service.IMoneyTimerService;
import com.jishijiajiao.finance.util.DateUtil;

@Service("moneyTimerService")
public class IMoneyTimerServiceImpl implements IMoneyTimerService {
	private static Logger log = Logger.getLogger(IMoneyTimerServiceImpl.class);
	@Autowired
	private IMoneyTimerDAO moneyTimerDAO;
	@Autowired
	private IFinanceLogDAO financeLogDAO;
	@Autowired
	private ISettleAccountsDAO settleAccountsDAO;
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
			}else{
				moneyTimer.setUpdateTime(DateUtil.StringPattern(moneyTimer.getUpdateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS_MS, DateUtil.YYYY_MM_DD_HH_MM_SS));
			}
			DateUtil du = new DateUtil();
			//获得本月第一天
			Date beginMonth = du.calcBeginMonth(DateUtil.getNowTime());
			//获得本月最后一天
			Date endMonth = du.calcEndMonth(DateUtil.getNowTime());
			//获得本月11号日期  11 00:00:00
			Date ten = DateUtil.addDays(beginMonth, 10);
			Date now = new Date();
			double income1=0.0;
			double income2=0.0; 
			if(now.getTime()>= beginMonth.getTime() && now.getTime() < ten.getTime()){
				//计算本期账户余额,如果查询日期在本月1号到11号0点之前，本期余额为上个月收入
				try {
					income1 = financeLogDAO.queryTeacherIncomeForLastMonth(openId);
				} catch (Exception e) {
					income1=0.0;
				}
				try {
					income2= settleAccountsDAO.queryTeacherIncomeForLastMonth(openId);
				} catch (Exception e) {
					income2=0.0;
				}
			}else {
				//计算本期账户余额,如果查询日期在本月11号0点到月末之前，本期余额为本月收入
				try {
					income1 = financeLogDAO.queryTeacherIncomeForThisMonth(openId);
				} catch (Exception e) {
					income1=0.0;
				}
				try {
					income2= settleAccountsDAO.queryTeacherIncomeForThisMonth(openId);
				} catch (Exception e) {
					income2=0.0;
				}
			}
			moneyTimer.setWithdrawalCash(income1+income2);
			log.info("查询数据为 moneyTimer=="+moneyTimer);
			this.resultMapper.setSucResult(moneyTimer);
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
