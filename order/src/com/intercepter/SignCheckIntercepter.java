package com.intercepter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SignCheckIntercepter implements HandlerInterceptor {
	
	private String token;
	
	public void setToken(String token){
		this.token = token;
	}
	
	private Log logger = LogFactory.getLog(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*if (true) {
			return true;
		}*/
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String[] arrTmp = {this.token, timestamp, nonce};
		Arrays.sort(arrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrTmp.length; ++i) {
			sb.append(arrTmp[i]);
		}
		String plain = sb.toString();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(plain.getBytes());
			String mySignature = bytes2Hex(md.digest());
			return mySignature.equals(signature);
		} catch(NoSuchAlgorithmException e) {
			this.logger.error(e.getMessage(), e);
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
	
	private  String bytes2Hex(byte[] bts) {
		String ret = "";
		String tmp = null;
		for (int i = 0; i < bts.length; ++i) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (1 == tmp.length()) {
				ret += "0";
			}
			ret += tmp;
		}
		
		return ret;
	}

}
