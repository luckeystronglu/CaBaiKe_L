package com.example.cabaike_lijing.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cabaike_lijing.R;
import com.example.cabaike_lijing.application.UILApplication;
import com.example.cabaike_lijing.helper.UrlToJsonString;
import com.example.cabaike_lijing.url.IsConnNetwork;
import com.example.cabaike_lijing.url.Url;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class TouTiaoTopPicturesFragment extends Fragment {
	List<String> data;
	int page ;
	ImageView iv_toutiao_top_pictures ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle bundle = getArguments();
		page = bundle.getInt("count");
		
		data = new ArrayList<String>();

		View view = inflater.inflate(R.layout.toutiao_top_pictures_item,
				container, false);
		ImageView iv_page01 = (ImageView) view.findViewById(R.id.iv_top_page01);
		ImageView iv_page02 = (ImageView) view.findViewById(R.id.iv_top_page02);
		ImageView iv_page03 = (ImageView) view.findViewById(R.id.iv_top_page03);	
		
		iv_toutiao_top_pictures = (ImageView) view
				.findViewById(R.id.iv_toutiao_top_pictures);	
		//判断是否有网
		if(IsConnNetwork.isNetworkConnected(getActivity())){
			// 得到数据
			new MyTask(getActivity()).execute(Url.HOMEPATH);
		}else{
			
			Toast.makeText(getActivity(), "网络连接异常", 0).show();
		}		

		switch (page) {
		case 0:
			break;
		case 1:			
			iv_page01.setImageResource(R.drawable.page);
			iv_page02.setImageResource(R.drawable.page_now);
			break;
		case 2:
			iv_page01.setImageResource(R.drawable.page);
			iv_page03.setImageResource(R.drawable.page_now);
			break;
		default:
			break;
		}
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
			dialog.setMessage("正在加载图片，请稍等……");
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
			Log.e("str", str);
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
					String image = obj_data.getString("image");
					UILApplication.imageLoader.loadImage(image, UILApplication.options, new ImageLoadingListener() {
						
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onLoadingCancelled(String imageUri, View view) {
							// TODO Auto-generated method stub
							
						}
					});
					Log.e("image", image);				
					data.add(image);				
				}
				Log.e("data", data+"");
				switch (page) {
				case 0:				
					UILApplication.imageLoader.displayImage(data.get(0),
					iv_toutiao_top_pictures, UILApplication.options, null);
					break;
				case 1:				
					UILApplication.imageLoader.displayImage(data.get(1),
					iv_toutiao_top_pictures, UILApplication.options, null);
					break;
				case 2:				
					UILApplication.imageLoader.displayImage(data.get(2),
					iv_toutiao_top_pictures, UILApplication.options, null);
					break;
				default:
					break;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}

}
