package com.jishijiajiao.finance.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.WaresSlave;
import com.jishijiajiao.finance.service.IBatchPayService;
import com.jishijiajiao.finance.util.Config;
import com.jishijiajiao.finance.util.DateUtil;
import com.jishijiajiao.finance.util.HttpClient;
import com.jishijiajiao.finance.util.JsonDateValueProcessor;
import com.jishijiajiao.finance.util.RandomUtil;
import com.jishijiajiao.finance.util.ReadExcelUtil;

public class ITest {
	@Test
	public void testDate() throws ParseException{
/*		Calendar cal = Calendar.getInstance();
	    int day = cal.get(Calendar.DATE);
	     int month = cal.get(Calendar.MONTH) + 1;
	     int year = cal.get(Calendar.YEAR);
	     int dow = cal.get(Calendar.DAY_OF_WEEK);
	     int dom = cal.get(Calendar.DAY_OF_MONTH);
	     int doy = cal.get(Calendar.DAY_OF_YEAR);
	     System.out.println("day=="+day);
	     System.out.println("month=="+month);
	     System.out.println("year=="+year);
	     System.out.println("dow=="+dow);
	     System.out.println("dom=="+dom);
	     System.out.println("doy=="+doy);*/
		DateUtil du = new DateUtil();
		//获得本月第一天
		Date beginMonth = du.calcBeginMonth(DateUtil.getNowTime());
		Date endMonth = du.calcEndMonth(DateUtil.getNowTime());
		Date addDays = DateUtil.addDays(beginMonth, 10);
		System.out.println(DateUtil.dateToString(beginMonth, DateUtil.YYYY_MM_DD_HH_MM_SS));
		System.out.println(DateUtil.dateToString(addDays, DateUtil.YYYY_MM_DD_HH_MM_SS));
		System.out.println(DateUtil.dateToString(endMonth, DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		//上个月第一天
		String lasb = DateUtil.calcDate(beginMonth,DateUtil.YYYY_MM_DD, 2, -1);
		//上个月最后天
		String lase = DateUtil.calcDate(beginMonth, DateUtil.YYYY_MM_DD, 5, -1);
		System.out.println(lasb);
		System.out.println(lase);
	}
	@Test
	public void testMath(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		java.util.Date date = new java.util.Date();
		String format = sdf.format(date);
		System.out.println(format);
	}
	@Test
	public void testJson(){
		Date date = new Date();
		JsonDateValueProcessor jdvp = new JsonDateValueProcessor();
		JsonConfig jconfig=new JsonConfig();
		Object value = jdvp.processObjectValue("", date, jconfig);
		System.out.println(value);
		
	}
	@Test
	public void testHttpClient(){
		
		String httpRest = HttpClient.httpRest(Config.getString("user.server"),Config.getString("userinfo.url")+"0fe3858f-df02-402c-99ad-4ebc5a031e50", null, null, "GET");
		System.out.println(httpRest);
		JSONObject json = JSONObject.fromObject(httpRest);
		JSONObject object = (JSONObject) json.get("result");
		String name = object.getString("name");
		String phone = object.getString("username");
		System.out.println(name);
		System.out.println(phone);
	}
	@Test
	public void testStringBuilder(){
		String time = DateUtil.getNowTime("MMdd");
		System.out.println("now=="+time);
		
	}
	@Test
	public void getBatchno(){
		String batch_no = DateUtil.getNowTime("yyyyMMddhhmmss")+RandomUtil.getRandomCharNum(2);
		System.out.println(batch_no);
	}
	
	@Test
	public void readExcel(){
		String file = "D:/work/template.xls";
		List<BatchPayDetail> readExcel = ReadExcelUtil.readExcel(file);
		
		for(BatchPayDetail bpd:readExcel){
			System.out.println(bpd.getPayString());
		}
	}
	@Test
	public void testStringBulder(){
		StringBuilder sb = new StringBuilder();
		sb.append(" 张三").append("                ").append("18");
		System.out.println(sb.toString());
	}
	@Test
	public void testmodBatchPayDetailAndMoneyTiemer(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		IBatchPayService batchPayService=(IBatchPayService) context.getBean("batchPayService");
		System.out.println("batchPayService============="+batchPayService);
		batchPayService.modBatchPayDetailAndMoneyTiemer("20160321034826531855");
	}

}
