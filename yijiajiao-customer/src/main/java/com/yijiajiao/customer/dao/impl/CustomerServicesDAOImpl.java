package com.yijiajiao.customer.dao.impl;

import java.text.ParseException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yijiajiao.customer.bean.CustomerBean;
import com.yijiajiao.customer.dao.CustomerServicesDAO;
import com.yijiajiao.customer.model.Customer;
@Repository("customedao")
public class CustomerServicesDAOImpl implements CustomerServicesDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public List<Customer> getCustomerList(CustomerBean customerBean) {
		return sessionTemplate.selectList("com.yijiajiao.customer.dao.CustomerServicesDAO.getCustomerList", customerBean);
	}

	@Override
	public Customer getCustomerById(String id) throws ParseException {
		return sessionTemplate.selectOne("com.yijiajiao.customer.dao.CustomerServicesDAO.getCustomerById", id);
	}

	@Override
	public int updateCustomer(Customer customerServices) {
		return sessionTemplate.update("com.yijiajiao.customer.dao.CustomerServicesDAO.updateCustomer", customerServices);
	}

	@Override
	public int getCount(CustomerBean customerBean) {
		return sessionTemplate.selectOne("com.yijiajiao.customer.dao.CustomerServicesDAO.getCount", customerBean);
	}

	@Override
	public int insertCustomer(Customer customer) {
		return sessionTemplate.insert("com.yijiajiao.customer.dao.CustomerServicesDAO.insertCustomer", customer);
	}
	
}
