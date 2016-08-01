package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bearkiddiary.enterprise.R;

/**
 * Created by YarenChoi on 2016/8/1.
 * 已审批的界面
 */
public class ExaminedFragment extends BaseFragment {
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unexamined, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
