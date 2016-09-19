package com.example.cabaike_lijing.adapter;

import java.util.List;
import java.util.Map;

import com.example.cabaike_lijing.R;
import com.example.cabaike_lijing.application.UILApplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter {

	private Context context;
	private int layout;
	private List<Map<String, String>> data;

	public MyListViewAdapter(Context context, int layout,
			List<Map<String, String>> data) {
		super();
		this.context = context;
		this.layout = layout;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(layout, null);
		}
		TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
		TextView tv2 = (TextView) convertView.findViewById(R.id.tv2);
		TextView tv3 = (TextView) convertView.findViewById(R.id.tv3);
		ImageView iv = (ImageView) convertView.findViewById(R.id.iv);

		Map<String, String> map = data.get(position);
		tv_title.setText(map.get("title"));
		tv1.setText(map.get("source"));
		tv2.setText(map.get("create_time"));
		tv3.setText(map.get("nickname"));
		String img_url = map.get("wap_thumb");
		UILApplication.imageLoader.displayImage(img_url, iv,
				UILApplication.options, null);

		return convertView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
