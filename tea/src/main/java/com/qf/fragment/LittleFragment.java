package com.qf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qf.tea.R;

/**
 * Created by ${WU} on 2016/9/14.
 */
public class LittleFragment extends Fragment {
    private ImageView iv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgent_lit,null);
        iv = (ImageView) view.findViewById(R.id.litf_iv);

        return view;
    }

    public static LittleFragment getInctance(String url){
        LittleFragment littleFragment = new LittleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        littleFragment.setArguments(bundle);
        return littleFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
//      new DownUtil().with(iv).downBitmap(url);
        Glide.with(getActivity()).load(url).into(iv);
    }
}
