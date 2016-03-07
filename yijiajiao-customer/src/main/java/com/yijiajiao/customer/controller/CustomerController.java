package com.yijiajiao.customer.controller;

import java.text.ParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yijiajiao.customer.bean.CustomerBean;
import com.yijiajiao.customer.model.Customer;
import com.yijiajiao.customer.page.Pagination;
import com.yijiajiao.customer.servcie.CustomerService;
import com.yijiajiao.customer.util.ResultWrapper;
import com.yijiajiao.customer.util.ResultWrapperService;

@Component
@Path("/customer")
public class CustomerController extends ResultWrapperService {

	@Autowired
	CustomerService service;

	/**
	 * (咨询首页)初始化 获取咨询列表（分页、回复\未回复、 手机号）
	 * 
	 * @param list集合
	 *            (分页、回复\未回复、 手机号)
	 * @return 集合
	 * @person jiaojiuxin
	 * @date 2015/11/2 @
	 */
	@POST
	@Path("/service")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper getCustomerList(CustomerBean customerBean) {
		ResultWrapperService wrapper = new ResultWrapperService();
		Pagination lists = service.getCustomerList(customerBean);
		if (lists.getList().size() > 0) {
			wrapper.successful("成功", lists);
		} else {
			wrapper.failed("失败");
		}
		return wrapper.result;

	}

	/**
	 * (咨询首页)根据咨询ID获取单条咨询详情
	 * 
	 * @param id
	 * @return
	 * @person jiaojiuxin
	 * @date 2015/11/2
	 **/
	@GET
	@Path("/customeryId/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper getCustomerById(@PathParam("id") String id)
			throws ParseException {
		ResultWrapperService wrapper = new ResultWrapperService();
		Customer cus = service.getCustomerById(id);
		if (cus != null) {
			wrapper.successful("成功", cus);
		} else {
			wrapper.failed("失败");
		}
		return wrapper.result;
	}

	/**
	 * (咨询首页)根据咨询ID 更新 单条咨询 （回复）
	 */

	@PUT
	@Path("/updateCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper updateCustomer(Customer customerServices) {
		ResultWrapperService wrapper = new ResultWrapperService();
		int customer = service.updateCustomer(customerServices);
		System.out.println("进upadte方法");
		if (customer==1) {
			wrapper.successful("成功", customer);
		} else {
			wrapper.failed("失败");
		}
		return wrapper.result;
	}

	
	@POST
	@Path("/addCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper addCustomer(Customer customerServices) {
		ResultWrapperService wrapper = new ResultWrapperService();
		int issuc = service.addCustomer(customerServices);
		if (issuc==1) wrapper.successful("保存成功", issuc);
		else wrapper.failed("保存失败");
		return wrapper.result;
	}

}
