package com.example.cabaike_lijing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cabaike_lijing.db.SQLiteUtils;
import com.example.cabaike_lijing.helper.UrlToJsonString;
import com.example.cabaike_lijing.url.IsConnNetwork;
import com.example.cabaike_lijing.url.Url;

import org.json.JSONException;
import org.json.JSONObject;

public class WebViewActivity extends Activity {

	String url;
	WebView webView;
	String wap_content;
	WebSettings bs;

	String id;
	String title;
	String source;
	String wap_thumb;
	String create_time;
	String nickname;

	SQLiteUtils utils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview);
		utils = new SQLiteUtils(WebViewActivity.this);

		webView = (WebView) findViewById(R.id.web_body);

		id = getIntent().getStringExtra("id");
		url = Url.NEWCONTENTPATH + "&id=" + id;
		title = getIntent().getStringExtra("title");
		source = getIntent().getStringExtra("source");
		wap_thumb = getIntent().getStringExtra("wap_thumb");
		create_time = getIntent().getStringExtra("create_time");
		nickname = getIntent().getStringExtra("nickname");

		// 增加历史记录：
		Cursor cursor = utils.queryHistory();
		ContentValues values = new ContentValues();
		values.put("_id", id);
		values.put("title", title);
		values.put("source", source);
		values.put("wap_thumb", wap_thumb);
		values.put("create_time", create_time);
		values.put("nickname", nickname);
		utils.addHistory(values);

		TextView tv_body_title = (TextView) findViewById(R.id.tv_body_1);
		tv_body_title.setText(title);
		TextView tv_body_detail = (TextView) findViewById(R.id.tv_body_2);
		tv_body_detail.setText("时间：" + create_time + "  " + "来源：" + source);

		if (IsConnNetwork.isNetworkConnected(WebViewActivity.this)) {
			new MyTask(WebViewActivity.this).execute(url);
		} else {
			Toast.makeText(WebViewActivity.this, "网络连接异常", Toast.LENGTH_LONG)
					.show();
		}

		bs = webView.getSettings();
		bs.setBuiltInZoomControls(true);
	}

	public void bottom_click(View view) {
		switch (view.getId()) {
		case R.id.ib_body_back:
			finish();
			break;
		// 分享到短信
		case R.id.ib_body_share:
			if(IsConnNetwork.isNetworkConnected(WebViewActivity.this)){
				Uri uri = Uri.parse("smsto:");
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SENDTO);
				intent.setData(uri);
				intent.putExtra("sms_body", "title" + "\r\n" + url);
				startActivity(intent);
			}else{
				Toast.makeText(WebViewActivity.this, "该功能需要联网……", Toast.LENGTH_SHORT).show();
			}
			

			break;
		case R.id.ib_body_collect:
			Cursor cursor = utils.queryCollection();
			boolean flag = false;
			if (cursor.getCount() == 0) {
				flag = true;
			} else {
				while (cursor.moveToNext()) {
					if (cursor.getString(cursor.getColumnIndex("_id")).equals(
							id)) {
						Toast.makeText(WebViewActivity.this, "您已经添加过该收藏",
								Toast.LENGTH_LONG).show();
						flag = false;
						break;
					} else {
						flag = true;
					}
				}
			}
			if (flag) {
				ContentValues values = new ContentValues();
				values.put("_id", id);
				values.put("title", title);
				values.put("source", source);
				values.put("wap_thumb", wap_thumb);
				values.put("create_time", create_time);
				values.put("nickname", nickname);
				utils.addCollection(values);
				Toast.makeText(WebViewActivity.this, "成功添加收藏",
						Toast.LENGTH_LONG).show();
			}

			break;
		default:
			break;
		}
	}

	class MyTask extends AsyncTask<String, String, String> {

		Context context;
		ProgressDialog dialog;

		public MyTask(Context context) {
			super();
			this.context = context;
			dialog = new ProgressDialog(context);
			dialog.setTitle("提示");
			dialog.setMessage("数据加载中……");
			dialog.setIcon(R.drawable.ic_logo);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String str = UrlToJsonString.getJsonStringFromURL(params[0]);
			return str;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			try {
				JSONObject obj = new JSONObject(result);
				JSONObject obj_data = obj.getJSONObject("data");
				wap_content = obj_data.getString("wap_content");
				Log.e("wap_content", wap_content + "");
				webView.loadDataWithBaseURL(null, wap_content, "text/html",
						"UTF-8", null);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
