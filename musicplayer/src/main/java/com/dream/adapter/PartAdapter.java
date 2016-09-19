package com.dream.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.entity.PartEntity;
import com.dream.musicplayer.R;
import com.dream.task.AbsAsyncTask;
import com.util.Constants;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class PartAdapter extends BaseAdapter {
    private Context context;
    private List<PartEntity.ResultBean.SonglistBean> partlist;

    public PartAdapter(Context context,List<PartEntity.ResultBean.SonglistBean> partlist){
        this.context=context;
        this.partlist=partlist;

    }
    @Override
    public int getCount() {
        return partlist.size();
    }

    @Override
    public Object getItem(int position) {
        return partlist.get(position);
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
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listpart_layout,null);
            viewHolder.iv_part= (ImageView) convertView.findViewById(R.id.iv_part);
            viewHolder.tv_singname= (TextView) convertView.findViewById(R.id.tv_singname);
            viewHolder.tv_singer= (TextView) convertView.findViewById(R.id.tv_singer);
            convertView.setTag(viewHolder);
        }

        viewHolder.tv_singname.setText(partlist.get(position).getTitle());
        viewHolder.tv_singer.setText(partlist.get(position).getArtist());
        new AbsAsyncTask(AbsAsyncTask.DownType.IMAGE).with(viewHolder.iv_part).execute(partlist.get(position).getThumb());

        return convertView;
    }

    class ViewHolder{
        ImageView iv_part;
        TextView tv_singname,tv_singer;
    }
}
