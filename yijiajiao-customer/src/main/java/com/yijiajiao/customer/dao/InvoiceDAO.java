package com.yijiajiao.customer.dao;

import java.util.List;

import com.yijiajiao.customer.bean.InvoiceBean;
import com.yijiajiao.customer.model.Invoice;


public interface InvoiceDAO {

	int updateInvoice(Invoice invoice);

	List<Invoice> getInvoiceList(InvoiceBean invoiceBean);

	int getCount(InvoiceBean invoiceBean);

}
