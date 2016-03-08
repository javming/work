package com.yijiajiao.customer.servcie.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yijiajiao.customer.bean.ComplainBean;
import com.yijiajiao.customer.dao.ComplainDAO;
import com.yijiajiao.customer.factory.MybatisSessionFactory;
import com.yijiajiao.customer.model.Complain;
import com.yijiajiao.customer.page.Pagination;
import com.yijiajiao.customer.servcie.ComplainService;
import com.yijiajiao.customer.util.DateUtil;

@Service
public class ComplainServiceImpl implements ComplainService {
	@Autowired
	private ComplainDAO complainDAO;
	@Override
	public Pagination getComplainList(ComplainBean complainBean) {
		List<Complain> lists = null;
		Pagination pagination = new Pagination();
		try {
			// 查询满足条件的用户列表
			lists = complainDAO.getComplainList(complainBean);
			// 查询符合条件的总条数
			int totalCount = complainDAO.getCount(complainBean);
			// 当前页 页号
			// 每页数
			// 总条数 （符合条件）
			pagination.setPageNo(complainBean.getPageNum());
			pagination.setPageSize(complainBean.getPageSize());
			pagination.setTotalCount(totalCount);
			pagination.setList(lists);

			if (lists.size() > 0) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < lists.size(); i++) {
					if (lists.get(i).getTrading_time() != null) {
						String trading_time = format.format(lists.get(i)
								.getTrading_time());
						lists.get(i).setNew_trading_time(trading_time);
					}
					if (lists.get(i).getOperator_date() != null) {
						String operator_date = format.format(lists.get(i)
								.getOperator_date());
						lists.get(i).setNew_operator_date(operator_date);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagination;
	}

	@Override
	public Complain getComplainById(String id) {
		Complain complain = null;
		try {
			complain = complainDAO.getComplainById(id);
			if (complain != null) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				if (complain.getTrading_time() != null) {
					String trading = format.format(complain.getTrading_time());
					complain.setNew_trading_time(trading);
				}
				if (complain.getOperator_date() != null) {
					String operator_date = format.format(complain
							.getOperator_date());
					complain.setNew_operator_date(operator_date);
				}

			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return complain;
	}

	@Override
	@Transactional
	public int updateComplain(Complain complain) {
		int articles;
		try {
			complain.setOperator_date(new Date());
			if (complain.getReply()!=null){
				complain.setStatus("1");
			}
			articles = complainDAO.updateComplain(complain);
			System.out.println("articles=============="+articles);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional
	public int addComplain(Complain complain) {
		complain.setOperator_date(new Date());
		complain.setTrading_time(new Date());
		complain.setStatus("0");
		int insertComplain;
		try {
			insertComplain = complainDAO.insertComplain(complain);
			System.out.println("insertComplain==="+insertComplain);
			return insertComplain;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

}
