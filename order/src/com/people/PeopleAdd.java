package com.people;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.PeopleDao;

public class PeopleAdd implements Controller {
	

	
	private String success;
	
	private PeopleDao peopleDao;
	
	public void setSuccess(String success) {
		this.success = success;
	}
	
	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		Map map = Util.request2Map(request);
		String name = (String)map.get("name");
		name = java.net.URLDecoder.decode(name,"UTF-8");
		map.put("name", name);
//		name = java.net.URLDecoder.decode(name,"utf-8");
		peopleDao.addPerson(map);
		return new ModelAndView(success, map);
	}



}
