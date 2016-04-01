package com.jishijiajiao.finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.dao.IUserAlipayDAO;
import com.jishijiajiao.finance.entity.SystemStatus;
import com.jishijiajiao.finance.entity.UserAlipay;
import com.jishijiajiao.finance.service.UserAlipayService;
import com.jishijiajiao.finance.util.DateUtil;
@Service("userAlipayService")
public class UserAlipayServiceImpl implements UserAlipayService {
	@Autowired
	private IUserAlipayDAO userAlipayDAO;
	private ResultMapper resultBean = new ResultMapper();
	@Override
	@Transactional
	public ResultMapper addUserAlipay(UserAlipay userAlipay) {
		try {
			//验证数据是否为空
			boolean is_null= userAlipay.getOpen_id()==null || "".equals(userAlipay.getOpen_id()) || userAlipay.getAlipay_account()==null 
					||"".equals(userAlipay.equals(userAlipay.getAlipay_account())) || userAlipay.getAlipay_name()==null || "".equals(userAlipay.getAlipay_name());
			if(is_null){
				this.resultBean.setFailMsg(SystemStatus.PARAM_NULL);
				return this.resultBean;
			}
			UserAlipay byOpenId = userAlipayDAO.queryUserAlipayByOpenId(userAlipay.getOpen_id());
			if(byOpenId != null){
				this.resultBean.setFailMsg(SystemStatus.USER_ALIPAY_HAS);
				return this.resultBean;
			}
			userAlipay.setCreate_time(DateUtil.getNowTime());
			int i = userAlipayDAO.insertUserAlipay(userAlipay);
			System.out.println("i===="+i+"保存的数据==="+userAlipay);
			this.resultBean.setSucResult("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}
	@Override
	public ResultMapper queryAllUsersAlipay() {
		return null;
	}
	@Override
	public ResultMapper modUserAlipay(UserAlipay userAlipay) {
		try {
			//验证数据是否为空
			boolean is_null= userAlipay.getOpen_id()==null || "".equals(userAlipay.getOpen_id()) || userAlipay.getAlipay_account()==null 
					||"".equals(userAlipay.equals(userAlipay.getAlipay_account())) || userAlipay.getAlipay_name()==null || "".equals(userAlipay.getAlipay_name());
			if(is_null){
				this.resultBean.setFailMsg(SystemStatus.PARAM_NULL);
				return this.resultBean;
			}
			
			UserAlipay user=userAlipayDAO.queryUserAlipayByOpenId(userAlipay.getOpen_id());
			if(user == null ){
				userAlipayDAO.insertUserAlipay(userAlipay);
			}else{
				user.setAlipay_account(userAlipay.getAlipay_account());
				user.setAlipay_name(userAlipay.getAlipay_name());
				user.setCreate_time(DateUtil.getNowTime());
				userAlipayDAO.updateUserAlipay(userAlipay);
			}
			this.resultBean.setSucResult("修改成功！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}
	@Override
	public ResultMapper queryUserAlipayByOpenId(String openId) {
		try {
			UserAlipay alipay = userAlipayDAO.queryUserAlipayByOpenId(openId);
			if(alipay == null ) this.resultBean.setFailMsg(SystemStatus.ID_NOT_FOUND);
			else this.resultBean.setSucResult(alipay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}
	@Override
	public ResultMapper delUserAlipayByOpenId(String openId) {
		try {
			userAlipayDAO.deleteUserAlipay(openId);
			this.resultBean.setSucResult("解绑成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.resultBean.setFailMsg(SystemStatus.SERVER_ERROR);
		}
		return this.resultBean;
	}

}
