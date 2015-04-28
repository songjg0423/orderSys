package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PreModifyPwd implements Controller{
	
	private String success;
	
	public void setSuccess(String success) {
		this.success = success;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		return new ModelAndView(success, null);
	}

}
