package com.example.cabaike_lijing.url;

public class Url {
	// 获取最新apk版本路径
	public static final String UPAPKPATH = "http://baike.maimaicha.com/getversion.ashx";
	// 首页数据路径(头条里面的三张图)
	public static final String HOMEPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow";
	// 头条数据路径(头条中的list)
	public static final String HEADLINEPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getHeadlines";
	// 百科，咨询，经营，数据的前半部分
	public static final String FRONT = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType";
	//搜索的前半部分   // 搜索 post： rows=10&page=1&search=tea
	public static final String SEARCHPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.searcListByTitle";
	
	//得到(rows=15&page=0)百科<&type=16>，咨询<&type=52>，经营<&type=53>，数据<&type=54>的解析网址的方法
	public static String getURL(int page, int type) {
		String url = FRONT + "&rows=15&page=" + page + "&type=" + type + "";
		return url;
	}

	public static String getUrlForSearch(String search){
		String url = SEARCHPATH+"&search="+search;
		return url;		
	}
	
	// 内容新页
	public static final String NEWCONTENTPATH = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getNewsContent";


}