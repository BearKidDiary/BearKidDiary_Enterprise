package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.AttendanceCountFragment;
import com.bearkiddiary.enterprise.ui.fragment.AttendanceSettingFragment;
import com.bearkiddiary.enterprise.ui.fragment.AttendanceAddFragment;

/**
 * Created by YarenChoi on 2016/7/28.
 * 考勤界面
 */
public class WorkAttendanceActivity extends BaseFragmentActivity {

    protected RelativeLayout rl_tab1, rl_tab2;
    protected TextView tv_title;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_work_attendance);
        tv_title = (TextView) findViewById(R.id.tv_title_work_attendance);
        tv_title.setText("考勤组管理");
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_work_attendance_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_work_attendance_tab2);
        iv_tab = new ImageView[2];
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_work_attendance_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_work_attendance_tab2);
        contain = (FrameLayout) findViewById(R.id.activity_work_attendance_contain);
    }

    @Override
    protected void setFragment() {
        mFragments = new Fragment[3];
        mFragments[TABONE] = new AttendanceSettingFragment();
        mFragments[TABTWO] = new AttendanceCountFragment();
        mFragments[TABTHREE] = new AttendanceAddFragment();
    }

    @Override
    protected void setUpView() {
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
    }

    @Override
    public void changeTab(int tabNum) {
        if (tabNum == selected) {
            return;
        }
        switch (tabNum) {
            case TABONE:
                tv_title.setText("考勤组管理");
                iv_tab[TABONE].setVisibility(View.VISIBLE);
                iv_tab[TABTWO].setVisibility(View.GONE);
                break;
            case TABTWO:
                tv_title.setText("考勤统计");
                iv_tab[TABONE].setVisibility(View.GONE);
                iv_tab[TABTWO].setVisibility(View.VISIBLE);
                break;
            case TABTHREE:
                tv_title.setText("考勤设置");
                break;
            default:
                break;
        }
        changeFragment(tabNum);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, WorkAttendanceActivity.class));
    }
}
