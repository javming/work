package com.yijiajiao.customer.dao;

import java.util.List;

import com.yijiajiao.customer.bean.ComplainBean;
import com.yijiajiao.customer.model.Complain;

public interface ComplainDAO {

	List<Complain> getComplainList (ComplainBean complainBean);

	Complain getComplainById(String id);

	int updateComplain(Complain complain);

	int getCount(ComplainBean complainBean);
	
	int insertComplain(Complain complain);

}
