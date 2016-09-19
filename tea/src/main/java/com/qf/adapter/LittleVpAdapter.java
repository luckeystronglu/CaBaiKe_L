package com.qf.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qf.entity.HeadBanner;
import com.qf.fragment.LittleFragment;

import java.util.List;

/**
 * Created by ${WU} on 2016/9/14.
 */
public class LittleVpAdapter extends FragmentPagerAdapter {
    private List<HeadBanner.DataBean> datas;


    public LittleVpAdapter(FragmentManager fm,List<HeadBanner.DataBean> datas) {
        super(fm);
        this.datas =datas;
    }

    @Override
    public Fragment getItem(int position) {
        return LittleFragment.getInctance(datas.get(position).getImage());
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
