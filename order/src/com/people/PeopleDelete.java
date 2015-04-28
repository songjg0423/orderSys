package com.people;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.PeopleDao;

public class PeopleDelete implements Controller {
	
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
		peopleDao.deletePerson(map);
		return new ModelAndView(success, map);
	}

}
