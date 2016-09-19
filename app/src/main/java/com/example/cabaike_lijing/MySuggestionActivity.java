package com.example.cabaike_lijing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cabaike_lijing.db.SQLiteUtils;

public class MySuggestionActivity extends Activity{

	EditText et_title;
	EditText et_content;
	List<Map<String,String>> data;
	TextView tv_submit;
	ImageView iv_back ;
	SQLiteUtils utils;
	int id = 0;
	String title;
	String content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_suggeation);
		data = new ArrayList<Map<String,String>>();
		utils = new SQLiteUtils(this);
		et_title = (EditText) findViewById(R.id.et_title);
		et_content = (EditText) findViewById(R.id.et_content);			
		 
		tv_submit = (TextView) findViewById(R.id.tv_submit);		
		tv_submit.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title = et_title.getText().toString();
				content = et_content.getText().toString();
				ContentValues values = new ContentValues();
				values.put("_id", id);
				values.put("title", title);
				values.put("content", content);
				utils.addSuggestion(values);
				Toast.makeText(MySuggestionActivity.this, "反馈意见成功", 0).show();
				id++;
			}
		});
			
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
}
