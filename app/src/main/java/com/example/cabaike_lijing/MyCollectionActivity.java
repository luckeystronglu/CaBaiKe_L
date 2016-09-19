package com.example.cabaike_lijing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cabaike_lijing.adapter.MyListViewAdapter;
import com.example.cabaike_lijing.db.SQLiteUtils;

public class MyCollectionActivity extends Activity {

	ListView lv;
	MyListViewAdapter adapter;
	List<Map<String, String>> list;
	SQLiteUtils utils;
	String id;
	String title;
	String source;
	String wap_thumb;
	String create_time;
	String nickname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_collections);
		utils = new SQLiteUtils(MyCollectionActivity.this);

		lv = (ListView) findViewById(R.id.lv_collection);
		list = new ArrayList<Map<String, String>>();

		Cursor cursor = utils.queryCollection();
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			id = cursor.getString(cursor.getColumnIndex("_id"));
			title = cursor.getString(cursor.getColumnIndex("title"));
			source = cursor.getString(cursor.getColumnIndex("source"));
			wap_thumb = cursor.getString(cursor.getColumnIndex("wap_thumb"));
			create_time = cursor
					.getString(cursor.getColumnIndex("create_time"));
			nickname = cursor.getString(cursor.getColumnIndex("nickname"));

			map.put("id", id);
			map.put("title", title);
			map.put("source", source);
			map.put("wap_thumb", wap_thumb);
			map.put("create_time", create_time);
			map.put("nickname", nickname);
			list.add(map); // 数据库中的数据

		}
		adapter = new MyListViewAdapter(MyCollectionActivity.this,
				R.layout.listview_item, list);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyCollectionActivity.this,
						WebViewActivity.class);
				String _id = list.get(position).get("id");
				String _title = list.get(position).get("title");
				String _source = list.get(position).get("source");
				String _wap_thumb = list.get(position).get("wap_thumb");
				String _create_time = list.get(position).get("create_time");
				String _nickname = list.get(position).get("nickname");
				intent.putExtra("id", _id);
				intent.putExtra("title", _title);
				intent.putExtra("source", _source);
				intent.putExtra("wap_thumb", _wap_thumb);
				intent.putExtra("create_time", _create_time);
				intent.putExtra("nickname", _nickname);
				startActivity(intent);
			}
		});

		// 长按：删除本收藏
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String _id = list.get(position).get("id");
				DialogShow(_id, position);
				lv.invalidate();
				return false;
			}
		});
	}

	private void DialogShow(final String id, final int position) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("删除本收藏？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				utils.delOneCollection(id);
				list.remove(position);
				adapter.notifyDataSetChanged();
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		builder.create().show();
	}

	// 顶部的按钮的点击事件
	public void topOnClick(View view) {
		switch (view.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.iv_clear:
			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage("删除全部收藏？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					utils.delAllCollection();
					list.removeAll(list);

					adapter.notifyDataSetChanged();
					dialog.dismiss();
				}
			});

			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			builder.create().show();
			lv.invalidate();
			break;
		default:
			break;
		}
	}
}
