package com.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UserDao {
	
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public void updateUserPwd(Map map) throws Exception{
		try {
			this.sqlMapClient.update("updateUserPwd", map);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("MP002");
		}
		
	}

}
