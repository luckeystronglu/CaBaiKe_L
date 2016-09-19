package com.example.cabaike_lijing.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cabaike_lijing.R;
import com.example.cabaike_lijing.WebViewActivity;
import com.example.cabaike_lijing.adapter.MyListViewAdapter;
import com.example.cabaike_lijing.application.UILApplication;
import com.example.cabaike_lijing.db.SQLiteUtils;
import com.example.cabaike_lijing.helper.UrlToJsonString;
import com.example.cabaike_lijing.url.IsConnNetwork;
import com.example.cabaike_lijing.url.Url;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class IndexFragment extends Fragment {

	ListView lv_content;
	List<Map<String, String>> data;
	List<Fragment> fragments = new ArrayList<Fragment>();
	MyListViewAdapter adapter;
	int count = 0;
	int type;
	String url;
	private static SQLiteUtils utils;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		utils = new SQLiteUtils(getActivity());
		Bundle bundle = getArguments();
		int page = bundle.getInt("page");
		View view = inflater.inflate(R.layout.content_fragment, container,
				false);
		lv_content = (ListView) view.findViewById(R.id.lv_content);
		lv_content.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), WebViewActivity.class);
				String _id = data.get(position).get("id");
				String _title = data.get(position).get("title");
				String _source = data.get(position).get("source");
				String _wap_thumb = data.get(position).get("wap_thumb");
				String _create_time = data.get(position).get("create_time");
				String _nickname = data.get(position).get("nickname");

				intent.putExtra("id", _id);
				intent.putExtra("title", _title);
				intent.putExtra("source", _source);
				intent.putExtra("wap_thumb", _wap_thumb);
				intent.putExtra("create_time", _create_time);
				intent.putExtra("nickname", _nickname);

				startActivity(intent);
			}
		});

		data = new ArrayList<Map<String, String>>();

		adapter = new MyListViewAdapter(getActivity(), R.layout.listview_item,
				data);

		switch (page) {
		case 0:
			LayoutInflater inflater2 = LayoutInflater.from(getActivity());
			View view2 = inflater2.inflate(R.layout.toutiao_pictures_viewpager,
					null);
			ViewPager toutiao_pictures_pager = (ViewPager) view2
					.findViewById(R.id.toutiao_pictures_pager);
			fragments.add(getFragment(0));
			fragments.add(getFragment(1));
			fragments.add(getFragment(2));
			toutiao_pictures_pager.setAdapter(new MyFragmentPagerAdapter(
					getFragmentManager()));
			lv_content.addHeaderView(view2);
			url = Url.HEADLINEPATH;

			break;
		case 1:
			// 百科
			url = Url.getURL(count, 16);
			break;
		case 2:
			// 咨询
			url = Url.getURL(count, 52);
			break;
		case 3:
			// 经营
			url = Url.getURL(count, 53);
			break;
		case 4:
			// 数据
			url = Url.getURL(count, 54);
			break;
		default:
			break;
		}

		// 判断是否联网,并得到数据
		if (IsConnNetwork.isNetworkConnected(getActivity())) {
			new MyTask(getActivity()).execute(url);
		} else {
			Cursor cursor = utils.queryCache();
			while (cursor.moveToNext()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", cursor.getString(cursor.getColumnIndex("_id"))
						.toString());
				map.put("title",
						cursor.getString(cursor.getColumnIndex("title"))
								.toString());
				map.put("wap_thumb",
						cursor.getString(cursor.getColumnIndex("wap_thumb"))
								.toString());
				map.put("create_time",
						cursor.getString(cursor.getColumnIndex("create_time"))
								.toString());
				map.put("nickname",
						cursor.getString(cursor.getColumnIndex("nickname"))
								.toString());
				data.add(map);
			}
		}
		lv_content.setAdapter(adapter);

		return view;
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
					// 离线缓存
					addCache(id, title, source, wap_thumb, create_time,
							nickname);
					UILApplication.imageLoader.loadImage(wap_thumb,
							UILApplication.options, new ImageLoadingListener() {

								@Override
								public void onLoadingStarted(String imageUri,
										View view) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onLoadingFailed(String imageUri,
										View view, FailReason failReason) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onLoadingComplete(String imageUri,
										View view, Bitmap loadedImage) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onLoadingCancelled(String imageUri,
										View view) {
									// TODO Auto-generated method stub

								}
							});
					data.add(map);
				}

				Log.e("list", data + "");
				adapter.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 添加离线缓存方法
	public static void addCache(String id, String title, String source,
			String wap_thumb, String create_time, String nickname) {
		Cursor cursor = utils.queryCache();
		boolean flag = false;
		if (cursor.getCount() == 0) {
			flag = true;
		} else {
			while (cursor.moveToNext()) {
				if (cursor.getString(cursor.getColumnIndex("_id")).equals(id)) {
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
			utils.addCache(values);
		}
	}

	// 返回一个fragment对象，并且把参数放进bundle
	public Fragment getFragment(int count) {
		Fragment fragment = new TouTiaoTopPicturesFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("count", count);
		fragment.setArguments(bundle);
		return fragment;
	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			/** 此方法中可以不用写任何东西 */
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Log.e("MyFragment", "getItem" + arg0);
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}
	}

}
