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
import com.dao.PriceDao;
import com.dao.RoomDao;

public class MeetingBook implements Controller{
	
	private String success;
	
	public void setSuccess(String success) {
		this.success = success;
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
		String beginDate = (String)map.get("BeginDate");
		String beginHour = (String) map.get("beginHour");
		String beginMinute = (String) map.get("beginMinute");
		String beginDateStr = beginDate + " " + beginHour + ":" + beginMinute + ":00";
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		Date RealbeginDate = new Date(sdf.parse(beginDateStr).getTime());
		String endDate = (String)map.get("EndDate");
		String endHour = (String) map.get("endHour");
		String endMinute = (String) map.get("endMinute");
		String endDateStr = endDate + " " + endHour + ":" + endMinute + ":00";
		Date realEndDate = new Date(sdf.parse(endDateStr).getTime());
//		
//		map.put("starttime", RealbeginDate);
//		map.put("endtime", realEndDate);
		
		map.put("starttimestr", beginDateStr);
		map.put("endtimestr", endDateStr);
		roomDao.queryRoomInfByRoomseq(map);
		
		long mseconds = realEndDate.getTime() - RealbeginDate.getTime();
		BigDecimal dayPrice = new BigDecimal("0");
		int days = (int)mseconds/(24 * 3600 * 1000);
		if (days > 0) {
			Map dayPriceMap = new HashMap();
			dayPriceMap.put("pricetype", "2");
			dayPriceMap.put("roomseq", map.get("roomseq"));
			dayPrice = this.priceDao.qryPrice(dayPriceMap).multiply(new BigDecimal(days));
			
		}
		long hSeconds = (int)mseconds%(24 * 3600 * 1000);
		BigDecimal hprice = new BigDecimal("0");
		if (hSeconds > 0) {
			int hours = (int)hSeconds/(3600 * 1000);
			if (hSeconds%(3600 * 1000) != 0) hours++;
			if (hours > 0) {
				Map hourPriceMap = new HashMap();
				hourPriceMap.put("pricetype", "1");
				hourPriceMap.put("roomseq", map.get("roomseq"));
				hprice = this.priceDao.qryPrice(hourPriceMap).multiply(new BigDecimal(hours));
			}
		}
		BigDecimal price = dayPrice.add(hprice).setScale(2);
		map.put("price", price);
		
		return new ModelAndView(success, map);
	}

}
