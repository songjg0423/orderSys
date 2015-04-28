package com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PriceDao {
	
	private SqlMapClient sqlMapClient;
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public BigDecimal qryPrice(Map map) throws Exception {
		try {
			Map result = (Map)this.sqlMapClient.queryForObject("qryPrice", map);
			BigDecimal price = ((BigDecimal)result.get("price")).multiply((BigDecimal)result.get("discount"));
			return price;
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new Exception("RA001");
		}
	}

}
