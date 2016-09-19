package com.example.cabaike_lijing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cabaike_lijing.adapter.MyListViewAdapter;
import com.example.cabaike_lijing.helper.UrlToJsonString;
import com.example.cabaike_lijing.url.IsConnNetwork;
import com.example.cabaike_lijing.url.Url;

public class SearchActivity extends Activity {
	String search;
	String url;
	MyListViewAdapter adapter;
	List<Map<String, String>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.content_fragment);
		ListView lv = (ListView) findViewById(R.id.lv_content);
		data = new ArrayList<Map<String, String>>();
		adapter = new MyListViewAdapter(SearchActivity.this,
				R.layout.listview_item, data);
		search = getIntent().getStringExtra("text");
		url = Url.getUrlForSearch(search);
		if(IsConnNetwork.isNetworkConnected(SearchActivity.this)){
			new MyTask(SearchActivity.this).execute(url);	
			lv.setAdapter(adapter);
		}else{
			Toast.makeText(SearchActivity.this, "网络连接异常，请求不到任何数据……", 0).show();
		}
		
		
	}

	class MyTask extends AsyncTask<String, String, String> {

		private Context context;
		private ProgressDialog dialog;

		public MyTask(Context context) {
			super();
			this.context = context;
			dialog = new ProgressDialog(context);
			dialog.setTitle("提示");
			dialog.setMessage("正在搜索中，请稍等……");
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
			Log.e(">>>>str", str);
			return str;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			try {
				JSONObject obj = new JSONObject(result);
				JSONArray array_data = obj.getJSONArray("data");
				for (int i = 0; i < array_data.length(); i++) {
					JSONObject obj_data = array_data.getJSONObject(i);
					String id = obj_data.getString("id");
					String title = obj_data.getString("title");
					String source = obj_data.getString("source");
					String wap_thumb = obj_data.getString("wap_thumb");// 图片
					String create_time = obj_data.getString("create_time");
					String nickname = obj_data.getString("nickname");
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", id);
					map.put("title", title);
					map.put("source", source);
					map.put("wap_thumb", wap_thumb);
					map.put("create_time", create_time);
					map.put("nickname", nickname);
					data.add(map);
				}
				adapter.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
