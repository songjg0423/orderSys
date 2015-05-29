package com.intercepter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class JsonParseIntercepter implements HandlerInterceptor {

	private Log logger = LogFactory.getLog(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*
		 * if (true) { return true; }
		 */
		if (request.getContentType().indexOf("application/json") >= 0) {
			// Thread.sleep(1000);
			/*InputStream in = request.getInputStream();
			byte[] bytes = new byte[request.getContentLength()];

			in.read(bytes);*/
			byte[] bytes = this.getStream(request);
			if (bytes == null) {
				return true;
			}
			String jsonStr = new String(bytes, "utf-8");
			if (jsonStr == null || jsonStr.trim().length() == 0) {
				return true;
			}
			ObjectMapper objectMapper = new ObjectMapper();
			if (jsonStr.startsWith("[")) {
				List list = objectMapper.readValue(jsonStr, List.class);
				request.setAttribute("List", list);
			} else {
				System.out.println(new Date());
				Map map = objectMapper.readValue(jsonStr, Map.class);
				if (map != null) {
					Iterator it = map.keySet().iterator();
					while (it.hasNext()) {
						Object key = it.next();
						request.setAttribute(key.toString(), map.get(key));
					}
				}
			}

		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	private byte[] getStream(HttpServletRequest request) throws Exception {
		int contentLength = request.getContentLength();

		byte[] content = new byte[contentLength];

		int offset = 0;
		while (offset < contentLength) {
			try {
				int realLength = request.getInputStream().read(content, offset,
						contentLength - offset);
				if (realLength == -1) {
					return null;
				}
				offset += realLength;
			} catch (IOException e) {
				throw new Exception("request_isnot_a_valid_stream");
			}

		}

		return content;
	}

}
