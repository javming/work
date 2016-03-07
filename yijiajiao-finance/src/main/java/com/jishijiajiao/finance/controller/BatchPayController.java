package com.jishijiajiao.finance.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.service.IBatchPayService;

@Controller
@Path("/batchpay")
public class BatchPayController {
	@Autowired
	private IBatchPayService batchPayService;
	/**
	 *@description 批量转账
	 *@date 2016-2-2
	 *@return ResultMapper
	 *@return
	 */
	@POST
	@Path("/paylist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper batchPay(List<BatchPayDetail> batchPayDetails){
		return batchPayService.batchPay(batchPayDetails);
	}
}
