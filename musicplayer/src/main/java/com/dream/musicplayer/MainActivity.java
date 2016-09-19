package com.dream.musicplayer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.adapter.ClassesAdapter;
import com.dream.entity.ClassesEntity;
import com.dream.task.AbsAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,AbsAsyncTask.OnDownListener,View.OnClickListener{
    private HorizontalScrollView hsv;
    //头部滑动条的的HorizontalScrollView内的滑动条的线性布局
    private LinearLayout ll_Tabs;
    //指示器
    private View viewzsq;
    private ViewPager vp;
    //布局的属性
    private LinearLayout.LayoutParams layoutlp;
    private List<Integer> widths;//缓存所有textview的宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadDatas();
    }

    private void loadDatas() {
        new AbsAsyncTask(AbsAsyncTask.DownType.JSON)
                .setOnDownListener(this).execute(Constants.URL_DIANTAI);
    }

    private void init() {
        ll_Tabs=(LinearLayout) findViewById(R.id.ll_tabs);
        viewzsq=findViewById(R.id.view);
        layoutlp=(LinearLayout.LayoutParams)viewzsq.getLayoutParams();
        layoutlp.width=200;
        vp=(ViewPager) findViewById(R.id.vp);
        vp.addOnPageChangeListener(this);
        hsv=(HorizontalScrollView) findViewById(R.id.hsv);
    }


    /**
     * 下载完成，开始解析
     * @param json
     * @return
     */
    @Override
    public Object downJSON(String json) {
        if(json!=null){
            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsa=jsonObject.getJSONArray("result");
                TypeToken<List<ClassesEntity.ResultBean>> tt = new TypeToken<List<ClassesEntity.ResultBean>>(){};
                List<ClassesEntity.ResultBean> classdatas= new Gson().fromJson(jsa.toString(), tt.getType());
                return classdatas;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public void paresJSON(Object datas) {
        Log.d("print","打印一下"+datas);
        if(datas!=null){
            List<ClassesEntity.ResultBean> topdatas= (List<ClassesEntity.ResultBean>) datas;
            ClassesAdapter classesAdapter=new ClassesAdapter(getSupportFragmentManager(),topdatas);
            vp.setAdapter(classesAdapter);
            addTabs(topdatas);
        }
    }


    /**
     * 根据下载的数据，生成新闻tab头部
     * @param tabs
     */
    public void addTabs(List<ClassesEntity.ResultBean> tabs){
        widths = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(tabs.get(i).getTitle());
            textView.setPadding(20, 20, 20, 20);
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(this);
            textView.setTag(i);
            textView.setTextSize(20);
            if(i == 0){
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            } else {
                textView.setTextColor(Color.GRAY);
            }

            //获得TextView的宽度
            textView.measure(0, 0);//测量TextView
            int width = textView.getMeasuredWidth();//获得宽度
            widths.add(width);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll_Tabs.addView(textView, layoutParams);
        }
        layoutlp.width = widths.get(0);//把第一个TextView的宽度设置给指示器
    }

    /**
     * ViewPager的监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //TextView不等长，移动指示器
        int movex = 0;
        for(int i = 0; i < position; i++){
            movex += widths.get(i);
        }
        int move = (int)(movex + widths.get(position) * positionOffset);
        layoutlp.leftMargin = move;
        viewzsq.setLayoutParams(layoutlp);

        //处理光标的长度
        if(position != widths.size() - 1) {
            int changewidth = widths.get(position + 1) - widths.get(position);//变化的宽度
            layoutlp.width = (int) (widths.get(position) + changewidth * positionOffset);
        } else {
            //滑动到最后一个tab了
            layoutlp.width = widths.get(position);
        }

        //移动scrollview
        hsv.scrollTo(move - 100, 0);
    }

    @Override
    public void onPageSelected(int position) {
        for(int i = 0; i < ll_Tabs.getChildCount(); i++){
            TextView view = (TextView) ll_Tabs.getChildAt(i);
            if(position != i) {
                view.setTextColor(Color.GRAY);
            } else {
                view.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * tab的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        vp.setCurrentItem(position);
    }
}
