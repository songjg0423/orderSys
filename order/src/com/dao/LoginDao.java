package com.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LoginDao {
	
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@SuppressWarnings("unchecked")
	public Map login(Map<String, String> map) throws Exception {
		try {
			Map result = (Map)this.sqlMapClient.queryForObject("queryUserInfByUserId", map);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("UL001");
		}
	}

}
