package com.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.LoginDao;

public class Login implements Controller {
	
	private LoginDao loginDao;
	
	private String success;
	
	private String error;

	public void setError(String error) {
		this.error = error;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao dao) {
		this.loginDao = dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> map = (Map<String, String>) Util.request2Map(request);
		Map userInf = loginDao.login(map);
		if ( userInf == null || Util.isEmpty((String)userInf.get("userid"))) {
			Map errMap = new HashMap();
			errMap.put("errCode", "UL003");
			return new ModelAndView(error, errMap);
		}
		if (!Util.encrypt(map.get("password")).equals(userInf.get("password"))) {
			Map errMap = new HashMap();
			errMap.put("errCode", "UL002");
			return new ModelAndView(error, errMap);
		}
		request.getSession().setAttribute("user", userInf);
		return new ModelAndView(getSuccess(),userInf);
	}

}
