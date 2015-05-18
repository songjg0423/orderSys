package com.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class TextView extends AbstractView {
	
	public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=utf-8";

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String message = (String)model.get("Content");
		response.setContentType(DEFAULT_CONTENT_TYPE);
		try {
			response.getWriter().print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
