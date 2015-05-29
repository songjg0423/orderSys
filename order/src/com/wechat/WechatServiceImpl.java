package com.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.orm.ibatis.SqlMapClientOperations;
import org.springframework.transaction.support.TransactionTemplate;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class WechatServiceImpl {
	private static Log log = LogFactory.getLog(WechatServiceImpl.class);

	private String appID;

	private String appSecret;
	
	public void setAppID(String appID) {
		this.appID = appID;
	}
	
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	private ObjectMapper objectMapper = new ObjectMapper();

	public String getAccessToken(String code) throws Exception {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=" + this.appID + "&secret=" + this.appSecret
				+ "&code=" + code + "&grant_type=authorization_code";
		log.info(url);
		Map map = this.resultForGet(url);

		if (map == null || map.containsKey("errcode")) {
			throw new Exception("get_weixin_openid_fail");
		}
		return (String) map.get("openid");
	}

	private String getStringFromHttp(HttpEntity entity) {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "utf-8"));

			String temp = null;
			while (null != (temp = reader.readLine())) {
				buffer.append(temp);
			}
		} catch (IllegalStateException e) {
			log.error("get result data failed!", e);
		} catch (IOException e) {
			log.error("get result data failed!", e);
		}

		return buffer.toString();
	}

	public Map resultForPost(String url, Map params) throws Exception {
		HttpClient httpClient = this.getHttpClient();

		HttpResponse response = null;
		try {
			String jsonObject = objectMapper.writeValueAsString(params);

			StringEntity entity = new StringEntity(jsonObject.toString(),
					"utf-8");
			entity.setContentType("application/json;charset=utf-8");

			HttpPost httpost = new HttpPost(url);
			httpost.addHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=utf-8");
			httpost.setEntity(entity);

			response = httpClient.execute(httpost);
		}catch (Exception e) {
			log.error("httpclient method [get] failed!", e);
			throw new Exception(
					"communicaton_with_weixin_fail");
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		String result = getStringFromHttp(response.getEntity());
		log.info("result data: " + result);
		try {
			return this.objectMapper.readValue(result, HashMap.class);
		} catch (Exception e) {
			throw new Exception(
					"communicaton_with_weixin_fail");
		}

	}

	public Map<String, Object> resultForGet(String url) throws Exception {
		HttpClient httpClient = this.getHttpClient();

		HttpResponse response = null;
		try {
			HttpGet httpget = new HttpGet(url);
			response = httpClient.execute(httpget);
		} catch (ClientProtocolException e) {
			log.error("Please check your provided http address [" + url + "]",
					e);
			throw new Exception(
					"communicaton_with_weixin_fail");
		} catch (IOException e) {
			log.error("httpclient method [get] failed!", e);
			throw new Exception(
					"communicaton_with_weixin_fail");
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		String result = getStringFromHttp(response.getEntity());
		log.info("result data: " + result);
		try {
			return this.objectMapper.readValue(result, HashMap.class);
		} catch (Exception e) {
			throw new Exception(
					"communicaton_with_weixin_fail");
		}
	}


	public HttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		return httpClient;
	}


}
