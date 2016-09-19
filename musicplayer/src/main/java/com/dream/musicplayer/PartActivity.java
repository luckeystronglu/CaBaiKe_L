package com.dream.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dream.adapter.PartAdapter;
import com.dream.entity.PartEntity;
import com.dream.task.AbsAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PartActivity extends AppCompatActivity implements AbsAsyncTask.OnDownListener{
    private ListView lv_part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);
        lv_part= (ListView) findViewById(R.id.lv_part);
        loadDatas();
    }

    private void loadDatas() {
        Intent intent=getIntent();
        String drc=intent.getStringExtra("ch_name");
        String url_drc = String.format(Constants.URL_PART,drc);
        new AbsAsyncTask(AbsAsyncTask.DownType.JSON).setOnDownListener(this).execute(url_drc);
    }


    @Override
    public Object downJSON(String json) {
        if(json!=null){
            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONObject("result").getJSONArray("songlist");
                TypeToken<List<PartEntity.ResultBean.SonglistBean>> tt = new TypeToken<List<PartEntity.ResultBean.SonglistBean>>(){};
                List<PartEntity.ResultBean.SonglistBean> shujulist= new Gson().fromJson(jsonArray.toString(), tt.getType());
                return shujulist;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void paresJSON(Object datas) {
        List<PartEntity.ResultBean.SonglistBean> partlist= (List<PartEntity.ResultBean.SonglistBean>) datas;
    PartAdapter partAdapter=new PartAdapter(this,partlist);
    lv_part.setAdapter(partAdapter);
}
}
