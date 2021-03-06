package com.jishijiajiao.finance.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jishijiajiao.finance.entity.BatchPayDetail;

public class ReadExcelUtil{
  
public static List<BatchPayDetail> readExcel(String file){
	List<BatchPayDetail> payDetails = new ArrayList<BatchPayDetail>();
    jxl.Workbook readwb = null;
    try{
		//构建workbook对象，只读workbook对象
		//直接从本地文件创建workbook
		InputStream instream = new FileInputStream(file);
		readwb = Workbook.getWorkbook(instream);
		
		//sheet的下标是从0开始的
		//获取第一张Sheet表
		Sheet readsheet = readwb.getSheet(0);
		//获取sheet表中所包含的总列数和总行数
		int rsColums = readsheet.getColumns();
		int rsRows = readsheet.getRows();
		System.out.println("hang=="+rsRows+"--lie=="+rsColums);
		//获取指定单元格的对象引用
		for(int i =1;i<rsRows;i++){
			BatchPayDetail payDetail = new BatchPayDetail();
			for(int j=0;j<rsColums;j++){
				Cell cell = readsheet.getCell(j,i);
				String value = cell.getContents();
				System.out.print(j+value+"----");
				switch (j) {
				case 0:
					payDetail.setRunning_no(value);
					break;
				case 1:
					payDetail.setProceeds_account(value);
					break;
				case 2:
					payDetail.setProceeds_name(value);
					break;
				case 3:
					payDetail.setProceeds_fee(Double.parseDouble(value));
					break;
				case 4:
					payDetail.setRemark(value);
					break;
				default:
					break;
				}
			}
			payDetails.add(payDetail);
			System.out.println();
		}
    }catch(Exception e){
    	e.printStackTrace();
    }finally{
    	readwb.close();
    }
    return payDetails;
	}

}
