package com.dream.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dream.entity.ClassesEntity;
import com.dream.fragment.GgpdFragment;
import com.util.Constants;

import java.util.List;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public class ClassesAdapter extends FragmentPagerAdapter {
    private List<ClassesEntity.ResultBean> classDatas;
    public ClassesAdapter(FragmentManager fm,List<ClassesEntity.ResultBean> classDatas) {
        super(fm);
        this.classDatas=classDatas;
    }

    @Override
    public Fragment getItem(int position) {

        return GgpdFragment.getInstance(Constants.URL_DIANTAI);
    }

    @Override
    public int getCount() {
        return classDatas.size();
    }
}
