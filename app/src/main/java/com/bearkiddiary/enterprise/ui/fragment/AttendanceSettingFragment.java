package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.activity.WorkAttendanceActivity;

/**
 * Created by yarenChoi on 2016/7/28.
 * 考勤设置界面
 */
public class AttendanceSettingFragment extends BaseFragment {
    private Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance_setting, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.rl_attendance_add_group).setOnClickListener(view1 ->
                ((WorkAttendanceActivity)getActivity()).changeTab(WorkAttendanceActivity.TABTHREE));

    }
}
