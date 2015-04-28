package com.meeting;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.common.Util;
import com.dao.MeetingDao;

public class RoomQry implements Controller {

	private String success;

	private String error;
	
	private MeetingDao meetingDao;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
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
		String beginDate = (String)map.get("beginDate");
		String beginHour = (String) map.get("beginHour");
		String beginMinute = (String) map.get("beginMinute");
		String beginDateStr = beginDate + " " + beginHour + ":" + beginMinute + ":00";
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		Timestamp RealbeginDate = new Timestamp(sdf.parse(beginDateStr).getTime());
		String endDate = (String)map.get("endDate");
		String endHour = (String) map.get("endHour");
		String endMinute = (String) map.get("endMinute");
		String endDateStr = endDate + " " + endHour + ":" + endMinute + ":00";
		Timestamp realEndDate = new Timestamp(sdf.parse(endDateStr).getTime());
		String perCount = (String)map.get("percount");
		String roomLevel = Util.getRoomLevel(Integer.parseInt(perCount));
		map.put("roomLevel", roomLevel);
		map.put("starttime", RealbeginDate);
		map.put("endtime", realEndDate);
		meetingDao.qryAvaRoom(map);
		return new ModelAndView(success, map);
	}

}
