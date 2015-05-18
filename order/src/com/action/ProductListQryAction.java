package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProductListQryAction implements Controller {
	
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Map> productList = this.sqlMapClient.queryForList("qryProductList");
		Map map = new HashMap();
		map.put("ProductList", productList);
		Map result = new HashMap();
		result.put("Json", map);
		return new ModelAndView("jsonView", result);
	}

}
