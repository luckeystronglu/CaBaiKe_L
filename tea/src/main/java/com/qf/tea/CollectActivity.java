package com.qf.tea;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qf.adapter.MyBaseAdapter;
import com.qf.db.TeaSQLiteOpenHelper;
import com.qf.entity.TeaNewsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/18.
 */
public class CollectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv_cellect;
    private MyBaseAdapter adapter;
    private TeaSQLiteOpenHelper helper;
    private SQLiteDatabase sqLiteDatabase;
    private List<TeaNewsEntity.DataBean> coList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.collect_listview);

        lv_cellect = (ListView) findViewById(R.id.collect_lv);
        lv_cellect.setOnItemClickListener(this);
        helper = new TeaSQLiteOpenHelper(this);
        sqLiteDatabase = helper.getReadableDatabase();
        adapter=new MyBaseAdapter(this);

        lv_cellect.setAdapter(adapter);
        loadDatas();
    }

    private void loadDatas() {
        coList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("collections", new String[]{"_id","id","title", "create_time", "nickname","wap_thumb"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String co_id = cursor.getString(cursor.getColumnIndex("id"));
            Log.d("print", "loadDatas: "+cursor.getString(cursor.getColumnIndex("id")));
            String co_title = cursor.getString(cursor.getColumnIndex("title"));
            String co_create_time = cursor.getString(cursor.getColumnIndex("create_time"));
            String co_nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            String wap_thumb = cursor.getString(cursor.getColumnIndex("wap_thumb"));
             TeaNewsEntity.DataBean teaNewsEntity= new TeaNewsEntity.DataBean();
            teaNewsEntity.setId(co_id);
            teaNewsEntity.setTitle(co_title);
            teaNewsEntity.setCreate_time(co_create_time);
            teaNewsEntity.setNickname(co_nickname);
            teaNewsEntity.setWap_thumb(wap_thumb);
            coList.add(teaNewsEntity);
        }
        adapter.setDatas(coList);

        cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("id",coList.get(position).getId());
        intent.putExtra("title",coList.get(position).getTitle());
        intent.putExtra("nickName",coList.get(position).getNickname());
        intent.putExtra("time",coList.get(position).getCreate_time());
        intent.putExtra("wap_thumb",coList.get(position).getWap_thumb());
        startActivity(intent);
    }
}



