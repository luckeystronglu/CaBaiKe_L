package com.dream.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.entity.GridEntity;
import com.dream.musicplayer.R;
import com.dream.task.AbsAsyncTask;

import java.util.List;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public class GgpdAdapter extends BaseAdapter {

    private Context context;

    private List<GridEntity.ResultBean.ChannellistBean> datas;

    public GgpdAdapter(Context context,List<GridEntity.ResultBean.ChannellistBean> datass){
        this.context=context;
        this.datas=datass;

    }

    @Override
    public int getCount() {

        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView!=null){
            viewHolder= (ViewHolder) convertView.getTag();
        }else {
            viewHolder = new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            viewHolder.iv_style= (ImageView) convertView.findViewById(R.id.iv_style);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }
        viewHolder.tv_name.setText(datas.get(position).getName());
        new AbsAsyncTask(AbsAsyncTask.DownType.IMAGE).
                with(viewHolder.iv_style).execute(datas.get(position).getThumb());
        return convertView;
    }

     class ViewHolder{
        ImageView iv_style;
        TextView tv_name;
    }
}
