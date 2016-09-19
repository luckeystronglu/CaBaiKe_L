package com.example.cabaike_lijing.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlToJsonString {
	
	// 联网得到传进来的url的json字符串
	public static String getJsonStringFromURL(String path) {
		String jsonString = "";
		BufferedReader br = null;
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String cache = "";
			while ((cache = br.readLine()) != null) {
				jsonString += cache;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonString;
	}

}
