package com.tag;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.Util;

public class ShowMsg extends TagSupport {
	
	private String key;
	
	private String namespace;
	
	private String defaultValue;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public void setDefaultValue(String value) {
		this.defaultValue = value;
	}

	public int doStartTag() throws JspException {
		String attrName = "org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet";
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext(), attrName);
		Properties prop = (Properties) wac.getBean("configproperties");
		String keyStr = this.key;
		if (!Util.isEmpty(namespace)) {
			keyStr = namespace + "." + this.key;
		}
		String msg = prop.getProperty(keyStr);
		if (Util.isEmpty(msg)) {
			msg = this.defaultValue;
		}
		
		if (Util.isEmpty(msg)) {
			msg = "";
		}
		JspWriter jw = pageContext.getOut();
		try {
			jw.print(new String(msg.getBytes("iso8859-1"), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
