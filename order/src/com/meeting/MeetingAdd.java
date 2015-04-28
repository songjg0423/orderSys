package com.meeting;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.MeetingDao;

public class MeetingAdd implements Controller {
	
	private String success;
	
	private MeetingDao meetingDao;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public MeetingDao getMeetingDao() {
		return meetingDao;
	}

	public void setMeetingDao(MeetingDao meetingDao) {
		this.meetingDao = meetingDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		Map map = Util.request2Map(request);
		meetingDao.addMeeting(map);
		return new ModelAndView(success, map);
	}

}
