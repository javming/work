package com.yijiajiao.customer.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yijiajiao.customer.bean.InvoiceBean;
import com.yijiajiao.customer.dao.InvoiceDAO;
import com.yijiajiao.customer.model.Invoice;
@Repository("invoiceDAO")
public class InvoiceDAOImpl implements InvoiceDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public int updateInvoice(Invoice invoice) {
		return sessionTemplate.update("com.yijiajiao.customer.dao.InvoiceDAO.updateInvoice", invoice);
	}

	@Override
	public List<Invoice> getInvoiceList(InvoiceBean invoiceBean) {
		return sessionTemplate.selectList("com.yijiajiao.customer.dao.InvoiceDAO.getInvoiceList", invoiceBean);
	}

	@Override
	public int getCount(InvoiceBean invoiceBean) {
		return sessionTemplate.selectOne("com.yijiajiao.customer.dao.InvoiceDAO.getCount",invoiceBean);
	}

}
