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
import com.jishijiajiao.finance.service.IDailySettleAccountsService;

@Controller
@Path("/dailysettle")
public class DailySettleAccountsController {
	@Autowired
	private IDailySettleAccountsService dailySettleAccountsService;
	
	@POST
	@Path("/dailyadd")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultMapper addDailySettleAccounts(List<DailySettleAccounts> dailySettleAccounts){
		return dailySettleAccountsService.addDailySettleAccounts(dailySettleAccounts);
	}

}
