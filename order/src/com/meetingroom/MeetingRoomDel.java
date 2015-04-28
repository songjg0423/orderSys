package com.meetingroom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.RoomDao;

public class MeetingRoomDel implements Controller{
	private RoomDao roomDao;
	
	private String success;
	
	public void setRoomDao(RoomDao roomDao) {
		
		this.roomDao = roomDao;
	}
	
	public void setSuccess(String success) {
		
		this.success = success;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = Util.request2Map(request);
		roomDao.deleteRoom(map);
		return new ModelAndView(success, null);
	}

}
