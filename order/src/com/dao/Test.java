package com.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class Test {

	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public List getTableInf()throws  DataAccessException{
		try {
//			Map map2 = new HashMap();
//			map2.put("name", "userseq");
//			Integer i = (Integer) this.sqlMapClient.queryForObject("getuserseq",map2);
//			System.out.println(i);
//			Map map1 = new HashMap();
//			map1.put("name", "userseq");
//			this.sqlMapClient.insert("updateuserseq",map1);
			
						
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "1");
			return this.sqlMapClient.queryForList("getTableInf",map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
