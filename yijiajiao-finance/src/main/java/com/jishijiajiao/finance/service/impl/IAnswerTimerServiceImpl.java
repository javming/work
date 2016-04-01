package com.jishijiajiao.finance.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IAnswerTimerDAO;
import com.jishijiajiao.finance.entity.AnswerTimer;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.service.IAnswerTimerService;
import com.jishijiajiao.finance.util.DateUtil;

@Service("answerTimerService")
public class IAnswerTimerServiceImpl implements IAnswerTimerService {
	private static Logger log = Logger.getLogger(IAnswerTimerServiceImpl.class);
	@Autowired
	private IAnswerTimerDAO answerTimerDao;
	private ResultMapper resultMap = new ResultMapper();

	@Transactional
	public int saveOrUpdateAnswerTime(AnswerTimer answerTimer) {
		try {
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public ResultMapper getRemainAnswerTime(String openId) {
		try {
			AnswerTimer answerTimer = answerTimerDao.queryAnswerTimerByOpenId(openId);
			if (answerTimer == null) {
				answerTimer= new AnswerTimer();
				answerTimer.setId(0);
				answerTimer.setRemainTime(0);
				answerTimer.setOpenId(openId);
				answerTimer.setUpdateTime(DateUtil.getNowTime());
			}else{
				answerTimer.setUpdateTime(DateUtil.StringPattern(answerTimer.getUpdateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS_MS, DateUtil.YYYY_MM_DD_HH_MM_SS));
			}
			this.resultMap.setSucResult(answerTimer);
			log.info("resultMap==" + this.resultMap);
			return this.resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultMap.setFailMsg(SystemStatus.SERVER_ERROR);
			return resultMap;
		}
	}

	@Override
	public Double getRemainTimeByOpenId(String openId) {
		try {
			AnswerTimer answerTimer = answerTimerDao
					.queryAnswerTimerByOpenId(openId);
			Double time = answerTimer.getRemainTime();
			log.info("    remainTime==" + time);
			return time;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
