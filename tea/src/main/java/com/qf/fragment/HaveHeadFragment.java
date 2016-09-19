package com.qf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.Constant.Constants;
import com.qf.adapter.LittleVpAdapter;
import com.qf.adapter.MyBaseAdapter;
import com.qf.entity.TeaNewsEntity;
import com.qf.entity.HeadBanner;
import com.qf.tea.DetailsActivity;
import com.qf.tea.R;
import com.qf.util.DownUtil;

import java.util.List;

/**
 * Created by ${WU} on 2016/9/13.
 */
public class HaveHeadFragment extends Fragment implements DownUtil.OnDownListener, AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private LittleVpAdapter littleVpAdapter;
    private List<HeadBanner.DataBean> headDatas;
    private ListView listView;
    private MyBaseAdapter myBaseAdapter;
    private TextView tv;
    private LinearLayout line_o;
    private List<TeaNewsEntity.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        init(view);
        return view;
    }

    private void init(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.fvp);
        listView = (ListView) view.findViewById(R.id.flv);
        myBaseAdapter = new MyBaseAdapter(getContext());
        viewPager.addOnPageChangeListener(this);


        tv = (TextView) view.findViewById(R.id.details);

        line_o = (LinearLayout) view.findViewById(R.id.lit_o);

        listView.setAdapter(myBaseAdapter);
        listView.setOnItemClickListener(this);
        loadHead();

    }

    private void loadHead() {
        new DownUtil().setOnDownListener(this).downJSON(Constants.HEAD_VP);
        new DownUtil().setOnDownListener(new DownUtil.OnDownListener() {
            @Override
            public Object paresJson(String json) {
                return new Gson().fromJson(json, TeaNewsEntity.class);
            }

            @Override
            public void downSucc(Object object) {
                TeaNewsEntity firstEntity = (TeaNewsEntity) object;
                data = firstEntity.getData();
                myBaseAdapter.setDatas(data);

            }
        }).downJSON(Constants.HEADLINES);
    }


    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, HeadBanner.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            HeadBanner headBanner = (HeadBanner) object;
            headDatas = headBanner.getData();
            littleVpAdapter = new LittleVpAdapter(getChildFragmentManager(), headDatas);
            viewPager.setAdapter(littleVpAdapter);
            tv.setText(headDatas.get(0).getTitle());
            for(int i =0;i<headDatas.size();i++){
                ImageView imageView = new ImageView(getContext());
                imageView.setPadding(10,10,10,10);
                if(i==0){
                    imageView.setImageResource(R.drawable.page_now);
                    imageView.setTag("checked");
                }else {
                    imageView.setImageResource(R.drawable.page);
                }
                line_o.addView(imageView);

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        intent.putExtra("id",data.get(position).getId());
        intent.putExtra("title",data.get(position).getTitle());
        intent.putExtra("nickName",data.get(position).getNickname());
        intent.putExtra("time",data.get(position).getCreate_time());
        intent.putExtra("wap_thumb",data.get(position).getWap_thumb());
        startActivity(intent);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ImageView iv = (ImageView) line_o.findViewWithTag("checked");
        iv.setImageResource(R.drawable.page);
        iv.setTag(null);
        ImageView image = (ImageView) line_o.getChildAt(position);
        image.setImageResource(R.drawable.page_now);
        image.setTag("checked");
    }

    @Override
    public void onPageSelected(int position) {
        tv.setText(headDatas.get(position).getTitle());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
