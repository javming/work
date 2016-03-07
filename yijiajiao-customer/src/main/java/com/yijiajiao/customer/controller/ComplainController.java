package com.yijiajiao.customer.controller;

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

import com.yijiajiao.customer.bean.ComplainBean;
import com.yijiajiao.customer.model.Complain;
import com.yijiajiao.customer.page.Pagination;
import com.yijiajiao.customer.servcie.ComplainService;
import com.yijiajiao.customer.util.ResultWrapper;
import com.yijiajiao.customer.util.ResultWrapperService;

@Component
@Path("/complain")
public class ComplainController {

	@Autowired
	ComplainService service;

	/**
	 * (投诉举报列表首页)初始化 获取投诉举报列表（分页、回复\未回复、 手机号）
	 * 
	 * @param list集合
	 *            (1.订单号,2.分页、3.回复\未回复、4. 手机号)
	 * @return 集合
	 * @person jiaojiuxin
	 * @date 2015/11/2
	 */
	@POST
	@Path("/service")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper getComplainList(ComplainBean complainBean) {
		ResultWrapperService wrapper = new ResultWrapperService();
		System.out.println("complainBean==="+complainBean);
		Pagination pagelists = service.getComplainList(complainBean);
		if (pagelists.getList().size() > 0) {
			wrapper.successful("成功", pagelists);
		} else {
			wrapper.failed("失败");
		}
		return wrapper.result;

	}

	/**
	 * (投诉举报).根据投诉举报ID获取单条咨询详情
	 * 
	 * @param id
	 * @return
	 * @person jiaojiuxin
	 * @date 2015/11/2
	 **/
	@GET
	@Path("/complainId/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper getComplainById(@PathParam("id") String id) {
		ResultWrapperService wrapper = new ResultWrapperService();
		Complain complain = service.getComplainById(id);
		if (complain != null) {
			wrapper.successful("成功", complain);
		} else {
			wrapper.failed("失败");
		}
		return wrapper.result;
	}

	/**
	 * (投诉举报).根据投诉举报ID 更新 单条咨询 （回复）
	 */

	@PUT
	@Path("/updateComplain")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper updateComplain(Complain complain) {
		ResultWrapperService wrapper = new ResultWrapperService();
		int complains = service.updateComplain(complain);
		if (complains==1) {
			wrapper.successful("成功", complains);
		} else {
			wrapper.failed("失败");
		}
		return wrapper.result;
	}
	/**
	 *@description  添加投诉举报信息
	 *@date 2016-2-3
	 *@return ResultWrapper
	 *@param complain
	 */
	@POST
	@Path("addComplain")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultWrapper addComplain(Complain complain){
		ResultWrapperService wrapper = new ResultWrapperService();
		int issuc = service.addComplain(complain);
		if(issuc ==1) wrapper.successful("成功", issuc);
		else wrapper.failed("失败");
		return wrapper.result;
	}
}
