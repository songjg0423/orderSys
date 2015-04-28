package com.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.common.Util;

public class Time extends TagSupport{
	
	private String name;
	
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int doStartTag() throws JspException{
		JspWriter jw = pageContext.getOut();
		try {
			String idStr = Util.isEmpty(this.id) ? "" : "id='" + id + "'";
			String selectStr = "<select name='" + name + "'" + idStr + ">";
			jw.print(selectStr);
			jw.print("<option value='00'>0分</option>");
			jw.print("<option value='05'>5分</option>");
			jw.print("<option value='10'>10分</option>");
			jw.print("<option value='15'>15分</option>");
			jw.print("<option value='20'>20分</option>");
			jw.print("<option value='25'>25分</option>");
			jw.print("<option value='30'>30分</option>");
			jw.print("<option value='35'>35分</option>");
			jw.print("<option value='40'>40分</option>");
			jw.print("<option value='45'>45分</option>");
			jw.print("<option value='50'>50分</option>");
			jw.print("<option value='55'>55分</option>");
			jw.print("</select>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}


}
