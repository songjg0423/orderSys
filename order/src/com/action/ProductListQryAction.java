package com.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.wechat.WechatServiceImpl;

public class ProductListQryAction implements Controller {
	
	private SqlMapClient sqlMapClient;
	
	private WechatServiceImpl wechatService;
	
	public void setWechatService(WechatServiceImpl wechatService) {
		this.wechatService = wechatService;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//String code = request.getAttribute("code").toString();
		String openid = "oqQqVuLZrkv-T2KuLj-Q6Pc7NK_Q";//this.wechatService.getAccessToken(code);
		
		request.getSession().setAttribute("OpenId", openid);
		List<Map> productTypes = this.sqlMapClient.queryForList("qryProductTypeList");
		List resultList = new ArrayList();
		for (Map productType : productTypes) {
			List<Map> productList = this.sqlMapClient.queryForList("qryProductList", productType.get("TypeSeq").toString());
			if (productList != null && !productList.isEmpty()) {
				productType.put("ProductList", productList);
				resultList.add(productType);
			}
		}
		
		Map map = new HashMap();
		map.put("ProductTypeList", resultList);
		
		Map cifInfo = (Map)this.sqlMapClient.queryForObject("queryCifSeqByCifId", openid);
		if (cifInfo != null) {
			map.putAll(cifInfo);
		}
		
		
		Map result = new HashMap();
		result.put("Json", map);
		return new ModelAndView("jsonView", result);
	}

}
