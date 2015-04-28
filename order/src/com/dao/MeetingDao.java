package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MeetingDao {
	
	private SqlMapClient sqlMapClient;
	
	private int pageSize;
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public void qryAvaRoom(Map map) throws Exception {

		try {
			List list = this.sqlMapClient.queryForList("qryAvaRoom", map);
			int pageNum = Integer.parseInt((String)map.get("pageNum"));
			int startIndex = (pageNum -1)*pageSize;
			int endIndex = startIndex + pageSize;
			if (list.size() < endIndex + 1) {
				endIndex = list.size();
			}
			map.put("recordCount", list.size());
			int pageCount = list.size()/pageSize;
			if (list.size() % pageSize != 0) {
				pageCount++;
			}
			map.put("pageCount", pageCount);
			map.put("roomList", list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	
	}
	
	public void addMeeting(Map map) throws Exception {
		try {
			this.sqlMapClient.startTransaction();
			Integer meetingseq = (Integer)this.sqlMapClient.insert("insertMeeting", map);
			List people = (List)map.get("people");
			for (int i = 0; i < people.size(); i++) {
				Map peopleMap = (Map)people.get(i);
				peopleMap.put("meetingseq", meetingseq);
				this.sqlMapClient.insert("insertpeople", peopleMap);
			}
			this.sqlMapClient.commitTransaction();
			
		}catch (Exception e) {			
			e.printStackTrace();
			throw new Exception("RA001");
		}finally {
			this.sqlMapClient.endTransaction();
		}
	}
	
	public void qryMeeting(Map map) throws Exception {
		try {
			List list = this.sqlMapClient.queryForList("qrymeeting", map);
			int pageNum = Integer.parseInt((String)map.get("pageNum"));
			int startIndex = (pageNum -1)*pageSize;
			int endIndex = startIndex + pageSize;
			if (list.size() < endIndex + 1) {
				endIndex = list.size();
			}
			map.put("recordCount", list.size());
			int pageCount = list.size()/pageSize;
			if (list.size() % pageSize != 0) {
				pageCount++;
			}
			map.put("pageCount", pageCount);
			map.put("meetingList", list);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}
	
	public void cancelMeeting(Map map) throws Exception {
		try {
			this.sqlMapClient.update("cancelMeeting", map);
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}
	
	public void qryMeetingDetail(Map map) throws Exception {
		try {
			Map meetingInf = (Map)this.sqlMapClient.queryForObject("qryMeetingBySeq", map);
			List peopleList = (List)this.sqlMapClient.queryForList("qryMeetingPeople", map);
			map.putAll(meetingInf);
			map.put("peopleList", peopleList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}
	
	public void qryAllAvaRoom(Map map) throws Exception {

		try {
			List list = this.sqlMapClient.queryForList("qryChangeAvaRoom", map);
			map.put("roomList", list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	
	}

	public void changeRoom(Map map) throws Exception {

		try {
			this.sqlMapClient.update("changeRoom", map);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	
	}
}
