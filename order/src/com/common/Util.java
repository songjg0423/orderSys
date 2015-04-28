package com.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;

public class Util {

	public static String encrypt(String plainText) {

		String str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
//			System.out.println("result: " + buf.toString());// 32λ�ļ���
//			System.out.println("result: " + buf.toString().substring(8, 24));// 16λ�ļ���
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
		return str;

	}
	
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static Map request2Map(ServletRequest request) {
//		Map<String, String> result = new HashMap<String, String>();
//		Enumeration enumeration = request.getParameterNames();
//		while (enumeration.hasMoreElements()) {
//			Object o = enumeration.nextElement();
//			String key = null;
//			if (o instanceof String) {
//				key = (String) o;
//			}
//			String value = request.getParameter(key);
//			result.put(key, value);
//		}
//		return result;
		Map result = new HashMap();
		Map map = request.getParameterMap();
		Iterator keyIt = map.keySet().iterator();
		while(keyIt.hasNext()) {
			String key = keyIt.next().toString();
			
			 Pattern p = Pattern.compile("(.*)\\[(\\d)\\]\\.(.*)");
			 Matcher m = p.matcher(key);
			if (m.matches()) {
				String key1 = m.group(1);
				int index1 = Integer.parseInt(m.group(2));
				String mapValue = request.getParameter(key);
				String mapKey = m.group(3);
				List list1 = (List)result.get(key1);
				if (list1 == null) {
					list1 = new ArrayList();
					result.put(key1, list1);
				}
				setListSize(list1, index1 + 1);
				Map map1 = (Map)list1.get(index1);
				if (map1 == null) {
					map1 = new HashMap();	
				}
				map1.put(mapKey, mapValue);
				list1.set(index1, map1);
			} else {
				String[] values = (String[])map.get(key);
			    if (values != null && values.length != 0 && values.length == 1) {
			    	result.put(key, values[0]);
			    } else {
			    	result.put(key, Arrays.asList(values));
			    }
			}
		    
		}
		
		System.out.println();
		return result;
	}
	
	public static void setListSize(List list, int size) {
//		if (list == null) {
//			list = new ArrayList();
//		}
		if (list.size() < size) {
			for (int i = list.size(); i < size; i++) {
				list.add(null);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(Util.encrypt("123456"));
		
		
//		Map result = new HashMap();
//		Map map = new HashMap();
//		List temList1 = new ArrayList();
//		List temList2 = new ArrayList();
//		List temList3 = new ArrayList();
//		List temList4 = new ArrayList();
//		List temList5 = new ArrayList();
//		List temList6 = new ArrayList();
//		temList1.add("tom");
//		temList2.add("1234");
//		temList3.add("jim");
//		temList4.add("4321");
//		temList5.add("lilei");
//		temList6.add("0000");
//		
//		map.put("people[1].name", temList3);
//		map.put("people[1].phone", temList4);
//		map.put("people[0].name", temList1);
//		map.put("people[0].phone", temList2);
//		map.put("people[2].name", temList5);
//		map.put("people[2].phone", temList6);
//		
//		Iterator keyIt = map.keySet().iterator();
//		while(keyIt.hasNext()) {
//			String key = keyIt.next().toString();
//			
//			 Pattern p = Pattern.compile("(.*)\\[(\\d)\\]\\.(.*)");
//			 Matcher m = p.matcher(key);
//			if (m.matches()) {
//				String key1 = m.group(1);
//				int index1 = Integer.parseInt(m.group(2));
//				String mapValue = (String)((List)map.get(key)).get(0);
//				String mapKey = m.group(3);
//				List list1 = (List)result.get(key1);
//				if (list1 == null) {
//					list1 = new ArrayList();
//					result.put(key1, list1);
//				}
//				setListSize(list1, index1 + 1);
//				Map map1 = (Map)list1.get(index1);
//				if (map1 == null) {
//					map1 = new HashMap();	
//					list1.set(index1, map1);
//				}
//				map1.put(mapKey, mapValue);
//				
//			} else {
//				List values = (List)map.get(key);
//			    if (values != null && values.size() != 0 && values.size() == 1) {
//			    	result.put(key, values.get(0));
//			    } else {
//			    	result.put(key, values);
//			    }
//			}
//		    
//		}
//		
//		System.out.println();
		
		
		
		
	}
	
	public static String getRoomLevel(int count) {
		String resutl = "1";
		if (count <= 5) {
			resutl = "1";
		}else if (count > 5 && count <= 10) {
			resutl = "2";
		} else if (count > 10 && count <= 20) {
			resutl = "3";
		} else {
			resutl = "4";
		}
		return resutl;
	}
}
