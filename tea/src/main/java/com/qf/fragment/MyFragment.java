package com.qf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qf.Constant.Constants;
import com.qf.adapter.MyBaseAdapter;
import com.qf.entity.TeaNewsEntity;
import com.qf.tea.DetailsActivity;
import com.qf.tea.R;
import com.qf.util.DownUtil;

import java.util.List;

/**
 * Created by ${WU} on 2016/9/13.
 */
public class MyFragment extends Fragment implements DownUtil.OnDownListener, AdapterView.OnItemClickListener {
    private MyBaseAdapter myBaseAdapter;
    private ListView lv;
    private List<TeaNewsEntity.DataBean> dataBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgent_main,null);
        lv = (ListView) view.findViewById(R.id.olv);
        myBaseAdapter = new MyBaseAdapter(getContext());
        lv.setAdapter(myBaseAdapter);
        lv.setOnItemClickListener(this);
        return view;
    }

    public static MyFragment geInstance(int i){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",i);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int i =bundle.getInt("position");
        String url = null;
        switch (i){
            case 1:
                url = String.format(Constants.OTHER,16);
                break;
            case 2:
                url = String.format(Constants.OTHER,52);
                break;
            case 3:
                url = String.format(Constants.OTHER,53);
                break;
            case 4:
                url = String.format(Constants.OTHER,54);
                break;
        }
        new DownUtil().setOnDownListener(this).downJSON(url);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, TeaNewsEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            TeaNewsEntity firstEntity = (TeaNewsEntity) object;
            dataBean = firstEntity.getData();
            myBaseAdapter.setDatas(dataBean);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        intent.putExtra("id",dataBean.get(position).getId());
        intent.putExtra("title",dataBean.get(position).getTitle());
        intent.putExtra("nickName",dataBean.get(position).getNickname());
        intent.putExtra("time",dataBean.get(position).getCreate_time());
        intent.putExtra("wap_thumb",dataBean.get(position).getWap_thumb());
        startActivity(intent);
    }
}
