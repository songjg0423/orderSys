package com.meetingroom;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.RoomDao;

public class MeetingRoomAdd implements Controller{
	
	private RoomDao roomDao;
	
	private String success;
	
	private String error;
	
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	public void setSuccess(String success) {
		this.success = success;
	} 
	
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		Map result = new HashMap();
		String view = this.success;
		Map map = Util.request2Map(request);
		try {
			Map roomInf = roomDao.qryRoomById(map);
			if (roomInf != null && !Util.isEmpty((String)roomInf.get("id"))) {
				throw new Exception("RA002");
			} else {
				roomDao.addRoom(map);
			}
			
		}catch (Exception e) {
			view = this.error;
			map.put("errCode", e.getMessage());
		}
		
		return new ModelAndView(view, map);
	}

}
