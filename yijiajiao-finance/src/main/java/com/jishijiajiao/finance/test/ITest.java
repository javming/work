package com.jishijiajiao.finance.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JsonConfig;

import org.junit.Test;

import com.jishijiajiao.finance.entity.BatchPayDetail;
import com.jishijiajiao.finance.entity.FinanceLog;
import com.jishijiajiao.finance.entity.WaresSlave;
import com.jishijiajiao.finance.util.DateUtil;
import com.jishijiajiao.finance.util.JsonDateValueProcessor;
import com.jishijiajiao.finance.util.RandomUtil;
import com.jishijiajiao.finance.util.ReadExcelUtil;

public class ITest {
	@Test
	public void testDate(){
		Calendar cal = Calendar.getInstance();
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
	     System.out.println("doy=="+doy);
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
	public void testHttpClient(){
		FinanceLog financeLog = new FinanceLog();
		financeLog.setOpenId("1f79efea-fcb2-4a55-8bc7-12b6055e49ed");
		financeLog.setSellOpenId("6f79efea-fcb2-4a55-8bc7-12b6055e49ed");
		financeLog.setOrderNumber("12121212131415926");
		financeLog.setCommodityId("12121212131415926");
		financeLog.setCurriculumName("初中历史");
		financeLog.setCurriculumInfo("秦始皇统一六国");
		financeLog.setTotalPrice(15.00);
		financeLog.setAccountNumber("123@qq.com");
		WaresSlave waSlave = new WaresSlave();
		waSlave.setSlaveId(1);
		waSlave.setStartTime("2016-01-27 08:00:00");
		waSlave.setEndTime("2016-01-27 09:00:00");
		financeLog.getWaresSlaves().add(waSlave);
		
	}
	@Test
	public void testStringBuilder(){
		String time = DateUtil.getNowTime("MMdd");
		System.out.println("now=="+time);
		
	}
	@Test
	public void getBatchno(){
		String time = DateUtil.getNowTime("yyyyMMddhhmmss");
		String random = RandomUtil.getRandomCharNum(6);
		System.out.println(time);
		System.out.println(random);
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
}
