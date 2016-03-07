package com.jishijiajiao.finance.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jishijiajiao.finance.bean.Page;
import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IAnswerTimerDAO;
import com.jishijiajiao.finance.dao.IFinanceLogDAO;
import com.jishijiajiao.finance.dao.IMoneyTimerDAO;
import com.jishijiajiao.finance.dao.IWaresSlaveDAO;
import com.jishijiajiao.finance.entity.AnswerTimer;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.MoneyTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.entity.WaresSlave;
import com.jishijiajiao.finance.entity.query.FinanceLogQuery;
import com.jishijiajiao.finance.service.IFinanceLogService;
import com.jishijiajiao.finance.util.Config;
import com.jishijiajiao.finance.util.DateUtil;

@Service("financeLogService")
public class IFinanceLogServiceImpl implements IFinanceLogService {
	protected static Logger log = Logger
			.getLogger(IFinanceLogServiceImpl.class);
	@Autowired
	private IFinanceLogDAO financeLogDAO;
	@Autowired
	private IAnswerTimerDAO answerTimerDAO;
	@Autowired
	private IMoneyTimerDAO moneyTimerDAO;
	@Autowired
	private IWaresSlaveDAO waresSlaveDAO;
	private ResultMapper resultBean = new ResultMapper();

	@Override
	@Transactional
	public ResultMapper addOrderFinanceLog(FinanceLog financeLog) {
		try {
			// 通用属性赋值
			financeLog.setFinanceLogsType(1);// 表示订单记录
			if(financeLog.getTradeTime()==null)financeLog.setTradeTime(DateUtil.getNowTime());
			FinanceLog financeLog2 = financeLogDAO.queryFinanceLogByConditions(financeLog);
			if(financeLog2 != null ){//防止重复添加
				this.resultBean.setFailMsg(SystemStatus.LOG_AREADY_BEEN);
				return this.resultBean;
			}
			switch (financeLog.getCommodityType()) {
			case 1:// 课程
				double percentage = Config.getDouble("percentage");
				financeLog.setTeacherIncome(financeLog.getTotalPrice()
						* percentage);
				financeLog.setSystemIncome(financeLog.getTotalPrice()
						* (1 - percentage));
				boolean ifRefund = false;// 判断该订单是否涉及到退款
				if (financeLog.getCurriculumType() != null
						&& financeLog.getCurriculumType() == 0)
					ifRefund = true;
				if (financeLog.getCurriculumType() == 1
						&& financeLog.getTeachMode() != null
						&& financeLog.getTeachMode() == 0)
					ifRefund = true;
				if (ifRefund) {
					financeLog.setVariableMoneyChange(financeLog
							.getTeacherIncome());
				} else {
					financeLog.setWithdrawalCashChange(financeLog
							.getTeacherIncome());
				}
				// 对金额账户表的修改
				MoneyTimer moneyTimer = moneyTimerDAO
						.queryMoneyTimerByOpenId(financeLog.getSellOpenId());
				if (moneyTimer == null) {
					moneyTimer = new MoneyTimer();
					System.out.println(moneyTimer);
					moneyTimer.setOpenId(financeLog.getSellOpenId());
					moneyTimer.setUpdateTime(DateUtil.getNowTime());
					moneyTimer.setVariableMoney(financeLog
							.getVariableMoneyChange());
					moneyTimer.setWithdrawalCash(financeLog
							.getWithdrawalCashChange());
					moneyTimer.setTotalMoney(moneyTimer.getVariableMoney()
							+ moneyTimer.getWithdrawalCash());
					moneyTimerDAO.saveMoneyTimer(moneyTimer);
					System.out.println("该用户无记录，进行记录添加" + moneyTimer);
				} else {
					moneyTimer.setUpdateTime(DateUtil.getNowTime());
					moneyTimer.setVariableMoney(moneyTimer.getVariableMoney()
							+ financeLog.getVariableMoneyChange());
					moneyTimer.setWithdrawalCash(moneyTimer.getWithdrawalCash()
							+ financeLog.getWithdrawalCashChange());
					moneyTimer.setTotalMoney(moneyTimer.getVariableMoney()
							+ moneyTimer.getWithdrawalCash());
					moneyTimerDAO.updateMoneyTimer(moneyTimer);
					System.out.println("金额修改后：variableMoney="
							+ moneyTimer.getVariableMoney()
							+ " withdrawalCash="
							+ moneyTimer.getWithdrawalCash() + "totalMoney="
							+ moneyTimer.getTotalMoney());
				}
				break;
			case 2:// 答疑
				AnswerTimer answerTimer = answerTimerDAO
						.queryAnswerTimerByOpenId(financeLog.getOpenId());
				if (answerTimer == null) {
					answerTimer = new AnswerTimer();
					answerTimer.setOpenId(financeLog.getOpenId());
					answerTimer.setRemainTime(financeLog.getTimeChange());
					answerTimer.setUpdateTime(DateUtil.getNowTime());
					answerTimerDAO.saveAnswerTimer(answerTimer);
					System.out.println("该用户无记录，进行记录添加" + answerTimer);
				} else {
					double totalTime = answerTimer.getRemainTime()
							+ financeLog.getTimeChange();
					answerTimer.setRemainTime(totalTime);
					answerTimer.setUpdateTime(DateUtil.getNowTime());
					answerTimerDAO.updateAnswerTimer(answerTimer);
					System.out.println("余额修改后：" + totalTime);
				}
				financeLog.setSystemIncome(financeLog.getTotalPrice());//答疑收入全部计入平台收入
				break;
			case 3:// 密卷

				break;
			case 4:// 诊断

				break;
			default:
				this.resultBean.setFailMsg(SystemStatus.COMMODITYTYPE_NOT_NULL);
				return this.resultBean;
			}

			financeLogDAO.saveFinanceLog(financeLog);// 保存财务记录
			log.info("保存的财务记录数据==" + financeLog);
			this.resultBean.setSucResult("保存成功！");
			return this.resultBean;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.UNAUTHORIZED);
			return this.resultBean;
		}
	}

/*	@Override
	@Transactional
	public ResultMapper insertDailySettleAccount(List<FinanceLog> financeLogs) {
		try {
			for (FinanceLog f : financeLogs) {
				MoneyTimer moneyTimer = moneyTimerDAO.queryMoneyTimerByOpenId(f
						.getSellOpenId());
				if (moneyTimer == null) {
					System.out.println("该账户没有金额记录 教师openid="
							+ f.getSellOpenId());
					continue;
				}
				if (moneyTimer.getVariableMoney() < f.getTotalPrice()) {
					System.out.println("该账户可变金额小于划拨金额 取消划拨！教师openid="
							+ f.getSellOpenId() + "variableMoney="
							+ moneyTimer.getVariableMoney()
							+ "variableMoneyChange=-" + f.getTotalPrice());
					continue;
				}
				f.setFinanceLogsType(3);// 表示每日结算记录
				if(f.getTradeTime() == null) f.setTradeTime(DateUtil.getNowTime());
				double moneyChange = f.getTotalPrice()
						* Config.getDouble("percentage");
				f.setVariableMoneyChange(0 - moneyChange);
				f.setWithdrawalCashChange(moneyChange);

				moneyTimer.setUpdateTime(DateUtil.getNowTime());
				moneyTimer.setVariableMoney(moneyTimer.getVariableMoney()
						+ f.getVariableMoneyChange());
				moneyTimer.setWithdrawalCash(moneyTimer.getWithdrawalCash()
						+ f.getWithdrawalCashChange());
				// 每日结算总金额不变
				moneyTimerDAO.updateMoneyTimer(moneyTimer);
				financeLogDAO.saveFinanceLog(f);
			}
			this.resultBean.setSucResult(null);
			return this.resultBean;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.UNAUTHORIZED);
			return this.resultBean;
		}
	}*/

	@Override
	@Transactional
	public ResultMapper addRefundMoneyLog(FinanceLog financeLog) {
		try {
			financeLog.setFinanceLogsType(2);// 表示退款记录
			if(financeLog.getTradeTime() == null)financeLog.setTradeTime(DateUtil.getNowTime());
			FinanceLog financeLog2 = financeLogDAO.queryFinanceLogByConditions(financeLog);
			if(financeLog2 != null){
				this.resultBean.setFailMsg(SystemStatus.LOG_AREADY_BEEN);
				return this.resultBean;
			}
			double totalPrice = financeLog.getTotalPrice();
			financeLog.setVariableMoneyChange(0 - totalPrice
					* Config.getDouble("percentage"));
			MoneyTimer moneyTimer = moneyTimerDAO
					.queryMoneyTimerByOpenId(financeLog.getSellOpenId());
			if (moneyTimer == null){
				//this.resultBean.setFailMsg(SystemStatus.VARIABLEMONEY_NOT_ENOUGH);
				//return this.resultBean;
				moneyTimer = new MoneyTimer();
				moneyTimer.setOpenId(financeLog.getSellOpenId());
				moneyTimer.setVariableMoney(moneyTimer.getVariableMoney()
						+ financeLog.getVariableMoneyChange());// 日志里存的负值所以直接加
				moneyTimer.setTotalMoney(moneyTimer.getTotalMoney()
						+ financeLog.getVariableMoneyChange());
				moneyTimer.setUpdateTime(DateUtil.getNowTime());
				moneyTimerDAO.saveMoneyTimer(moneyTimer);
			}else{
				moneyTimer.setVariableMoney(moneyTimer.getVariableMoney()
						+ financeLog.getVariableMoneyChange());// 日志里存的负值所以直接加
				moneyTimer.setTotalMoney(moneyTimer.getTotalMoney()
						+ financeLog.getVariableMoneyChange());
				moneyTimer.setUpdateTime(DateUtil.getNowTime());
				moneyTimerDAO.updateMoneyTimer(moneyTimer);
			}


			// 占位行 ：调用支付宝接口进行转账若成功 则进行记录保存
			log.info("保存数据：moneyTimer===="+moneyTimer);
			log.info("保存数据：financeLog===="+financeLog);

			financeLogDAO.saveFinanceLog(financeLog);
			for (WaresSlave ws : financeLog.getWaresSlaves()) {
				ws.setOrderNumber(financeLog.getOrderNumber());
				waresSlaveDAO.insertWaresSlave(ws);
			}

			this.resultBean.setSucResult("保存成功！");
			return this.resultBean;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
			return this.resultBean;
		}
	}

	@Override
	@Transactional
	public ResultMapper addConsumeAnswerTimeLog(FinanceLog financeLog) {
		try {
			financeLog.setFinanceLogsType(3);// 表示消费答疑时长记录
			if(financeLog.getTradeTime() == null)financeLog.setTradeTime(DateUtil.getNowTime());

			AnswerTimer answerTimer = answerTimerDAO
					.queryAnswerTimerByOpenId(financeLog.getOpenId());
			if (answerTimer == null){
				this.resultBean.setFailMsg(SystemStatus.HAVE_NOT_ANSWERTIME);
				return this.resultBean;
			}
			double remainTime = answerTimer.getRemainTime();
			if (remainTime < financeLog.getTimeChange()) {
				this.resultBean.setFailMsg(SystemStatus.REMAINTIME_NOT_ENOUGH);
				return this.resultBean;
			}

			answerTimer.setUpdateTime(DateUtil.getNowTime());
			answerTimer.setRemainTime(remainTime - financeLog.getTimeChange());
			answerTimerDAO.updateAnswerTimer(answerTimer);
			financeLogDAO.saveFinanceLog(financeLog);
			this.resultBean.setSucResult("保存成功！");
			return this.resultBean;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
			return this.resultBean;
		}
	}

	@Override
	@Transactional
	public ResultMapper addWithdrawsCashLog(FinanceLog financeLog) {
		try {
			financeLog.setFinanceLogsType(4);// 表示账户提现记录
			if(financeLog.getTradeTime() == null)financeLog.setTradeTime(DateUtil.getNowTime());

			financeLog.setSellOpenId(financeLog.getOpenId());// 这里将教师的openId存入sellOpenId,方便查询
			financeLog.setOpenId(null);

			MoneyTimer moneyTimer = moneyTimerDAO
					.queryMoneyTimerByOpenId(financeLog.getSellOpenId());
			if (moneyTimer == null || moneyTimer.getWithdrawalCash()< financeLog.getTeacherOutput()){
				this.resultBean.setFailMsg(SystemStatus.REMAINMONEY_NOT_ENOUGH);
				return this.resultBean;
			}
			double withdrawalCash = moneyTimer.getWithdrawalCash();
			moneyTimer.setUpdateTime(DateUtil.getNowTime());
			moneyTimer.setWithdrawalCash(withdrawalCash
					- financeLog.getTeacherOutput());
			moneyTimer.setTotalMoney(moneyTimer.getVariableMoney()
					+ moneyTimer.getWithdrawalCash());

			// 占位行 ：调用支付宝接口进行转账若成功 则进行记录保存

			moneyTimerDAO.updateMoneyTimer(moneyTimer);
			financeLogDAO.saveFinanceLog(financeLog);
			System.out.println("提现后余额 : WithdrawalCash="
					+ moneyTimer.getWithdrawalCash() + ",TotalMoney="
					+ moneyTimer.getTotalMoney() + ",VariableMoney="
					+ moneyTimer.getVariableMoney());
			this.resultBean.setSucResult("保存成功！");
			return this.resultBean;
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
			return this.resultBean;
		}
	}

	@Override
	public ResultMapper getPersonalBills(String openId,int month,int pageNum, int pageSize) {
		try{
			if(month== 0) {//如果月份为空，则查询当月的数据
				Calendar cal = Calendar.getInstance();
				month = cal.get(Calendar.MONTH)+1;
			}
			
			FinanceLogQuery financeLog = new FinanceLogQuery();
			financeLog.setOpenId(openId);
			financeLog.setFinanceLogsType(1);
			financeLog.setMonth(month);
			List<FinanceLog> list = financeLogDAO.queryFinanceLogsByConditions(financeLog);
			if(list.size()==0){
				this.resultBean.setFailMsg(SystemStatus.ID_NOT_FOUND);
				return this.resultBean;
			}
			Page<FinanceLog> page = new Page<FinanceLog>();
			page.setPageSize(pageSize);
			page.setPageNum(pageNum);
			page.setTotalCount(list.size());
			page.setPageCount((page.getTotalCount()-1)/pageSize+1);
			financeLog.setBegin(pageSize*(pageNum-1));
			financeLog.setSize(pageSize);
			list = financeLogDAO.queryFinanceLogsByConditions(financeLog);
			page.setResults(list);
			this.resultBean.setSucResult(page);
			return this.resultBean;
		}catch(Exception e){
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
			return this.resultBean;
		}
	}

}