package com.example.cabaike_lijing;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cabaike_lijing.fragment.MainFragment;

public class MainActivity extends FragmentActivity {
	List<Fragment> fragments = new ArrayList<Fragment>();
	Boolean boo;
	SharedPreferences getSharedPref;
	Editor editor;
	ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 读取
		getSharedPref = getSharedPreferences("FIRST", Context.MODE_PRIVATE);
		editor = getSharedPref.edit();
		boo = getSharedPref.getBoolean("first", true);

		if (boo) {
			setContentView(R.layout.activity_main);
			// Toast.makeText(MainActivity.this, "第一次",
			// Toast.LENGTH_LONG).show();
			pager = (ViewPager) findViewById(R.id.main_pager);
			fragments.add(getFragment(0));
			fragments.add(getFragment(1));
			fragments.add(getFragment(2));
			pager.setAdapter(new MyFragmentPagerAdapter(
					getSupportFragmentManager()));

			editor.putBoolean("first", false);
			editor.commit();
		} else {
			setContentView(R.layout.activity_default);
			// Toast.makeText(MainActivity.this, "不是第一次", Toast.LENGTH_LONG)
			// .show();

			// 定时任务类
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					Intent intent = new Intent(MainActivity.this,
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			};
			// 定时器
			Timer timer = new Timer();
			timer.schedule(task, 1000);
		}

	}

	// 返回一个fragment对象，并且把参数放进bundle
	public Fragment getFragment(int page) {
		Fragment fragment = new MainFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("page", page);
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
