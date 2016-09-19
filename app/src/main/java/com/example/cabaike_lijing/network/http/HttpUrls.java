package com.example.cabaike_lijing.network.http;

/**
 * 所有的http请求地址
 */
public class HttpUrls {

	/**
	 * 根路径
	 */
	public String WEB_ROOT;
	
	public static String NEW_HOME_ROOT = "/api?";
	
	//NEWCONTENTPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6
	//&format=json&method=news.getNewsContent"
	
	/**
	 * 默认地址
	 */
	public HttpUrls() {
		this("http://sns.maimaicha.com");
	}

	public HttpUrls(String webRoot) {
		WEB_ROOT = webRoot;	
		NEW_HOME_ROOT = WEB_ROOT + NEW_HOME_ROOT;
	}

}
