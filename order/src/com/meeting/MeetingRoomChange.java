package com.meeting;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.MeetingDao;

public class MeetingRoomChange implements Controller {
	
	private String success;
	
	private MeetingDao meetingDao;
	
	public void setSuccess(String success) {
		this.success = success;
	}
	
	public void setMeetingDao(MeetingDao meetingDao) {
		this.meetingDao = meetingDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		Map map = Util.request2Map(request);
		meetingDao.qryMeetingDetail(map);
		Map map1 = new HashMap();
		map1.put("meetingseq", map.get("meetingseq"));
		map1.put("roomLevel", Util.getRoomLevel((Integer)map.get("percount")));
		map1.put("starttime", map.get("starttime"));
		map1.put("endtime", map.get("endtime"));
		meetingDao.qryAllAvaRoom(map1);
		map.put("roomList", map1.get("roomList"));
		return new ModelAndView(success, map);
	}

}
