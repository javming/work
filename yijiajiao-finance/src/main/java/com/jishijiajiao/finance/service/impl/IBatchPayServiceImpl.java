package com.jishijiajiao.finance.service.impl;

import java.math.MathContext;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jishijiajiao.alipay.util.AlipayCore;
import com.jishijiajiao.alipay.util.AlipaySubmit;
import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IBatchPayDetailDAO;
import com.jishijiajiao.finance.dao.IBatchPaymentDAO;
import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.BatchPayment;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.service.IBatchPayService;
import com.jishijiajiao.finance.util.Config;
import com.jishijiajiao.finance.util.DateUtil;
import com.jishijiajiao.finance.util.RandomUtil;
import com.jishijiajiao.alipay.config.AlipayConfig;
import com.jishijiajiao.alipay.sign.RSA;
@Service
public class IBatchPayServiceImpl implements IBatchPayService {
	private ResultMapper resultBean = new ResultMapper();
	private Logger log = Logger.getLogger(IBatchPayServiceImpl.class);
	@Autowired
	private IBatchPaymentDAO batchPaymentDAO;
	@Autowired
	private IBatchPayDetailDAO batchPayDetailDAO;
	@Override
	public ResultMapper batchPay(List<BatchPayDetail> batchPayDetails) {
		try {
			if(batchPayDetails.size()>1000){
				this.resultBean.setFailMsg(SystemStatus.BATCH_NUM);
				return this.resultBean;
			}
			StringBuilder detail_data = new StringBuilder();
			String batch_no = DateUtil.getNowTime("yyyyMMddhhmmss")+RandomUtil.getRandomCharNum(6);//获得唯一批次号
			double batch_fee = 0;
			int running = 1000;
			for(BatchPayDetail bpd :batchPayDetails){
				bpd.setBatch_no(batch_no);
				bpd.setRunning_no(DateUtil.getNowTime("MMddhhmmss")+running);//生成流水号
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
			batchPayment.setPay_account_no(sParaTemp.get("email"));
			batchPayment.setPay_user_name(sParaTemp.get("account_name"));
			batchPaymentDAO.insertBatchPayment(batchPayment);
			for(BatchPayDetail bpd :batchPayDetails){
				batchPayDetailDAO.insertBatchPayDetail(bpd);
			}
			
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
	public void modBatchPayment(BatchPayment batchPayment) {
		BatchPayment batc = batchPaymentDAO.selectBatchPaymentByBatch_no(batchPayment.getBatch_no());
		if(batc == null){
			batchPaymentDAO.insertBatchPayment(batchPayment);
		}else{
			batchPaymentDAO.updateBatchPayment(batchPayment);
		}
	}
	
	
}
