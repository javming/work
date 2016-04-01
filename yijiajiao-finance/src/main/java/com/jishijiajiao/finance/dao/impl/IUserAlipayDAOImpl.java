package com.jishijiajiao.finance.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jishijiajiao.finance.dao.IUserAlipayDAO;
import com.jishijiajiao.finance.entity.UserAlipay;
@Repository("userAlipayDAO")
public class IUserAlipayDAOImpl implements IUserAlipayDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public int insertUserAlipay(UserAlipay userAlipay) {
		return sessionTemplate.insert("com.jishijiajiao.finance.dao.IUserAlipayDAO.insertUserAlipay", userAlipay);
	}
	@Override
	public UserAlipay queryUserAlipayByOpenId(String open_id) {
		return sessionTemplate.selectOne("com.jishijiajiao.finance.dao.IUserAlipayDAO.queryUserAlipayByOpenId", open_id);
	}
	@Override
	public List<UserAlipay> queryUserAlipayByOpenIds(List<String> openIds) {
		return sessionTemplate.selectList("com.jishijiajiao.finance.dao.IUserAlipayDAO.queryUserAlipayByOpenIds", openIds);
	}
	@Override
	public void updateUserAlipay(UserAlipay userAlipay) {
		sessionTemplate.update("com.jishijiajiao.finance.dao.IUserAlipayDAO.updateUserAlipay", userAlipay);
	}
	@Override
	public void deleteUserAlipay(String open_id) {
		sessionTemplate.update("com.jishijiajiao.finance.dao.IUserAlipayDAO.deleteUserAlipay", open_id);
	}

}
