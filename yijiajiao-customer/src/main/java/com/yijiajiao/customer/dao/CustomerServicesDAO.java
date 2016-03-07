package com.yijiajiao.customer.dao;


import java.text.ParseException;
import java.util.List;

import com.yijiajiao.customer.bean.CustomerBean;
import com.yijiajiao.customer.model.Customer;


public interface CustomerServicesDAO {

	public List<Customer> getCustomerList(CustomerBean customerBean);

	public Customer getCustomerById(String id)throws ParseException;

	public int updateCustomer(Customer customerServices);

	public int getCount(CustomerBean customerBean);

	public int insertCustomer(Customer customer);
}
