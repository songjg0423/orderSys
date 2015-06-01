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

public class OrderListQryAction implements Controller {
	
	private SqlMapClient sqlMapClient;
	
	private WechatServiceImpl wechatService;
	
	private int pageSize;
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setWechatService(WechatServiceImpl wechatService) {
		this.wechatService = wechatService;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String openid = null;
		String code = (String)request.getAttribute("code");
		if (code != null && code.trim().length() != 0) {
			openid = this.wechatService.getAccessToken(code);
		} else {
			openid = (String)request.getSession().getAttribute("OpenId");
		}
		if (openid == null)openid = "oqQqVuLZrkv-T2KuLj-Q6Pc7NK_Q";
		
		request.getSession().setAttribute("OpenId", openid);
		Map qryMap = new HashMap();
		qryMap.put("OpenId", openid);
		Integer pageNum = (Integer)request.getAttribute("pageNum");
		if (pageNum == null) pageNum = 0;
		qryMap.put("StartNum", (pageNum - 1) * pageSize);
		qryMap.put("PageSize", this.pageSize);
		List<Map> orderList = this.sqlMapClient.queryForList("qryOrderList", qryMap);
		for (Map order : orderList) {
			List<Map> orderDetail = this.sqlMapClient.queryForList("qryOrderDetail", order.get("jnlno"));
			order.put("OrderDetail", orderDetail);
		}
		Integer totalNum = (Integer)this.sqlMapClient.queryForObject("qryOrderCount", openid);
		int totalPage = (totalNum - 1)/this.pageSize + 1;
		Map map = new HashMap();
		map.put("OrderList", orderList);
		map.put("TotalNum", totalNum);
		map.put("TotalPage", totalPage);
		
		Map result = new HashMap();
		result.put("Json", map);
		return new ModelAndView("jsonView", result);
	}

}
