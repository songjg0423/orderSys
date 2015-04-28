package com.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.UserDao;

public class ModifyPwd implements Controller{
	
	private String success;
	
	private UserDao userDao;
	
	public void setSuccess(String success) {
		this.success = success;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		Map resultMap = new HashMap();
		resultMap.put("modifyFlag", "1");
		Map map = Util.request2Map(request);
		Map userInf = (Map)request.getSession().getAttribute("user");
		String dbOldPwd = (String)userInf.get("password");
		String reqOldPwd = Util.encrypt((String)map.get("oldPassword"));
		if (!dbOldPwd.equals(reqOldPwd)) {
			resultMap.put("errCode", "MP001");			
		} else {
			try{
				map.putAll(userInf);
				map.put("newPassword", Util.encrypt((String)map.get("newPassword1")));
				userDao.updateUserPwd(map);
				userInf.put("password", Util.encrypt((String)map.get("newPassword1")));
			}catch (Exception e) {
				resultMap.put("errCode", e.getMessage());
			}
			
		}
		
		return new ModelAndView(success, resultMap);
	}

}
