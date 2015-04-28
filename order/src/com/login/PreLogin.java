package com.login;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PreLogin implements Controller {

	private String viewPage;
	
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView(getViewPage(),new HashMap());
	}

}
