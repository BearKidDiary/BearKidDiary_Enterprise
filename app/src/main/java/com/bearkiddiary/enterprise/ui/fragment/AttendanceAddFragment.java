package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.utils.DateTimePickerUtil;

/**
 * Created by YarenChoi on 2016/7/28.
 * 添加考勤组界面
 */
public class AttendanceAddFragment extends BaseFragment {
    private Context mContext;
    protected TextView tv_start_time;
    protected TextView tv_end_time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance_add, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        WeekdayHolder weekdayHolder = new WeekdayHolder(view);
        tv_start_time = (TextView) view.findViewById(R.id.tv_attendance_add_start);
        tv_end_time = (TextView) view.findViewById(R.id.tv_attendance_add_end);
        tv_start_time.setOnClickListener(v ->
                DateTimePickerUtil.showTimePicker(mContext, time -> tv_start_time.setText(time)));
        tv_end_time.setOnClickListener(v ->
                DateTimePickerUtil.showTimePicker(mContext, time -> tv_end_time.setText(time)));
    }

    private class WeekdayHolder {
        private TextView[] tv_weekday = new TextView[7];
        public boolean[] isCheck = new boolean[7];
        private boolean lockState;

        public WeekdayHolder(View view) {
            lockState = true;
            LinearLayout ll_weekday = (LinearLayout) view.findViewById(R.id.ll_weekday);
            for (int i = 0; i < 7; i++) {
                final int index = i;
                isCheck[i] = false;
                tv_weekday[i] = (TextView) ll_weekday.getChildAt(i);
                tv_weekday[i].setOnClickListener(v -> {
                    if (lockState)
                        isCheck[index] = !isCheck[index];
                    if (isCheck[index]) {
                        tv_weekday[index].setBackgroundResource(R.drawable.drawable_edit_primary);
                        tv_weekday[index].setTextColor(getResources().getColor(R.color.text_white));
                    } else {
                        tv_weekday[index].setBackgroundResource(R.drawable.drawable_edit_normal);
                        tv_weekday[index].setTextColor(getResources().getColor(R.color.text_gray));
                    }
                    Log.e("WorkAttendance", "Week " + index + " is selected , result: " + isCheck[index]);
                });
            }
        }

        public boolean getCheckState(int index) {
            return isCheck[index];
        }

        public void lock() {
            lockState = false;
        }

        public void open() {
            lockState = true;
        }

    }

}
