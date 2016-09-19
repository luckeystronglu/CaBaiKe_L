package com.qf.tea;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.adapter.MyVPAdapter;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private int[] ints = {1,2,3,4,5};
    private View lit;
    private LinearLayout.LayoutParams litwidth;
    private LinearLayout linearLayout;

//    private MyDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        vp = (ViewPager) findViewById(R.id.vp);
        lit = findViewById(R.id.lit);
        linearLayout = (LinearLayout) findViewById(R.id.ll_layout);
        litwidth = (LinearLayout.LayoutParams) lit.getLayoutParams();
        int windowWidth = getResources().getDisplayMetrics().widthPixels;
        litwidth.width = windowWidth/linearLayout.getChildCount();
        MyVPAdapter myVpAdapter = new MyVPAdapter(getSupportFragmentManager(),ints);
        vp.setAdapter(myVpAdapter);
        vp.addOnPageChangeListener(this);
        TextView tv = (TextView) linearLayout.getChildAt(0);
        tv.setTextColor(getResources().getColor(R.color.text));
//        dataBase = new MyDataBase(this);
//        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int move = 0;
        for(int i =0;i<position;i++){
            move+=litwidth.width;
        }
        int movex = (int)(move + litwidth.width * positionOffset);
        litwidth.leftMargin = movex;
        lit.setLayoutParams(litwidth);
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView tv = (TextView) linearLayout.getChildAt(i);
            if(position!=i){
                tv.setTextColor(Color.GRAY);
            }else {
                tv.setTextColor(getResources().getColor(R.color.text));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void doclick(View v){
        switch (v.getId()){
            case R.id.tv1:
                vp.setCurrentItem(0);
            break;
            case R.id.tv2:
                vp.setCurrentItem(1);
                break;
            case R.id.tv3:
                vp.setCurrentItem(2);
                break;
            case R.id.tv4:
                vp.setCurrentItem(3);
                break;
            case R.id.tv5:
                vp.setCurrentItem(4);
                break;
            case R.id.tv6:
                startActivity(new Intent(MainActivity.this,CollectActivity.class));
                break;


        }

    }
}
