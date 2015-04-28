package com.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RoomDao {
	private SqlMapClient sqlMapClient;

	private int pageSize;

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void addRoom(Map map) throws Exception {

		try {
			this.sqlMapClient.startTransaction();
			Integer roomseq = (Integer)this.sqlMapClient.insert("insertRoom", map);
			map.put("meetingroomseq", roomseq);
			this.sqlMapClient.insert("insertDayPrice", map);
			this.sqlMapClient.insert("insertHourPrice", map);
			this.sqlMapClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}finally {
			this.sqlMapClient.endTransaction();
		}

	}

	public Map qryRoomById(Map map) throws Exception {
		try {
			return (Map) this.sqlMapClient.queryForObject("qryRoomById", map);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}

	public List queryRoomList(Map map) throws Exception {
		try {
			List list = (List) this.sqlMapClient.queryForList("qryRoomList",
					map);
			int pageNum = Integer.parseInt((String) map.get("pageNum"));
			int startIndex = (pageNum - 1) * pageSize;
			int endIndex = startIndex + pageSize;
			if (list.size() < endIndex + 1) {
				endIndex = list.size();
			}
			map.put("recordCount", list.size());
			int pageCount = list.size() / pageSize;
			if (list.size() % pageSize != 0) {
				pageCount++;
			}
			map.put("pageCount", pageCount);
			return list.subList(startIndex, endIndex);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}

	}

	public void deleteRoom(Map map) throws Exception {
		try {
			this.sqlMapClient.delete("deleteRoomByRoomseq", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}

	public void queryRoomInfByRoomseq(Map map) throws Exception {
		try {
			Map result = (Map) this.sqlMapClient.queryForObject(
					"queryRoomInfByRoomseq", map);
			map.putAll(result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}

	public void updateRoomInf(Map map) throws Exception {
		try {
			this.sqlMapClient.startTransaction();
			this.sqlMapClient.update("upateRoomInf", map);
			this.sqlMapClient.update("updateDayPrice", map);
			this.sqlMapClient.update("updateHourPrice", map);
			this.sqlMapClient.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("RA001");
		} finally {
			this.sqlMapClient.endTransaction();
		}
	}

}
