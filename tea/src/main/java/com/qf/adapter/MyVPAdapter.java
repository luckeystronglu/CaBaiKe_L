package com.qf.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qf.fragment.HaveHeadFragment;
import com.qf.fragment.MyFragment;

/**
 * Created by ${WU} on 2016/9/13.
 */
public class MyVPAdapter extends FragmentPagerAdapter {
    private int [] data;

    public MyVPAdapter(FragmentManager fm,int [] data)  {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new HaveHeadFragment();
        }
        return MyFragment.geInstance(position);
    }

    @Override
    public int getCount() {
        return data.length;
    }
}
