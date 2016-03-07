package com.yijiajiao.customer.factory;



import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yijiajiao.customer.dao.ComplainDAO;
import com.yijiajiao.customer.dao.CustomerServicesDAO;
import com.yijiajiao.customer.dao.InvoiceDAO;

public class MybatisSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
    private SqlSession sqlSession;
	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MybatisSessionFactory() {
		super();
	}

	public static MybatisSessionFactory newInstance() {
		return new MybatisSessionFactory();
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public SqlSession openSession() {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		this.sqlSession = sqlSession1;
		return this.sqlSession;
	}
	
	public void commit(){
        this.sqlSession.commit();
	}
	
	public void close(){
        this.sqlSession.close();
	}
	
	public CustomerServicesDAO getCustomer(){
		CustomerServicesDAO IuserOption = this.openSession().getMapper(CustomerServicesDAO.class);
		return IuserOption;
	}
	
	public ComplainDAO getComplain(){
		ComplainDAO complainDAO = this.openSession().getMapper(ComplainDAO.class);
		return complainDAO;
	}	
	
	
	public InvoiceDAO getInvoice(){
		InvoiceDAO invoiceDAO = this.openSession().getMapper(InvoiceDAO.class);
		return invoiceDAO;
	}	
}
