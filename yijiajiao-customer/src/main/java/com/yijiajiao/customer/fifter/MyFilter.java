package com.yijiajiao.customer.fifter;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 *  基础请求监控过滤器
 *
 */
public class MyFilter implements Filter {
	private static Logger log = Logger.getLogger(MyFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		 System.out.println("开始过滤... "); 
	        // 将请求转换成HttpServletRequest 请求 
		HttpServletRequest req = (HttpServletRequest) request;

		//req.getRequestURL()=http://192.168.1.169:8080/RestTest/student/getusers/1
		//req.getMethod()=get
		log.info("请求地址:" + req.getRequestURL() + "  涉及到的方法为：" + req.getMethod());

		int length = req.getContentLength();
		if (length > 0) {
			BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(req, length);
			InputStream is = bufferedRequest.getInputStream();
			byte[] content = new byte[length];
			int pad = 0;
			while (pad < length) {
				pad += is.read(content, pad, length);
			}
			log.info("涉及到的内容为:" + new String(content, "utf-8"));
			request = bufferedRequest;
		}
		 long bef = System.currentTimeMillis(); 
		 /// Filter 只是链式处理，请求依然转发到目的地址
		 chain.doFilter(request, response);
		 long aft = System.currentTimeMillis(); 
		 log.info(" Request to " + req.getRequestURI()+ "  use: " + (aft-bef)+" ms"); 
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("创建filter");
	}

}