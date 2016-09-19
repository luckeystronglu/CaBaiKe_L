package com.qf.tea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qf.util.SharedUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${WU} on 2016/9/13.
 */
public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private int[]datas={R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};
    private LinearLayout linearLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private Button btn;
    private boolean notFirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        notFirst = SharedUtil.getBoolean("notFirst");
            btn = (Button) findViewById(R.id.in);
        if(notFirst){
            startActivity(new Intent(this,MainActivity.class));
            this.finish();
        }

            SharedUtil.putBoolean("notFirst",true);

        viewPager = (ViewPager) findViewById(R.id.vp);
        linearLayout = (LinearLayout) findViewById(R.id.ll);
        viewPagerAdapter = new ViewPagerAdapter(this,datas);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        for(int i =0;i<datas.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setPadding(20,10,20,10);
            if(i==0){
                imageView.setImageResource(R.drawable.page_now);
                imageView.setTag("checked");
            }else {
                imageView.setImageResource(R.drawable.page);
            }
            linearLayout.addView(imageView);

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ImageView iv = (ImageView) linearLayout.findViewWithTag("checked");
        iv.setImageResource(R.drawable.page);
        iv.setTag(null);
        ImageView image = (ImageView) linearLayout.getChildAt(position);
        image.setImageResource(R.drawable.page_now);
        image.setTag("checked");
    }

    @Override
    public void onPageSelected(int position) {
       if(position == datas.length-1){
           btn.setVisibility(View.VISIBLE);
       }else {
           btn.setVisibility(View.GONE);
       }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class ViewPagerAdapter extends PagerAdapter{
        private Context context;
        private int []datas;
        private List<ImageView> views;

        public ViewPagerAdapter(Context context,int[] datas) {
            this.datas = datas;
            this.context =context;
            views = new ArrayList<>();
            for (int i = 0; i <datas.length ; i++) {
                ImageView imageView=new ImageView(context);
                imageView.setImageResource(datas[i]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                views.add(imageView);
            }
        }


        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

    public void btnClick(View view ){
        SharedUtil.putBoolean("notFirst",true);
        this.finish();
        startActivity(new Intent(this,MainActivity.class));

    }
}
