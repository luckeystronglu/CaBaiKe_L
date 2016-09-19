package com.dream.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dream.adapter.GgpdAdapter;
import com.dream.entity.GridEntity;
import com.dream.musicplayer.PartActivity;
import com.dream.musicplayer.R;
import com.dream.task.AbsAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public class GgpdFragment extends Fragment implements AbsAsyncTask.OnDownListener{

    /**
     * 定义一个静态方法
     * @param url
     * @return
     */
    public static GgpdFragment getInstance(String url){
        GgpdFragment  ggpdFragment=new GgpdFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        ggpdFragment.setArguments(bundle);
        return ggpdFragment;
    }

    private GridView gridView;
    public List<GridEntity.ResultBean.ChannellistBean> partList;
    private GgpdAdapter ggpdAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.ggpd_layout,null);
        gridView= (GridView) view.findViewById(R.id.gv);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), PartActivity.class);
                intent.putExtra("ch_name",partList.get(position).getCh_name());
                startActivity(intent);
            }
        });
        loadDatas();
        return  view;
    }

    private void loadDatas() {
        new AbsAsyncTask(AbsAsyncTask.DownType.JSON).setOnDownListener(this).execute(Constants.URL_DIANTAI);
    }

    /**
     * 下载完成，解析
     * @param json
     * @return
     */
    @Override
    public Object downJSON(String json) {
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("result");
                JSONObject jso=jsonArray.optJSONObject(0);
                JSONArray jsa=jso.optJSONArray("channellist");

                TypeToken<List<GridEntity.ResultBean.ChannellistBean>> tt = new TypeToken<List<GridEntity.ResultBean.ChannellistBean>>(){};
                List<GridEntity.ResultBean.ChannellistBean> dataslist= new Gson().fromJson(jsa.toString(), tt.getType());
                return dataslist;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把数据添加到适配器
     * @param datas
     */
    @Override
    public void paresJSON(Object datas) {
        Log.d("printjson",""+datas);
        partList= (List<GridEntity.ResultBean.ChannellistBean>) datas;
        ggpdAdapter=new GgpdAdapter(getActivity(),partList);
        gridView.setAdapter(ggpdAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");

        new AbsAsyncTask(AbsAsyncTask.DownType.JSON).setOnDownListener(this).execute(url);
    }
}
