package com.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.Util;

public class Radio extends TagSupport {

	private String key;
	
	private String checkedValue;

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setCheckedValue(String checkedValue) {
		
		this.checkedValue = checkedValue;
	}
	
	public int doStartTag() throws JspException{
//		pageContext.getServletContext().get
		String attrName = "org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet";
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext(), attrName);
		Properties props = (Properties) wac.getBean("configproperties");
		JspWriter jw = pageContext.getOut();
		Set keySet = props.keySet();
		Iterator it = keySet.iterator();
 
		while (it.hasNext()) {
			String key = (String)it.next();
			
			if (key.startsWith(this.key + ".")) {
				try {
				String value = key.substring(this.key.length() + 1);
				String outStr = "<input type='radio' name='" + this.key + "' value='" + value + "' />" + new String(props.getProperty(key).getBytes("iso8859-1"),"UTF-8");
				if (!Util.isEmpty(this.checkedValue) && this.checkedValue.equals(value)) {
					outStr = "<input type='radio' name='" + this.key + "' value='" + value + "' checked />" + new String(props.getProperty(key).getBytes("iso8859-1"),"UTF-8");
				}
				jw.print(outStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return 0;
	}

}
