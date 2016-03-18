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
import com.jishijiajiao.finance.entity.DailySettleAccounts;
import com.jishijiajiao.finance.service.ISettleAccountsService;

@Controller
@Path("/settleaccounts")
public class SettleAccountsController {
	@Autowired
	private ISettleAccountsService settleAccountsService;
	
	@POST
	@Path("/dailysettle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper addDailySettleAccounts(List<DailySettleAccounts> dailySettleAccounts){
		return settleAccountsService.addDailySettleAccounts(dailySettleAccounts);
	}
	
	
	
}
