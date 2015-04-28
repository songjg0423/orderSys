package com.meeting;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.MeetingDao;

public class MeetingQry implements Controller{
	
	private String success;
	
	private MeetingDao meetingDao;
	
	public void setSuccess(String success) {
		this.success = success;
	}
	
	public void setMeetingDao (MeetingDao meetingDao) {
		this.meetingDao = meetingDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		Map map = Util.request2Map(request);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		if (!Util.isEmpty((String)map.get("beginDate"))){
			String beginDate = (String)map.get("beginDate");
			String beginHour = (String) map.get("beginHour");
			String beginMinute = (String) map.get("beginMinute");
			String beginDateStr = beginDate + " " + beginHour + ":" + beginMinute + ":00";		
			Timestamp RealbeginDate = new Timestamp(sdf.parse(beginDateStr).getTime());
			map.put("starttime", RealbeginDate);
		}
		
		if (!Util.isEmpty((String)map.get("endDate"))){
			String endDate = (String)map.get("endDate");
			String endHour = (String) map.get("endHour");
			String endMinute = (String) map.get("endMinute");
			String endDateStr = endDate + " " + endHour + ":" + endMinute + ":00";
			Timestamp realEndDate = new Timestamp(sdf.parse(endDateStr).getTime());
			map.put("endtime", realEndDate);
		}
		this.meetingDao.qryMeeting(map);
		return new ModelAndView(success, map);
	}

}
