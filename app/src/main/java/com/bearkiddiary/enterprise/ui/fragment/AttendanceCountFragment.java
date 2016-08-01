package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.ifragment.IAttendanceCountFragment;

import java.util.Calendar;

/**
 * Created by yarenChoi on 2016/7/28.
 * 考勤统计界面
 */
public class AttendanceCountFragment extends BaseFragment implements IAttendanceCountFragment{
    private Context mContext;
    private Calendar calendar;
    protected RelativeLayout rl_minus;
    protected RelativeLayout rl_plus;
    protected TextView tv_calendar;
    protected TextView tv_normal; //正常打卡
    protected TextView tv_absence; //未打卡
    protected TextView tv_leave; //请假
    protected TextView tv_late; //迟到
    protected TextView tv_absenteeism; //旷工

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance_count, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        calendar = Calendar.getInstance();
        rl_minus = (RelativeLayout) view.findViewById(R.id.rl_calendar_minus);
        rl_plus = (RelativeLayout) view.findViewById(R.id.rl_calendar_plus);
        tv_calendar = (TextView) view.findViewById(R.id.tv_attendance_count_calendar);
        tv_normal = (TextView) view.findViewById(R.id.tv_attendance_count_normal);
        tv_absence = (TextView) view.findViewById(R.id.tv_attendance_count_absence);
        tv_leave = (TextView) view.findViewById(R.id.tv_attendance_count_leave);
        tv_late = (TextView) view.findViewById(R.id.tv_attendance_count_late);
        tv_absenteeism = (TextView) view.findViewById(R.id.tv_attendance_count_absenteeism);

        rl_minus.setOnClickListener(v -> {
            calendarMinus();
            refreshUI();
        });
        rl_plus.setOnClickListener(v -> {
            calendarIncrease();
            refreshUI();
        });

        refreshUI();
    }


    private void calendarIncrease() {
        calendar.add(Calendar.DATE, 1);
        refreshUI();
    }

    private void calendarMinus() {
        calendar.add(Calendar.DATE, -1);
        refreshUI();
    }

    private void refreshUI() {
        showCalendar();
        setCount(0,0,0,0,0);
    }

    @Override
    public void showCalendar() {
        String date =
                calendar.get(Calendar.DAY_OF_WEEK) + " " +
                calendar.get(Calendar.YEAR) + "-" +
                calendar.get(Calendar.MONTH) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH);
        tv_calendar.setText(date);
    }

    @Override
    public void setCount(int normal, int absence, int leave, int late, int absenteeism) {
        tv_normal.setText(normal);
        tv_absence.setText(absence);
        tv_leave.setText(leave);
        tv_late.setText(late);
        tv_absenteeism.setText(absenteeism);
    }
}
