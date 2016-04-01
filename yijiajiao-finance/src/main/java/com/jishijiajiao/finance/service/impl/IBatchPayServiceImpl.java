package com.jishijiajiao.finance.service.impl;

import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jishijiajiao.alipay.util.AlipayCore;
import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IBatchPayDetailDAO;
import com.jishijiajiao.finance.dao.IBatchPaymentDAO;
import com.jishijiajiao.finance.dao.IFinanceLogDAO;
import com.jishijiajiao.finance.dao.IMoneyTimerDAO;
import com.jishijiajiao.finance.dao.ISettleAccountsDAO;
import com.jishijiajiao.finance.dao.IUserAlipayDAO;
import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.BatchPayment;
import com.jishijiajiao.finance.entity.MoneyTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.entity.UserAlipay;
import com.jishijiajiao.finance.entity.query.BatchPayDetailQuery;
import com.jishijiajiao.finance.page.Pagination;
import com.jishijiajiao.finance.service.IBatchPayService;
import com.jishijiajiao.finance.util.Config;
import com.jishijiajiao.finance.util.DateUtil;
import com.jishijiajiao.finance.util.HttpClient;
import com.jishijiajiao.finance.util.RandomUtil;
import com.jishijiajiao.alipay.config.AlipayConfig;
import com.jishijiajiao.alipay.sign.RSA;
@Service("batchPayService")
public class IBatchPayServiceImpl implements IBatchPayService {
	private ResultMapper resultBean = new ResultMapper();
	private Logger log = Logger.getLogger(IBatchPayServiceImpl.class);
	@Autowired
	private IBatchPaymentDAO batchPaymentDAO;
	@Autowired
	private IBatchPayDetailDAO batchPayDetailDAO;
	@Autowired
	private IMoneyTimerDAO moneyTimerDAO;
	@Autowired
	private IUserAlipayDAO userAlipayDAO;
	@Autowired
	private ISettleAccountsDAO settleAccountsDAO;
	@Autowired
	private IFinanceLogDAO financeLogDAO;
	@Override
	@Transactional
	public ResultMapper batchPay(List<BatchPayDetail> batchPayDetails) {
		try {
			if(batchPayDetails.size()>1000){
				this.resultBean.setFailMsg(SystemStatus.BATCH_NUM);
				return this.resultBean;
			}
			StringBuilder detail_data = new StringBuilder();
			String batch_no = DateUtil.getNowTime("yyyyMMddhhmmss")+RandomUtil.getRandomCharNum(2);//获得唯一批次号
			double batch_fee = 0;
			int running = 1001;
			for(BatchPayDetail bpd :batchPayDetails){
				bpd.setBatch_no(batch_no);
				bpd.setRunning_no(batch_no+running);//生成流水号
				System.out.println("bpd2==="+bpd.getPayString());
				boolean is_insert=bpd.getRunning_no() ==null || "".equals(bpd.getRunning_no())||bpd.getProceeds_account() == null || "".equals(bpd.getProceeds_account())
						|| bpd.getProceeds_name()==null || "".equals(bpd.getProceeds_name())||bpd.getRemark()==null || "".equals(bpd.getRemark());
				if(is_insert){
					this.resultBean.setFailMsg(SystemStatus.BATCH_DETAIL_ERROR);
					return this.resultBean;
				}
				if(detail_data.length()>0){
					detail_data.append("|"+bpd.getPayString());		
				}else{
					detail_data.append(bpd.getPayString());	
				}
				batch_fee+=bpd.getProceeds_fee();
				running++;
			}
			
			System.out.println("detail_data="+detail_data.toString());
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("account_name",AlipayConfig.account_name);
			sParaTemp.put("batch_fee", batch_fee+"");
			sParaTemp.put("batch_no",batch_no);
			sParaTemp.put("batch_num",batchPayDetails.size()+"");
			sParaTemp.put("detail_data", detail_data.toString());
			sParaTemp.put("email", AlipayConfig.email);
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("pay_date", DateUtil.getNowTime("yyyyMMdd"));
			sParaTemp.put("_input_charset",AlipayConfig.input_charset);
			sParaTemp.put("notify_url", Config.getString("notify_url"));
			sParaTemp.put("service",Config.getString("service"));
			String content = AlipayCore.createLinkString(sParaTemp);
			System.out.println("content=="+content);
			String sign_content = RSA.sign(content, AlipayConfig.private_key, AlipayConfig.input_charset);
			System.out.println("sign_content=="+sign_content);
			String result = Config.getString("web_pay_url")+content+"&sign="+URLEncoder.encode( sign_content )+"&sign_type="+AlipayConfig.sign_type;
			log.info("result=="+result);
			this.resultBean.setSucResult(result);
			
			BatchPayment batchPayment = new BatchPayment();
			batchPayment.setBatch_no(batch_no);
			batchPayment.setBatch_fee(batch_fee);
			batchPayment.setBatch_num(batchPayDetails.size());
			batchPayment.setPay_date(DateUtil.getNowTime());
			batchPayment.setEmail(sParaTemp.get("email"));
			batchPayment.setPay_user_name(sParaTemp.get("account_name"));
			batchPaymentDAO.insertBatchPayment(batchPayment);
			log.info("batchPayment保存成功！"+batchPayment);
			DateUtil du = new DateUtil();
			Date beginMonth = du.calcBeginMonth(DateUtil.getNowTime());
			//上个月第一天
			String lasb = DateUtil.calcDate(beginMonth,DateUtil.YYYY_MM_DD, 2, -1);
			for(BatchPayDetail bpd :batchPayDetails){
				bpd.setStart_time(lasb);
				batchPayDetailDAO.updateNumber(bpd);
			}
			log.info("本批次发放工资的人数=="+batchPayDetails.size());
		} catch (Exception e) {
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean; 
	}
	@Override
	public BatchPayment getBatchPaymentByBatch_no(String batch_no) {
		BatchPayment batchPayment = batchPaymentDAO.selectBatchPaymentByBatch_no(batch_no);
		return batchPayment;
	}
	@Override
	@Transactional
	public void modBatchPayment(BatchPayment batchPayment) {
		BatchPayment batc = batchPaymentDAO.selectBatchPaymentByBatch_no(batchPayment.getBatch_no());
		if(batc == null){
			batchPaymentDAO.insertBatchPayment(batchPayment);
		}else{
			batc.setFail_details(batchPayment.getFail_details());
			batc.setIs_dispose(batchPayment.getIs_dispose());
			batc.setNotify_id(batchPayment.getNotify_id());
			batc.setNotify_time(batchPayment.getNotify_time());
			batc.setNotify_type(batchPayment.getNotify_type());
			batc.setPay_user_id(batchPayment.getPay_user_id());
			batc.setPay_account_no(batchPayment.getPay_account_no());
			batc.setSuccess_details(batchPayment.getSuccess_details());
			batchPaymentDAO.updateBatchPayment(batc);
		}
	}
	@Override
	@Transactional
	public Pagination querySettleInfo(BatchPayDetailQuery batchPayDetailQuery) throws ParseException{
		DateUtil du = new DateUtil();
		Date beginMonth = du.calcBeginMonth(DateUtil.getNowTime());
		//上个月第一天
		String lasb = DateUtil.calcDate(beginMonth,DateUtil.YYYY_MM_DD, 2, -1);
		batchPayDetailQuery.setStart_time(lasb);
		List<BatchPayDetail> batchPayDetails=batchPayDetailDAO.getBatchPayDetailsByConditions(batchPayDetailQuery);
		int totalCount =batchPayDetailDAO.getCountByConditions(batchPayDetailQuery);
		Pagination pagination = new Pagination(batchPayDetailQuery.getPageNum(), batchPayDetailQuery.getPageSize(), totalCount, batchPayDetails);
		return pagination;
	}
	@Override
	@Transactional
	public void modBatchPayDetailAndMoneyTiemer(String batch_no) {
		batchPayDetailDAO.updateIsDispose(batch_no);//标记为1 表示已转账成功,同时标记收款成功时间
		List<BatchPayDetail>  batchPayDetails=batchPayDetailDAO.queryByBatch_no(batch_no);
		for(BatchPayDetail bpd :batchPayDetails){
			MoneyTimer moneyTimer = moneyTimerDAO.queryMoneyTimerByOpenId(bpd.getOpen_id());
			log.info("moneyTimer=="+moneyTimer);
			moneyTimer.setWithdrawalCash(moneyTimer.getWithdrawalCash()-bpd.getProceeds_fee());
			moneyTimer.setTotalMoney(moneyTimer.getVariableMoney()+moneyTimer.getWithdrawalCash());
			moneyTimer.setUpdateTime(DateUtil.getNowTime());
			moneyTimerDAO.updateMoneyTimer(moneyTimer);
		}
	}
	@Override
	public int getAndSaveSalary() throws ParseException {
		List<BatchPayDetail> batchPayDetails = new ArrayList<BatchPayDetail>();
		//获得所有拥有收入的教师的openid
		List<String> openIds = moneyTimerDAO.queryAllOpenIds();
		//通过openid获得教师绑定的支付宝账号
		List<UserAlipay> userAlipays = userAlipayDAO.queryUserAlipayByOpenIds(openIds);
		//获得上个月的第一天和最后一天
		DateUtil du = new DateUtil();
		Date beginMonth = du.calcBeginMonth(DateUtil.getNowTime());
		//上个月第一天
		String lasb = DateUtil.calcDate(beginMonth,DateUtil.YYYY_MM_DD, 2, -1);
		//上个月最后天
		String lase = DateUtil.calcDate(beginMonth, DateUtil.YYYY_MM_DD, 5, -1);
		//组合批量付款数据
		for(UserAlipay ua : userAlipays){
			System.out.println("ua==========="+ua);
			//调用用户信息接口获得教师姓名和手机号
			String httpRest = HttpClient.httpRest(Config.getString("user.server"),Config.getString("userinfo.url")+ua.getOpen_id(), null, null, "GET");
			System.out.println("解析：httpRest=="+httpRest);
			JSONObject json = JSONObject.fromObject(httpRest);
			JSONObject object = (JSONObject) json.get("result");
			String name = object.getString("name");
			String phone = object.getString("username");
			//计算上月收入，包括两部分 直接订单收入和每日课程结算收入
			double income1;//直接订单收入
			double income2; //每日课程结算收入
			try {
				income1 = financeLogDAO.queryTeacherIncomeForLastMonth(ua.getOpen_id());
			} catch (Exception e) {
				income1=0.0;
			}
			try {
				income2= settleAccountsDAO.queryTeacherIncomeForLastMonth(ua.getOpen_id());
			} catch (Exception e) {
				income2=0.0;
			}
			System.out.println("teacher_income========="+(income1+income2));
			if((income1+income2)<=0) continue;
			BatchPayDetail bpd = new BatchPayDetail();
			bpd.setTeacher_name(name);
			bpd.setPhone_num(phone);
			bpd.setStart_time(lasb);
			bpd.setEnd_time(lase);
			bpd.setOpen_id(ua.getOpen_id());
			bpd.setProceeds_account(ua.getAlipay_account());
			bpd.setProceeds_name(ua.getAlipay_name());
			bpd.setProceeds_fee(income1+income2);
			bpd.setRemark("教师收入提现");
			
			batchPayDetailDAO.insertBatchPayDetail(bpd);
			System.out.println("保存成功！");
		}
		return 1;
	}
	
	
}
