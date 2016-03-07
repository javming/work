package com.yijiajiao.customer.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yijiajiao.customer.bean.ComplainBean;
import com.yijiajiao.customer.dao.ComplainDAO;
import com.yijiajiao.customer.model.Complain;
@Repository("complainDAO")
public class ComplainDAOImpl implements ComplainDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public List<Complain> getComplainList(ComplainBean complainBean) {
		System.out.println(complainBean.getStartRow()+"pageSize=="+complainBean.getPageSize());
		return sessionTemplate.selectList("com.yijiajiao.customer.dao.ComplainDAO.getComplainList",complainBean);
	}

	@Override
	public Complain getComplainById(String id) {
		return sessionTemplate.selectOne("com.yijiajiao.customer.dao.ComplainDAO.getComplainById", id);
	}

	@Override
	public int updateComplain(Complain complain) {
		return sessionTemplate.update("com.yijiajiao.customer.dao.ComplainDAO.updateComplain", complain);
	}

	@Override
	public int getCount(ComplainBean complainBean) {
		return sessionTemplate.selectOne("com.yijiajiao.customer.dao.ComplainDAO.getCount", complainBean);
	}

	@Override
	public int insertComplain(Complain complain) {
		return sessionTemplate.insert("com.yijiajiao.customer.dao.ComplainDAO.insertComplain", complain);
	}

}
