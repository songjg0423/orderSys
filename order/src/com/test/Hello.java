package com.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.dao.Test;

public class Hello implements Controller {
	
	private Test testDAO;

	private String viewPage;
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		List list = testDAO.getTableInf();
		Map map = (Map)list.get(0);
		return new ModelAndView(getViewPage(),map);
	}
	public void setViewPage(String viewPage){ 
		this.viewPage = viewPage; 
	} 
		
	public String getViewPage(){
		return this.viewPage; 
	} 
	public void setTestDAO(Test testDAO) {
		this.testDAO = testDAO;
	}
	public Test getTestDAO() {
		return testDAO;
	}

}
