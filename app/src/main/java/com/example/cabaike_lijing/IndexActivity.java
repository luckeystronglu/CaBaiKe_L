package com.example.cabaike_lijing;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cabaike_lijing.fragment.IndexFragment;


public class IndexActivity extends FragmentActivity {
	List<Fragment> fragments = new ArrayList<Fragment>();
	ImageView iv_choose;
	Intent intent ;
	int[] iv = { R.id.iv_choose1, R.id.iv_choose2, R.id.iv_choose3,
			R.id.iv_choose4, R.id.iv_choose5 };
	private boolean isCanAccessFocus = true;// 抽屉是否打开，打开就将设置为false
	ViewPager pager;
	private DrawerLayout drawerLayout;// 抽屉
	private EditText et_search ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contents);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		et_search = (EditText) findViewById(R.id.et_search);
		
		pager = (ViewPager) findViewById(R.id.index_pager);
		fragments.add(getFragment(0));
		fragments.add(getFragment(1));
		fragments.add(getFragment(2));
		fragments.add(getFragment(3));
		fragments.add(getFragment(4));
		pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
				for(int i=0;i<iv.length;i++){
					iv_choose = (ImageView) findViewById(iv[i]);
					iv_choose.setBackgroundColor(Color.WHITE);
				}				
				iv_choose = (ImageView) findViewById(iv[arg0]);
				iv_choose.setBackgroundColor(Color.argb(255, 0, 170, 0));
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		//抽屉
		drawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDrawerOpened(View arg0) {
				System.out.println("抽屉打开");
				isCanAccessFocus = false;
			}

			@Override
			public void onDrawerClosed(View arg0) {
				System.out.println("抽屉关闭");
				isCanAccessFocus = true;
			}
		});
	}

	public void choose(View view) {
		switch (view.getId()) {
		case R.id.tv_toutiao:
			pager.setCurrentItem(0);
			break;
		case R.id.tv_baike:
			pager.setCurrentItem(1);
			break;
		case R.id.tv_zixun:
			pager.setCurrentItem(2);
			break;
		case R.id.tv_jingying:
			pager.setCurrentItem(3);
			break;
		case R.id.tv_data:
			pager.setCurrentItem(4);
			break;
		case R.id.ib_setting:
			drawerLayout.openDrawer(Gravity.RIGHT);
			break;
		/**抽屉中的内容*/
		case R.id.ib_back:
			drawerLayout.closeDrawer(Gravity.RIGHT);
			break;
		//搜索
		case R.id.ib_search:
			intent = new Intent(IndexActivity.this,SearchActivity.class);
			intent.putExtra("text", et_search.getText().toString().trim());
			startActivity(intent);
			break;
		//我的收藏
		case R.id.tv_collect:
			intent = new Intent(IndexActivity.this,MyCollectionActivity.class);
			startActivity(intent);
			break;
		//历史记录
		case R.id.tv_history:
			intent = new Intent(IndexActivity.this,MyHistoryActivity.class);
			startActivity(intent);
			break;
		//地图查询
		case R.id.tv_map:
			intent = new Intent(IndexActivity.this,MapActivity.class);
			startActivity(intent);
			break;
		//版权信息
		case R.id.tv_information:
			intent = new Intent(IndexActivity.this,VersionInformationActivity.class);
			startActivity(intent);
			break;
		//反馈意见
		case R.id.tv_suggestion:
			intent = new Intent(IndexActivity.this,MySuggestionActivity.class);
			startActivity(intent);
			break;			
		default:
			break;
		}
	}

	// 返回一个fragment对象，并且把参数放进bundle
	public Fragment getFragment(int page) {
		Fragment fragment = new IndexFragment();
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
