package com.jishijiajiao.finance.controller;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.AnswerTimer;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.MoneyTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.entity.WaresSlave;
import com.jishijiajiao.finance.entity.query.FinanceLogQuery;
import com.jishijiajiao.finance.service.IAnswerTimerService;
import com.jishijiajiao.finance.service.IFinanceLogService;
import com.jishijiajiao.finance.service.IMoneyTimerService;
import com.jishijiajiao.finance.service.impl.IAnswerTimerServiceImpl;
import com.jishijiajiao.finance.service.impl.IFinanceLogServiceImpl;
import com.jishijiajiao.finance.service.impl.IMoneyTimerServiceImpl;
import com.jishijiajiao.finance.util.Config;
import com.jishijiajiao.finance.util.HttpClient;

@Controller
@Path("/FinanceLog")
public class FinanceLogController {
	private static Logger log = Logger.getLogger(FinanceLogController.class);
	private ResultMapper resultBean = new ResultMapper();
	@Autowired
	private IFinanceLogService financeLogService;
	@Autowired
	private IAnswerTimerService answerTimerService;
	@Autowired
	private IMoneyTimerService moneyTimerService;

	/**
	 *@description 订单记录接口
	 *@date 2016-1-11
	 *@return ResultMapper
	 *@param financeLog
	 */
	@POST
	@Path("/orderFinanceLog")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper orderFinanceLog(FinanceLog financeLog) {
		return financeLogService.addOrderFinanceLog(financeLog);
	}
	/**
	 *@description 每日结算记录接口
	 *@date 2016-1-11
	 *@return ResultMapper
	 *@param financeLogs
	 
	@POST
	@Path("/dailySettleAccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper dailySettleAccount(List<FinanceLog> financeLogs) {
		return financeLogService.insertDailySettleAccount(financeLogs);
	}*/
	/**
	 *@description 退款记录接口
	 *@date 2016-1-13
	 *@return ResultMapper
	 *@param financeLog
	 */
	@POST
	@Path("/refundMoney")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper refundMoney(FinanceLog financeLog) {
		return financeLogService.addRefundMoneyLog(financeLog);
	}
	/**
	 *@description 消费答疑记录接口
	 *@date 2016-1-11
	 *@return ResultMapper
	 *@param financeLog
	 *@return
	 */
	@POST
	@Path("/consumeAnswerTime")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper consumeAnswerTime(FinanceLog financeLog) {
		return financeLogService.addConsumeAnswerTimeLog(financeLog);
	}
	/**
	 *@description 提现记录接口
	 *@date 2016-1-11
	 *@return ResultMapper
	 *@param financeLog
	
	@POST
	@Path("/withdrawsCash")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper withdrawsCash(FinanceLog financeLog) {
		return financeLogService.addWithdrawsCashLog(financeLog);
	}
 */
	/**
	 * @description 获取个人账户所剩答疑时长
	 * @date 2015-12-28
	 * @return ResultMapper
	 * @param openId
	 */
	@GET
	@Path("/getRemainAnswerTime")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper getRemainAnswerTime(@QueryParam("openId") String openId) {
		log.info("参数 ：openid=" + openId);
		if(openId==null || "".equals(openId)){
			this.resultBean.setFailMsg(SystemStatus.PARAM_NOT_NULL);
			return this.resultBean;
		}
		return answerTimerService.getRemainAnswerTime(openId);
	}

	/**
	 * @description 获取个人用户所剩金额
	 * @date 2015-12-28
	 * @return ResultMapper
	 * @param openId
	 */
	@GET
	@Path("/getRemainMoney")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper getRemainMoney(@QueryParam("openId") String openId) {
		log.info("参数：openId==" + openId);
		if(openId==null || "".equals(openId)){
			this.resultBean.setFailMsg(SystemStatus.PARAM_NOT_NULL);
			return this.resultBean;
		}
		return moneyTimerService.getRemainMoney(openId);
	}
	
	/**
	 *@description 教师账户批量提现，用于每月15日发工资
	 *@date 2016-3-17
	 *@return ResultMapper
	 *@param financeLogs
	 *@return
	 */
	@POST
	@Path("/monthSettleAccounts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper monthSettleAccounts(List<FinanceLog> financeLogs){
		return financeLogService.addMonthSettleAccounts(financeLogs);
	}
	
	/**
	@GET
	@Path("/personalBills")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper PersonalBills(@QueryParam("openId") String openId,@QueryParam("month") int month,
			@QueryParam("pageNum") int pageNum,@QueryParam("pageSize") int pageSize){
		System.out.println("参数为 ：openId="+openId+",month="+month+"pageNum="+pageNum+"pageSize="+pageSize);
		return financeLogService.getPersonalBills(openId,month,pageNum,pageSize);
		
	}*/
	
	@POST
	@Path("/queryTeacherIncomeLastMonth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper queryTeacherIncomeLastMonth(List<String> openIds){
		try {
			List<FinanceLog> list = financeLogService.queryTeacherIncomeLastMonth(openIds);
			this.resultBean.setSucResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}
	
	/**
	 *@description		通过手机号和时间获取教师交易详情列表
	 *@date 2016-4-1
	 *@return ResultMapper
	 *@param flq
	 *@return
	 */
	@POST
	@Path("/personalFinance")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper personalFinanceLogs(FinanceLogQuery flq){
		return financeLogService.queryTeacherTradeLogs(flq);
	}
}
