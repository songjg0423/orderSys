package com.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Util {
	
	private static Map map = new HashMap();
	
	public static Map parseXml(String xml){
		try {
			Document document = DocumentHelper.parseText(xml);
			Element rootElement = document.getRootElement();
			List<Element> elements = rootElement.elements();
			Map result = new HashMap();
			for (Element element : elements) {
				String key = element.getName();
				String value = element.getText();
				result.put(key, value);
			}
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void put(InputStream in){
		map.put("1", in);
	}
	
	public static InputStream get(){
		return (InputStream)map.get("1");
	}

}
