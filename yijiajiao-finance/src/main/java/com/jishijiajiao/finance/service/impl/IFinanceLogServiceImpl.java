package com.jishijiajiao.finance.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jishijiajiao.finance.bean.FinanceBean;
import com.jishijiajiao.finance.bean.Page;
import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IAnswerTimerDAO;
import com.jishijiajiao.finance.dao.IFinanceLogDAO;
import com.jishijiajiao.finance.dao.IMoneyTimerDAO;
import com.jishijiajiao.finance.dao.ISettleAccountsDAO;
import com.jishijiajiao.finance.dao.IWaresSlaveDAO;
import com.jishijiajiao.finance.entity.AnswerTimer;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.MoneyTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.entity.WaresSlave;
import com.jishijiajiao.finance.entity.query.FinanceLogQuery;
import com.jishijiajiao.finance.service.IFinanceLogService;
import com.jishijiajiao.finance.util.Arith;
import com.jishijiajiao.finance.util.Config;
import com.jishijiajiao.finance.util.DateUtil;
import com.jishijiajiao.finance.util.HttpClient;

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
	@Autowired
	private ISettleAccountsDAO settleAccountsDAO;
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
				financeLog.setTeacherIncome(Arith.mul(financeLog.getTotalPrice(),percentage));//double值的精确运算
				financeLog.setSystemIncome(Arith.mul(financeLog.getTotalPrice(),Arith.sub(1,percentage)));
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
					moneyTimer.setTotalSettleMoney(financeLog.getWithdrawalCashChange());
					moneyTimer.setTotalMoney(moneyTimer.getVariableMoney()
							+ moneyTimer.getWithdrawalCash());
					moneyTimerDAO.saveMoneyTimer(moneyTimer);
					System.out.println("该用户无记录，进行记录添加" + moneyTimer);
				} else {
					moneyTimer.setUpdateTime(DateUtil.getNowTime());
					moneyTimer.setVariableMoney(Arith.add(moneyTimer.getVariableMoney(),financeLog.getVariableMoneyChange()));
					moneyTimer.setWithdrawalCash(Arith.add(moneyTimer.getWithdrawalCash(),financeLog.getWithdrawalCashChange()));
					moneyTimer.setTotalMoney(Arith.add(moneyTimer.getVariableMoney(),moneyTimer.getWithdrawalCash()));
					moneyTimer.setTotalSettleMoney(Arith.add(moneyTimer.getTotalSettleMoney(),financeLog.getWithdrawalCashChange()));
					moneyTimerDAO.updateMoneyTimer(moneyTimer);
					System.out.println("金额修改后：variableMoney="
							+ moneyTimer.getVariableMoney()
							+ " withdrawalCash="
							+ moneyTimer.getWithdrawalCash() + "totalMoney="
							+ moneyTimer.getTotalMoney()+"TotalSettleMoney="+moneyTimer.getTotalSettleMoney());
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
					double totalTime =Arith.add(answerTimer.getRemainTime(),financeLog.getTimeChange()); 
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
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}

	@Override
	@Transactional
	public ResultMapper addRefundMoneyLog(FinanceLog financeLog) {
		try {
			financeLog.setFinanceLogsType(2);// 表示退款记录
			financeLog.setTradeType(1);//退款方式 支付宝
			if(financeLog.getTradeTime() == null)financeLog.setTradeTime(DateUtil.getNowTime());
			FinanceLog financeLog2 = financeLogDAO.queryFinanceLogByConditions(financeLog);
			if(financeLog2 != null){
				this.resultBean.setFailMsg(SystemStatus.LOG_AREADY_BEEN);
				return this.resultBean;
			}
			FinanceLog financeLog3 = financeLogDAO.queryFinanceLogByOrderNum(financeLog.getOrderNumber());
			System.out.println("financeLog3=="+financeLog3);
			financeLog.setCurriculumType(financeLog3.getCurriculumType());
			System.out.println("currculumType=="+financeLog.getCurriculumType());
			double totalPrice = financeLog.getTotalPrice();
			financeLog.setVariableMoneyChange(Arith.sub(0, Arith.mul(totalPrice, Config.getDouble("percentage"))));
			financeLog.setTeacherIncome(financeLog.getVariableMoneyChange());
			financeLog.setSystemIncome(Arith.sub(0, Arith.mul(totalPrice,Arith.sub(1,Config.getDouble("percentage")))));
			MoneyTimer moneyTimer = moneyTimerDAO.queryMoneyTimerByOpenId(financeLog.getSellOpenId());
			if (moneyTimer == null){
				moneyTimer = new MoneyTimer();
				moneyTimer.setOpenId(financeLog.getSellOpenId());
				moneyTimer.setVariableMoney(Arith.add(moneyTimer.getVariableMoney(),financeLog.getVariableMoneyChange()));// 日志里存的负值所以直接加
				moneyTimer.setTotalMoney(Arith.add(moneyTimer.getTotalMoney(),financeLog.getVariableMoneyChange()));
				moneyTimer.setUpdateTime(DateUtil.getNowTime());
				moneyTimerDAO.saveMoneyTimer(moneyTimer);
			}else{
				moneyTimer.setVariableMoney(Arith.add(moneyTimer.getVariableMoney(),financeLog.getVariableMoneyChange()));// 日志里存的负值所以直接加
				moneyTimer.setTotalMoney(Arith.add(moneyTimer.getTotalMoney(),financeLog.getVariableMoneyChange()));
				moneyTimer.setUpdateTime(DateUtil.getNowTime());
				moneyTimerDAO.updateMoneyTimer(moneyTimer);
			}
			log.info("保存数据：moneyTimer===="+moneyTimer);
			log.info("保存数据：financeLog===="+financeLog);

			financeLogDAO.saveFinanceLog(financeLog);
			for (WaresSlave ws : financeLog.getWaresSlaves()) {
				ws.setOrderNumber(financeLog.getOrderNumber());
				ws.setCreateTime(DateUtil.getNowTime());
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
			answerTimer.setRemainTime(Arith.sub(remainTime,financeLog.getTimeChange()));
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
	public ResultMapper addMonthSettleAccounts(List<FinanceLog> financeLogs) {
		try {
			for(FinanceLog flg:financeLogs){
				flg.setTradeTime(DateUtil.getNowTime()); 
				flg.setFinanceLogsType(4);
				
				financeLogDAO.saveFinanceLog(flg);
				MoneyTimer moneyTimer = moneyTimerDAO.queryMoneyTimerByOpenId(flg.getSellOpenId());
				double cash = moneyTimer.getWithdrawalCash();
				if(cash<flg.getTeacherOutput()){
					this.resultBean.setFailMsg(SystemStatus.REMAINMONEY_NOT_ENOUGH);
					return this.resultBean;
				}
				moneyTimer.setWithdrawalCash(cash-flg.getTeacherOutput());
				moneyTimer.setTotalSettleMoney(moneyTimer.getTotalSettleMoney()+flg.getTeacherOutput());
				moneyTimer.setTotalMoney(moneyTimer.getVariableMoney()+moneyTimer.getWithdrawalCash());
				moneyTimerDAO.updateMoneyTimer(moneyTimer);
			}
			this.resultBean.setSucResult("保存成功！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}
	
	
	public List<FinanceLog> queryTeacherIncomeLastMonth(List<String> openIds){
		List<FinanceLog> financeLogs = new ArrayList<FinanceLog>();
		for(String openId: openIds){
			double income1;
			double income2; 
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
			System.out.println("teacher_income========="+(income1+income2));
			FinanceLog flg = new FinanceLog();
			flg.setOpenId(openId);
			flg.setTeacherIncome(Arith.add(income1, income2));
			financeLogs.add(flg);
		}
		return financeLogs;
		
	}

	@Override
	public ResultMapper queryTeacherTradeLogs(FinanceLogQuery flq) {
		try {
			if(flq.getPhoneNum()==null){
				this.resultBean.setFailMsg(SystemStatus.PHONE_NOT_NULL);
				return this.resultBean;
			}else if(flq.getStartTime()==null || flq.getEndTime()==null){
				this.resultBean.setFailMsg(SystemStatus.DATE_NOT_NULL);
				return this.resultBean;
			}
			//通过手机号查询openId
			System.out.println("通过手机号查询openId-->"+Config.getString("user.server")+Config.getString("open_id.url")+flq.getPhoneNum());
			String httpRest = HttpClient.httpRest(Config.getString("user.server"), Config.getString("open_id.url")+flq.getPhoneNum(), null, null, "GET");
			System.out.println("httpRest=="+httpRest);
			JSONObject json = JSONObject.fromObject(httpRest);
			Object result = json.get("result");
			System.out.println("result============"+result);
			if(result.equals(null)){
				this.resultBean.setFailMsg(SystemStatus.PHONE_NOT_HAS);
				return this.resultBean;
			}
			JSONObject obj = (JSONObject) json.get("result");
			String openId = obj.getString("userOpenId");
			System.out.println("查询结果  openId=="+openId);
			flq.setSellOpenId(openId);
			Date endTime = DateUtil.stringTodate(flq.getEndTime(), "yyyy-MM-dd");
			flq.setEndTime(DateUtil.dateToString(DateUtil.addDays(endTime, 1), "yyyy-MM-dd"));
			System.out.println("参数==》sellOpenId="+flq.getSellOpenId()+",startTime="+flq.getStartTime()+",endTime="+flq.getEndTime());
			
			List<FinanceLog> conditions = financeLogDAO.queryTeacherTradeLogs(flq);
			List<FinanceBean> financeBeans = new ArrayList<FinanceBean>();
			double totalIncome=0.0;//收入总和
			double totalRefund=0.0;//退款总和
			double totalTeacherIncome=0.0; //教师部分总和
			double totalSytemIncome=0.0;//平台总和
			for(FinanceLog fl : conditions){
				FinanceBean financeBean = new FinanceBean();
				financeBean.setOpenId(fl.getSellOpenId());
				financeBean.setTeacherPhoneNum(fl.getTeacherPhoneNum());
				financeBean.setStudentPhoneNum(fl.getStudentPhoneNum());
				financeBean.setTradeTime(DateUtil.StringPattern(fl.getTradeTime(), DateUtil.YYYY_MM_DD_HH_MM_SS_MS, DateUtil.YYYY年MM月DD_HH_MM_SS));
				financeBean.setOrderNumber(fl.getOrderNumber());
				financeBean.setTrade_no(fl.getTrade_no());
				financeBean.setCurriculumName(fl.getCurriculumName());
				financeBean.setTotalPrice(fl.getTotalPrice());
				if(fl.getTradeType() != null){
					switch (fl.getTradeType()) {
					case 1:financeBean.setTradeType("支付宝");
						break;
					case 2:financeBean.setTradeType("微信");
						break;
					case 3:financeBean.setTradeType("银行卡");
						break;
					default:
						break;
					}
				}
				if(fl.getCurriculumType() != null){
					switch (fl.getCurriculumType()) {
					case 0:financeBean.setCurriculumType("直播课");
						break;
					case 1:financeBean.setCurriculumType("一对一");
						break;
					case 2:financeBean.setCurriculumType("视频课");
					default:
						break;
					}
				}
				financeBean.setTeacherIncome(fl.getTeacherIncome());
				financeBean.setSystemIncome(fl.getSystemIncome());
				if(fl.getFinanceLogsType()==1){
					totalIncome=Arith.add(totalIncome, fl.getTotalPrice());
				}else if(fl.getFinanceLogsType()==2){
					totalRefund=Arith.sub(totalRefund, fl.getTotalPrice());
				}
				financeBean.setFinanceLogsType(fl.getFinanceLogsType());
				totalTeacherIncome=Arith.add(totalTeacherIncome, fl.getTeacherIncome());
				totalSytemIncome=Arith.add(totalSytemIncome, fl.getSystemIncome());
				System.out.println("totalIncome="+totalIncome+",totalRefund="+totalRefund+",totalTeacherIncome="+totalTeacherIncome+",totalSystemIncome="+totalSytemIncome);
				financeBeans.add(financeBean);
			}
			if(flq.getRay()==0){
				Collections.sort(financeBeans, new sortByDateDesc0());
			}else{
				Collections.sort(financeBeans, new sortByDateDesc1());
			}
			FinanceBean financeBean = new FinanceBean();
			System.out.println("totalIncome="+totalIncome+",totalRefund="+totalRefund+",totalTeacherIncome="+totalTeacherIncome+",totalSystemIncome="+totalSytemIncome);
			financeBean.setTotalIncome(totalIncome);
			financeBean.setTotalRefund(totalRefund);
			financeBean.setTotalTeacherIncome(totalTeacherIncome);
			financeBean.setTotalSytemIncome(totalSytemIncome);
			financeBeans.add(financeBean);
			this.resultBean.setSucResult(financeBeans);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;

	}
}
//时间降序
class sortByDateDesc0 implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		FinanceBean fb1 = (FinanceBean) o1;
		FinanceBean fb2 = (FinanceBean) o2;
		return DateUtil.compare_date0(fb1.getTradeTime(), fb2.getTradeTime());
	}
}
//时间升序
class sortByDateDesc1 implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		FinanceBean fb1 = (FinanceBean) o1;
		FinanceBean fb2 = (FinanceBean) o2;
		return DateUtil.compare_date1(fb1.getTradeTime(), fb2.getTradeTime());
	}
}
