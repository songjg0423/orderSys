package com.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MyTestDao {
	
	private SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public void qryTestId(Map map) throws Exception {
		try {
			Map map1 = (Map)this.sqlMapClient.queryForObject("qryTestId");
			map.putAll(map1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}
	
	public void updateTestId(Map map) throws Exception {
		try {
			this.sqlMapClient.update("updateTestId", map);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}

}
