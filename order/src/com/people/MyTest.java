package com.people;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.MyTestDao;

public class MyTest implements Controller {
	
	private MyTestDao myTestDao;
	
	private String success;
	
	public void setMyTestDao(MyTestDao dao) {
		this.myTestDao = dao;
	}
	
	public void setSuccess(String success) {
		this.success = success;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		Map map = Util.request2Map(request);
		myTestDao.qryTestId(map);
		return new ModelAndView(success, map);
	}

}
