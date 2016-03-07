package com.yijiajiao.customer.util;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 *@author tbc  tianbencai@e-eduspace.com
 *@version 1.0 {2015年10月30日 下午3:25:40}
 */
@Scope("prototype")
@Service("resultWrapper")
public class ResultWrapper {
	
	private String requestId;
	private String code;
	private String httpCode;
	private String message;
	private Object result;
	
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	

}
