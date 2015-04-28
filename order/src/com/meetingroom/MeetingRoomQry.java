package com.meetingroom;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.RoomDao;

public class MeetingRoomQry implements Controller {
	
	private String success;
	
	private RoomDao roomDao;
	
	public void setSuccess(String success) {
		this.success = success;
	}
	
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = Util.request2Map(request);
		List roomList = roomDao.queryRoomList(map);
		map.put("roomList", roomList);
		return new ModelAndView(success, map);
	}

}
