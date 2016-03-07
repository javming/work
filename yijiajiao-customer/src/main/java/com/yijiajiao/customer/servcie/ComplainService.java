package com.yijiajiao.customer.servcie;

import com.yijiajiao.customer.bean.ComplainBean;
import com.yijiajiao.customer.model.Complain;
import com.yijiajiao.customer.page.Pagination;

public interface ComplainService {

	Pagination getComplainList(ComplainBean complainBean);

	Complain getComplainById(String id);

	int updateComplain(Complain complain);

	int addComplain(Complain complain);

}