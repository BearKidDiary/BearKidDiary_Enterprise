package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.AttendanceCountFragment;
import com.bearkiddiary.enterprise.ui.fragment.AttendanceSettingFragment;
import com.bearkiddiary.enterprise.ui.fragment.AttendanceAddFragment;

public class WorkAttendanceActivity extends BaseActivity {
    private static final String TAG = "WorkAttendanceActivity";

    public static final int TABONE = 0;
    public static final int TABTWO = 1;
    public static final int TABTHREE = 2;

    protected RelativeLayout rl_tab1, rl_tab2;
    protected ImageView iv_tab1, iv_tab2;
    private Fragment[] mFragments = new Fragment[3];
    protected int selected;

    protected TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_attendance);
        initTabView();
    }

    private void initTabView() {
        findViewById(R.id.iv_title_back_attendance).setOnClickListener(view -> finish());
        tv_title = (TextView) findViewById(R.id.tv_title_work_attendance);
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_work_attendance_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_work_attendance_tab2);
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
        iv_tab1 = (ImageView) findViewById(R.id.iv_work_attendance_tab1);
        iv_tab2 = (ImageView) findViewById(R.id.iv_work_attendance_tab2);

        initContain();
    }

    private void initContain() {
        selected = TABONE;
        mFragments[TABONE] = new AttendanceSettingFragment();
        mFragments[TABTWO] = new AttendanceCountFragment();
        mFragments[TABTHREE] = new AttendanceAddFragment();
        tv_title.setText("考勤组管理");
        iv_tab1.setVisibility(View.VISIBLE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_work_attendance_contain, mFragments[selected]);
        transaction.commit();
    }

    public void changeTab(int tabNum) {
        if (tabNum == selected) {
            return;
        }
        switch (tabNum) {
            case TABONE:
                tv_title.setText("考勤组管理");
                iv_tab1.setVisibility(View.VISIBLE);
                iv_tab2.setVisibility(View.GONE);
                break;
            case TABTWO:
                tv_title.setText("考勤统计");
                iv_tab1.setVisibility(View.GONE);
                iv_tab2.setVisibility(View.VISIBLE);
                break;
            case TABTHREE:
                tv_title.setText("考勤设置");
                break;
            default:
                break;
        }
        if (mFragments[tabNum].isAdded()){
            getSupportFragmentManager().beginTransaction()
                    .hide(mFragments[selected])
                    .show(mFragments[tabNum])
                    .commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .hide(mFragments[selected])
                    .add(R.id.activity_work_attendance_contain, mFragments[tabNum])
                    .commit();
        }
        selected = tabNum;
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, WorkAttendanceActivity.class));
    }
}
