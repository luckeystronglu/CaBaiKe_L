package com.example.cabaike_lijing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.cabaike_lijing.adapter.MyListViewAdapter;
import com.example.cabaike_lijing.db.SQLiteUtils;

public class MyHistoryActivity extends Activity{

	SQLiteUtils utils;
	List<Map<String,String>> data;
	MyListViewAdapter adapter;
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_history);
		
		lv = (ListView) findViewById(R.id.lv_history);	
		utils = new SQLiteUtils(MyHistoryActivity.this);
		data = new ArrayList<Map<String,String>>();
		adapter = new MyListViewAdapter(this, R.layout.listview_item, data);
		
		Cursor cursor = utils.queryHistory();
		while(cursor.moveToNext()){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", cursor.getString(cursor.getColumnIndex("_id")));
			map.put("title", cursor.getString(cursor.getColumnIndex("title")));
			map.put("source", cursor.getString(cursor.getColumnIndex("source")));
			map.put("wap_thumb", cursor.getString(cursor.getColumnIndex("wap_thumb")));
			map.put("create_time", cursor.getString(cursor.getColumnIndex("create_time")));
			map.put("nickname", cursor.getString(cursor.getColumnIndex("nickname")));
			data.add(map);			
		}
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyHistoryActivity.this,
						WebViewActivity.class);
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
	
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String _id = data.get(position).toString();
				DialogShow(_id,position);
				lv.invalidate();
				return false;
			}
		});
		
	}
	
	private void DialogShow(final String id,final int position) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("删除本记录？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub	
				utils.delOneCollection(id);
				data.remove(position);
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
	
	
	//顶部图片按钮的点击事件
	public void  topOnClick(View view){
		switch (view.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.iv_clear:
			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage("删除全部历史记录？");
			builder.setTitle("提示");
			builder.setPositiveButton("确定", new OnClickListener() {			
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					utils.delAllHistory();
					data.removeAll(data);
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
