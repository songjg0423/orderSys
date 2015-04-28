package com.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.common.Util;

public class Hour extends TagSupport {
	
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
			jw.print("<option value='00'>0点</option>");
			jw.print("<option value='01'>1点</option>");
			jw.print("<option value='02'>2点</option>");
			jw.print("<option value='03'>3点</option>");
			jw.print("<option value='04'>4点</option>");
			jw.print("<option value='05'>5点</option>");
			jw.print("<option value='06'>6点</option>");
			jw.print("<option value='07'>7点</option>");
			jw.print("<option value='08'>8点</option>");
			jw.print("<option value='09'>9点</option>");
			jw.print("<option value='10'>10点</option>");
			jw.print("<option value='11'>11点</option>");
			jw.print("<option value='12'>12点</option>");
			jw.print("<option value='13'>13点</option>");
			jw.print("<option value='14'>14点</option>");
			jw.print("<option value='15'>15点</option>");
			jw.print("<option value='16'>16点</option>");
			jw.print("<option value='17'>17点</option>");
			jw.print("<option value='18'>18点</option>");
			jw.print("<option value='19'>19点</option>");
			jw.print("<option value='20'>20点</option>");
			jw.print("<option value='21'>21点</option>");
			jw.print("<option value='22'>22点</option>");
			jw.print("<option value='23'>23点</option>");
			jw.print("</select>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
