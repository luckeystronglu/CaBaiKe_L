package com.qf.tea;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.Constant.Constants;
import com.qf.db.TeaSQLiteOpenHelper;
import com.qf.util.DownUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ${WU} on 2016/9/16.
 */
public class DetailsActivity extends AppCompatActivity implements DownUtil.OnDownListener {
    private TextView details_title,details_tv1,details_tv2;

    private WebView webView;
    private WebSettings webSettings;
    private String url;


    private String wap_content;
    String title,nickName,time,id,wap_thumb;

    private TeaSQLiteOpenHelper helper;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        helper = new TeaSQLiteOpenHelper(this);
        sqLiteDatabase = helper.getReadableDatabase();
        init();

    }

    private void init() {
        Intent intent = getIntent();
        details_title = (TextView) findViewById(R.id.details_title);
        details_tv1 = (TextView) findViewById(R.id.details_tv1);
        details_tv2 = (TextView) findViewById(R.id.details_tv2);
        webView = (WebView) findViewById(R.id.wb);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//让WebView支持javascript
        webSettings.setDefaultTextEncodingName("utf-8");//设置字符集
        webSettings.setLoadWithOverviewMode(true);//设置网页缩放至屏幕大小
        webSettings.setUseWideViewPort(true);


        title = intent.getStringExtra("title");
        nickName = intent.getStringExtra("nickName");
        time = intent.getStringExtra("time");
        id = intent.getStringExtra("id");
        wap_thumb = intent.getStringExtra("wap_thumb");
        details_title.setText(title);
        details_tv1.setText(nickName);
        details_tv2.setText(time);
        url = String.format(Constants.DETAILS,id);

        new DownUtil().setOnDownListener(this).downJSON(url);
//
    }

    @Override
    public Object paresJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json).optJSONObject("data");

            return jsonObject.get("wap_content");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        wap_content = (String) object;
        webView.loadData(wap_content,"text/html;charset=utf-8",null);

    }

//

    public void btnClick(View v){
        switch (v.getId()){
            case R.id.details_btn1:
                this.finish();
                break;
            case R.id.details_btn2:

                break;
            case R.id.details_btn3:
                Cursor cursor = sqLiteDatabase.query("collections",new String[]{"_id","id"},null,null,null,null,null);
                boolean flag = false;
                if (cursor.getCount() == 0) {
                    flag = true;
                } else {
                    while (cursor.moveToNext()) {
                        if (cursor.getString(cursor.getColumnIndex("id")).equals(
                                id)) {
                            Toast.makeText(DetailsActivity.this, "您已经添加过该收藏",
                                    Toast.LENGTH_LONG).show();
                            flag = false;
                            break;
                        } else {
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    ContentValues values = new ContentValues();
                    values.put("id", id);
                    values.put("title", title);
                    values.put("create_time", time);
                    values.put("nickname", nickName);
                    values.put("wap_thumb",wap_thumb);
                    sqLiteDatabase.insert("collections",null,values);

                    Toast.makeText(DetailsActivity.this, "成功添加收藏",
                            Toast.LENGTH_SHORT).show();
                }
                break;



        }
    }

}
