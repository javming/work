package com.yijiajiao.customer.servcie;


import com.yijiajiao.customer.bean.InvoiceBean;
import com.yijiajiao.customer.model.Invoice;
import com.yijiajiao.customer.page.Pagination;


public interface InvoiceService {

	int updateInvoice(Invoice invoice);

	Pagination getInvoice(InvoiceBean invoiceBean);

}