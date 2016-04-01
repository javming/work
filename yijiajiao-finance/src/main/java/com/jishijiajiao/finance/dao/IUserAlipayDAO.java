package com.jishijiajiao.finance.dao;

import java.util.List;

import com.jishijiajiao.finance.entity.UserAlipay;

public interface IUserAlipayDAO {
	int insertUserAlipay(UserAlipay userAlipay);
	UserAlipay queryUserAlipayByOpenId(String open_id);
	List<UserAlipay> queryUserAlipayByOpenIds(List<String> openIds);
	void updateUserAlipay(UserAlipay userAlipay);
	void deleteUserAlipay(String open_id);
}
