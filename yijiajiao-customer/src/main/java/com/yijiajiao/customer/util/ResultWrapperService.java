package com.yijiajiao.customer.util;

/**
 * @Title: ResultWrapperService.java
 * @Package com.eduspace.eduplatform.permission 
 * @Description: TODO
 * @author tbc
 * @date 2015年11月1日 上午12:20:28
 * @version
 */


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 
 * 
 * @author tbc e-eduspace
 * @version 1.0  create:{2015年11月1日 上午12:20:28}
 *  
 */
@Scope("prototype")
@Service("/resultWrapperService")
public class ResultWrapperService {
	Logger log = Logger.getLogger(getClass());
	
//	@Autowired
	public ResultWrapper result = new ResultWrapper();
	
	/**
	 * 执行成功时调用此方法返回成功包装器对象
	 * @param message
	 * @param result
	 * @return
	 */
	public void successful(String message,Object obj){
		result.setCode("200");
		result.setMessage(message);
		result.setResult(obj);
	}
	
	/**
	 * 服务未成功时调用此方法返回包含失败状态的包装器对象
	 * @param message
	 * @return
	 */
	public void failed(String message){
		result.setCode("400");
		result.setMessage(message);
	}
}
