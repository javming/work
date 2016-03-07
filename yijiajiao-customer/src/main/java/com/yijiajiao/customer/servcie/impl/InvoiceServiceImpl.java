package com.yijiajiao.customer.servcie.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yijiajiao.customer.bean.InvoiceBean;
import com.yijiajiao.customer.dao.ComplainDAO;
import com.yijiajiao.customer.dao.InvoiceDAO;
import com.yijiajiao.customer.factory.MybatisSessionFactory;
import com.yijiajiao.customer.model.Invoice;
import com.yijiajiao.customer.page.Pagination;
import com.yijiajiao.customer.servcie.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDAO invoiceDAO;
	@Override
	public Pagination getInvoice(InvoiceBean invoiceBean) {
		List<Invoice> lists = null;
		Pagination pagination = new Pagination();
		try {
			// 查询符合条件的总条数
			int totalCount = invoiceDAO.getCount(invoiceBean);
			// //查询满足条件的用户列表
			lists = invoiceDAO.getInvoiceList(invoiceBean);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (lists.size() > 0) {
				for (int i = 0; i < lists.size(); i++) {
					if (lists.get(i).getInvoice_date() != null) {
						String invoice_date = format.format(lists.get(i)
								.getInvoice_date());
						lists.get(i).setNew_invoice_date(invoice_date);
					}

					if (lists.get(i).getOperator_date() != null) {
						String operator_date = format.format(lists.get(i)
								.getOperator_date());
						lists.get(i).setNew_operator_date(operator_date);
					}

				}
			}
			// 当前页 页号
			// 每页数
			// 总条数 （符合条件）
			pagination.setPageNo(invoiceBean.getPageNum());
			pagination.setPageSize(invoiceBean.getPageSize());
			pagination.setTotalCount(totalCount);
			pagination.setList(lists);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagination;
	}

	@Override
	@Transactional
	public int updateInvoice(Invoice invoice) {
		invoice.setOperator_date(new Date());
	//	invoice.setInvoice_date(new Date());
		if (invoice.getExpress_number() != null) {
			invoice.setStatus("1");
		}
		int invoices = invoiceDAO.updateInvoice(invoice);
		return invoices;
	}

}
