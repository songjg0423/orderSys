package com.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {
	
	private MessageSource messageSource;
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();
		Map resultMap = new HashMap();
		Map json = new HashMap();
		try {
			String prop = messageSource.getMessage(ex.getMessage(), null, null);
			json.put("ReturnCode", ex.getMessage());
			json.put("RetMessage", prop);
		} catch (Exception e) {
			json.put("ReturnCode", "error.default");
			json.put("RetMessage", this.messageSource.getMessage("error.default", null, null));
		}
		resultMap.put("Json", json);
		return new ModelAndView("jsonView", resultMap);
	}


}
