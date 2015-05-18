package com.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.util.Util;

public class AccessAction implements Controller {
	
	private Log logger = LogFactory.getLog(AccessAction.class);

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		if ("GET".equals(method)) {
			Map model = new HashMap();
			model.put("Content", request.getParameter("echostr"));
			return new ModelAndView("textView", model);
		}
		
		int contentLength = request.getContentLength();
        byte[] content = new byte[contentLength];
        InputStream in = request.getInputStream();
        in.read(content);

        String xml = new String(content, "utf-8");
        this.logger.info(xml);
		Map requestData = Util.parseXml(xml);
		
		StringBuilder sb = new StringBuilder("");
		sb.append("<xml>");
		sb.append("<ToUserName>");
		sb.append(requestData.get("FromUserName"));
		sb.append("</ToUserName>");
		
		sb.append("<FromUserName>");
		sb.append(requestData.get("ToUserName"));
		sb.append("</FromUserName>");
		
		sb.append("<CreateTime>");
		sb.append(System.currentTimeMillis());
		sb.append("</CreateTime>");
		
		sb.append("<MsgType>");
		sb.append("text");
		sb.append("</MsgType>");
		
		sb.append("<Content>");
		sb.append("http://songjg0423.xicp.net/order1/html/index.html");
		sb.append("</Content>");
		sb.append("</xml>");
		
		Map model = new HashMap();
		model.put("xml", sb.toString());
		return new ModelAndView("xmlView", model);
	}

}
