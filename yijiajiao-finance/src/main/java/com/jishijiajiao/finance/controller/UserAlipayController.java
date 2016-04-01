package com.jishijiajiao.finance.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jishijiajiao.finance.bean.ResultMapper;
import com.jishijiajiao.finance.entity.UserAlipay;
import com.jishijiajiao.finance.service.UserAlipayService;

@Controller
@Path("/userAlipay")
public class UserAlipayController {
	@Autowired
	private UserAlipayService userAlipayService;
	
	/**
	 *@description 账户绑定支付宝账号
	 *@date 2016-3-29
	 *@return ResultMapper
	 *@param userAliapy
	 *@return
	 */
	@POST
	@Path("/adduser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultMapper addUserAlipay(UserAlipay userAliapy){
		return userAlipayService.addUserAlipay(userAliapy);
	}
	/**
	 *@description 通过openId查询
	 *@date 2016-3-29
	 *@return ResultMapper
	 *@param openId
	 *@return
	 */
	@GET
	@Path("/queryByOpenId")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultMapper queryUserAlipayByOpenId(@QueryParam("openId") String openId){
		return userAlipayService.queryUserAlipayByOpenId(openId);
	}
	/**
	 *@description 解绑支付宝
	 *@date 2016-3-29
	 *@return ResultMapper
	 *@param openId
	 *@return
	 */
	@GET
	@Path("/delByOpenId")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultMapper delUserAlipayByOpenId(@QueryParam("openId") String openId){
		return userAlipayService.delUserAlipayByOpenId(openId);
	}
	
	public ResultMapper queryAllUsersAlipay(){
		return userAlipayService.queryAllUsersAlipay();
	}
}
