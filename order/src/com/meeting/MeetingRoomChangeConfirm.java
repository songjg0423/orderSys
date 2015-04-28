package com.meeting;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.common.Util;
import com.dao.MeetingDao;
import com.dao.PriceDao;
import com.dao.RoomDao;

public class MeetingRoomChangeConfirm implements Controller {

	private String success;

	private MeetingDao meetingDao;

	public void setSuccess(String success) {
		this.success = success;
	}

	public void setMeetingDao(MeetingDao meetingDao) {
		this.meetingDao = meetingDao;
	}

	private RoomDao roomDao;

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	private PriceDao priceDao;

	public void setPriceDao(PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		Map map = Util.request2Map(request);
		// Map map1 = new HashMap();
		// map1.put("meetingseq", map.get("srcseq"));
		// meetingDao.qryMeetingDetail(map1);
		// map.putAll(map1);

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		Date startDate = new Date(sdf.parse((String) map.get("starttime"))
				.getTime());
		Date endDate = new Date(sdf.parse((String) map.get("endtime"))
				.getTime());

		long mseconds = endDate.getTime() - startDate.getTime();
		BigDecimal dayPrice = new BigDecimal("0");
		int days = (int) mseconds / (24 * 3600 * 1000);
		if (days > 0) {
			Map dayPriceMap = new HashMap();
			dayPriceMap.put("pricetype", "2");
			dayPriceMap.put("roomseq", map.get("targetseq"));
			dayPrice = this.priceDao.qryPrice(dayPriceMap).multiply(
					new BigDecimal(days));

		}
		long hSeconds = (int) mseconds % (24 * 3600 * 1000);
		BigDecimal hprice = new BigDecimal("0");
		if (hSeconds > 0) {
			int hours = (int) hSeconds / (3600 * 1000);
			if (hSeconds % (3600 * 1000) != 0)
				hours++;
			if (hours > 0) {
				Map hourPriceMap = new HashMap();
				hourPriceMap.put("pricetype", "1");
				hourPriceMap.put("roomseq", map.get("targetseq"));
				hprice = this.priceDao.qryPrice(hourPriceMap).multiply(
						new BigDecimal(hours));
			}
		}
		BigDecimal price = dayPrice.add(hprice).setScale(2);
		map.put("price", price);
		meetingDao.changeRoom(map);
		Map map1 = new HashMap();
		map1.put("meetingseq", map.get("srcseq"));
		map1.put("roomseq", map.get("targetseq"));
		meetingDao.qryMeetingDetail(map1);
		roomDao.queryRoomInfByRoomseq(map1);
		map.putAll(map1);
		return new ModelAndView(success, map);
	}

}
