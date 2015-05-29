package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.orm.ibatis.SqlMapClientOperations;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import DataModel.CifLevel;
import DataModel.CifState;
import DataModel.CifType;
import DataModel.OderState;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SubmitOrderAction implements Controller {
	
	private SqlMapClientOperations sqlMap;
	
	private TransactionTemplate trsTemplate;

	public void setSqlMap(SqlMapClientOperations sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	public void setTrsTemplate(TransactionTemplate trsTemplate){
		this.trsTemplate = trsTemplate;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Object name = request.getAttribute("Name");
		if (name == null || name.toString().trim().length() == 0) {
			throw new Exception("validation.submit.order.name.is.empty");
		}
		Object mobilePhone = request.getAttribute("MobilePhone");
		if (mobilePhone == null || mobilePhone.toString().trim().length() == 0) {
			throw new Exception("validation.submit.order.mobilePhone.is.empty");
		}
		Object address = request.getAttribute("Address");
		if (address == null || address.toString().trim().length() == 0) {
			throw new Exception("validation.submit.order.address.is.empty");
		}
		String openId = request.getSession().getAttribute("OpenId").toString();
		
		Map insertMap = new HashMap();
		insertMap.put("CifId", openId);
		insertMap.put("Name", name);
		insertMap.put("MobilePhone", mobilePhone);
		insertMap.put("Address", address);
		insertMap.put("CifType", CifType.PER.toString());
		insertMap.put("CifLevel", CifLevel.COM.toString());
		insertMap.put("CifState", CifState.NORMAL.toString());
		
		int i = this.sqlMap.update("updateCifInfo", insertMap);
		if (i == 0) {
			this.sqlMap.insert("insertCifInfo", insertMap);
		}
		Map cifInfo = (Map)this.sqlMap.queryForObject("queryCifSeqByCifId", openId);
		final Map orderInfo = new HashMap();
		orderInfo.put("CifSeq", cifInfo.get("CifSeq"));
		orderInfo.put("Address", address);
		orderInfo.put("Phone", mobilePhone);
		orderInfo.put("OrderState", OderState.INIT);
		orderInfo.put("TotalAmount", request.getAttribute("TotalAmount"));
		final List productList = (List)request.getAttribute("ProductList");
		
		this.trsTemplate.execute(new TransactionCallback<Object>(){

			@Override
			public Object doInTransaction(TransactionStatus status) {
				sqlMap.insert("insertOrderInfo", orderInfo);
				Integer orderSeq = (Integer)sqlMap.queryForObject("qeuryCurrentOrderSeq");
				System.out.println(orderSeq);
				for (int i = 0; i < productList.size(); i++) {
					Map orderDetail = (Map)productList.get(i);
					orderDetail.put("JnlNo", orderSeq);
					orderDetail.put("SubJnlNo", i);
					sqlMap.insert("insertOrderDetail", orderDetail);
				}
				return null;
			}
			
		});
		
		
		
		Map json = new HashMap();
		Map model = new HashMap();
		model.put("Json", json);
		return new ModelAndView("jsonView", model);
	}

}
