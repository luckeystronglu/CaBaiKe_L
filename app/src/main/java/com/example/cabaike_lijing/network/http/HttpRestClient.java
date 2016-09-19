package com.example.cabaike_lijing.network.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;



/*Make asynchronous HTTP requests, handle responses in anonymous callbacks 
 HTTP requests happen outside the UI thread 
 Requests use a threadpool to cap concurrent resource usage 
 GET/POST params builder (RequestParams) 
 Multipart file uploads with no additional third party libraries 
 Tiny size overhead to your application, only 19kb for everything 
 Automatic smart request retries optimized for spotty mobile connections 
 Automatic gzip response decoding support for super-fast requests 
 Optional built-in response parsing into JSON (JsonHttpResponseHandler) 
 Optional persistent cookie store, saves cookies into your app's SharedPreferences
 */

/**
 * 
 * 所有执行http请求操作调用此类
 */
public class HttpRestClient {
	private static HttpUrls mHttpUrls = new HttpUrls();
	/**
	 * 上传分隔符
	 */
	/*
	 * public static final String BOUNDARY =
	 * "------------------------- --265001916915724";
	 *//**
	 * 上传文件结束标记
	 */
	/*
	 * private static final String FILEUPLOAD_ENDTAG = "\r\n--" + BOUNDARY +
	 * "--" + "\r\n";
	 */

	public static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();

	public static HttpUrls getmHttpUrls() {
		return mHttpUrls;
	}

	public static void setmHttpUrls(HttpUrls mHttpUrls) {
		HttpRestClient.mHttpUrls = mHttpUrls;
	}

	//NEWCONTENTPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6
	//&format=json&method=news.getNewsContent"
	public static void doHttpNewsHome(Context context,String id,AsyncHttpResponseHandler responseHandler){
		RequestParams requestParams = new RequestParams();
		requestParams.put("apikey", "b4f4ee31a8b9acc866ef2afb754c33e6");		
		requestParams.put("format", "json");
		requestParams.put("method", "news.getNewsContent");
		requestParams.put("id", id);		
		mAsyncHttpClient.get(context,HttpUrls.NEW_HOME_ROOT,requestParams,responseHandler);
	}

}
