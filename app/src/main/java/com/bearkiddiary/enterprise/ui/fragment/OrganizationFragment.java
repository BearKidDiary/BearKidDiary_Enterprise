package com.bearkiddiary.enterprise.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bearkiddiary.enterprise.R;

/**
 * Created by admin on 2016/7/22.
 */
public class OrganizationFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organization, container, false);
        initView(view);
        return view;
    }

    private final void initView(View view){

    }
}
