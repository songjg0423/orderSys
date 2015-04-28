package com.dao;

import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PeopleDao {
	
	private SqlMapClient sqlMapClient;
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public void deletePerson(Map map) throws Exception {
		try {
			this.sqlMapClient.update("deletePeople", map);
			this.sqlMapClient.update("updatePersonCount", map);
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}
	
	public void addPerson(Map map) throws Exception {
		try {
			this.sqlMapClient.update("insertpeople", map);
			this.sqlMapClient.update("updatePersonCount1", map);
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}

}
