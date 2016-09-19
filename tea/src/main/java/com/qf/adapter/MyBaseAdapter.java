package com.qf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.entity.TeaNewsEntity;
import com.qf.tea.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${WU} on 2016/9/14.
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<TeaNewsEntity.DataBean> datas;

    public MyBaseAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }
    public void setDatas(List<TeaNewsEntity.DataBean> datas){
        this.datas =datas;
        this.notifyDataSetChanged();
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
        ViewHolder holder=null;
        if(convertView!=null){
            holder = (ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_iv);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv1 = (TextView) convertView.findViewById(R.id.item_tv1);
            holder.tv2 = (TextView) convertView.findViewById(R.id.item_tv2);
            holder.tv3 = (TextView) convertView.findViewById(R.id.item_tv3);
            convertView.setTag(holder);
        }

        holder.imageView.setImageResource(R.mipmap.ic_launcher);
//        new DownUtil().with(holder.imageView).downBitmap(datas.get(position).getWap_thumb());
        holder.tvTitle.setText(datas.get(position).getTitle());
        holder.tv1.setText(datas.get(position).getSource());
        holder.tv2.setText(datas.get(position).getNickname());
        holder.tv3.setText(datas.get(position).getCreate_time());
        Glide.with(context).load(datas.get(position).getWap_thumb()).into(holder.imageView);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView  tvTitle,tv1,tv2,tv3;
    }
}
