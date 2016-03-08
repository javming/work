package com.yijiajiao.customer.servcie.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yijiajiao.customer.bean.CustomerBean;
import com.yijiajiao.customer.dao.CustomerServicesDAO;
import com.yijiajiao.customer.factory.MybatisSessionFactory;
import com.yijiajiao.customer.model.Customer;
import com.yijiajiao.customer.page.Pagination;
import com.yijiajiao.customer.servcie.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerServicesDAO customedao;
	@Override
	public Pagination getCustomerList(CustomerBean customerBean) {
		List<Customer> lists = null;
		Pagination pagination = new Pagination();
		try {
			// 查询符合条件的总条数
			int totalCount = customedao.getCount(customerBean);
			// //查询满足条件的用户列表
			lists = customedao.getCustomerList(customerBean);

			if (lists.size() > 0) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < lists.size(); i++) {
					if (lists.get(i).getOperator_date() != null) {
						String str = format.format(lists.get(i)
								.getOperator_date());
						lists.get(i).setNew_operator_date(str);
					}

					if (lists.get(i).getCounseling_time() != null) {
						String str1 = format.format(lists.get(i)
								.getCounseling_time());
						lists.get(i).setNew_counseling_time(str1);
					}

				}
			}
			// System.out.println("lists-->" + lists.size());
			// 当前页 页号
			// 每页数
			// 总条数 （符合条件）
			pagination.setPageNo(customerBean.getPageNum());
			pagination.setPageSize(customerBean.getPageSize());
			pagination.setTotalCount(totalCount);
			pagination.setList(lists);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return pagination;
	}

	@Override
	public Customer getCustomerById(String id) throws ParseException {
		Customer articles = null;
		try {
			articles = customedao.getCustomerById(id);
			if (articles != null) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				if (articles.getOperator_date() != null) {
					String str = format.format(articles.getOperator_date());
					articles.setNew_operator_date(str);
				}
				if (articles.getCounseling_time() != null) {
					String strs = format.format(articles.getCounseling_time());
					articles.setNew_counseling_time(strs);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	@Transactional
	public int updateCustomer(Customer customerServices) {
		// customerServices.setCounseling_time(new Date());
		customerServices.setOperator_date(new Date());
		if (customerServices.getReply() != null) {
			customerServices.setStatus("1");
		}
		try {
			customedao.updateCustomer(customerServices);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	
	@Override
	public int addCustomer(Customer customerServices) {
		try {
			customerServices.setCounseling_time(new Date());
			customerServices.setStatus("0");
			int customer = customedao.insertCustomer(customerServices);
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
