package com.example.cabaike_lijing.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cabaike_lijing.IndexActivity;
import com.example.cabaike_lijing.R;

public class MainFragment extends Fragment{
	ArrayList<String> data;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		data = new ArrayList<String>();
		
		Bundle bundle = getArguments();
		int page = bundle.getInt("page");		
		View view  = inflater.inflate(R.layout.main_fragment, container, false);
		ImageView main_background = (ImageView) view.findViewById(R.id.main_background);
		RelativeLayout enter = (RelativeLayout) view.findViewById(R.id.enter_layout);		
		
		ImageView iv_page01 = (ImageView) view.findViewById(R.id.main_page01);
		ImageView iv_page02 = (ImageView) view.findViewById(R.id.main_page02);
		ImageView iv_page03 = (ImageView) view.findViewById(R.id.main_page03);
		
		
		switch (page) {
		case 0:
			main_background.setImageResource(R.drawable.slide1);
			iv_page01.setImageResource(R.drawable.page_now);
			break;
		case 1:
			main_background.setImageResource(R.drawable.slide2);
			iv_page02.setImageResource(R.drawable.page_now);
			iv_page01.setImageResource(R.drawable.page);
			break;
		case 2:		
			main_background.setImageResource(R.drawable.slide3);
			iv_page03.setImageResource(R.drawable.page_now);
			iv_page01.setImageResource(R.drawable.page);
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getActivity(),IndexActivity.class);
					startActivity(intent);
				}
			});
			break;
			
		default:
			break;
		}
		return view;
	}
}
