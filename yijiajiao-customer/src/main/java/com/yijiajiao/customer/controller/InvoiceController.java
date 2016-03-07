package com.yijiajiao.customer.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yijiajiao.customer.bean.InvoiceBean;
import com.yijiajiao.customer.model.Invoice;
import com.yijiajiao.customer.page.Pagination;
import com.yijiajiao.customer.servcie.InvoiceService;
import com.yijiajiao.customer.util.ResultWrapper;
import com.yijiajiao.customer.util.ResultWrapperService;

@Component
@Path("/invoice")
public class InvoiceController extends ResultWrapperService {

	@Autowired
	InvoiceService service;

	/**
	 * (发票管理首页)初始化 获取发票管理列表（分页、回复\未回复、 手机号）
	 * 
	 * @param list集合
	 *            (分页、回复\未回复、 订单号)
	 * @return 集合
	 * @person jiaojiuxin
	 * @date 2015/11/2 @
	 */
	@POST
	@Path("/invoiceList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper getInvoiceList(InvoiceBean invoiceBean) {

		ResultWrapperService wrappers = new ResultWrapperService();
		Pagination lists = service.getInvoice(invoiceBean);
		if (lists.getList().size() > 0) {
			wrappers.successful("成功", lists);
		} else {
			wrappers.failed("失败");
		}
		return wrappers.result;
	}

	/**
	 * 根据发票管理ID 更新 单条发票管理数据 （回复） 快递公司写死
	 */

	@PUT
	@Path("/updateInvoice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultWrapper updateInvoice(Invoice invoice) {
		ResultWrapperService wrapper = new ResultWrapperService();
		int invoices = service.updateInvoice(invoice);
		if (invoices > 0) {
			wrapper.successful("成功", invoices);
		} else {
			wrapper.failed("失败");
		}
		return wrapper.result;
	}

}
