package com.jishijiajiao.finance.service;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.AnswerTimer;

public interface IAnswerTimerService {
	/**
	 * @param financeLog
	 * @return 0表示成功 1表示失败
	 */
	int saveOrUpdateAnswerTime(AnswerTimer answerTimer);

	/**
	 * @description 通过openid获取所剩答疑时长，返回值是ResultMapper实体
	 * @date 2015-12-25
	 * @return ResultMapper
	 * @param openId
	 */
	ResultMapper getRemainAnswerTime(String openId);

	/**
	 * @description 通过openid获取所剩答疑时长，返回值是时长值
	 * @date 2015-12-29
	 * @return Double
	 * @param openId
	 */
	Double getRemainTimeByOpenId(String openId);

}
