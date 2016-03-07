package com.yijiajiao.customer.servcie;

import java.text.ParseException;





import com.yijiajiao.customer.bean.CustomerBean;
import com.yijiajiao.customer.model.Customer;
import com.yijiajiao.customer.page.Pagination;

public interface CustomerService {
	Pagination  getCustomerList(CustomerBean customerBean);

	Customer getCustomerById(String id)throws ParseException;

	int updateCustomer(Customer customerServices);

	int addCustomer(Customer customerServices);

	
}