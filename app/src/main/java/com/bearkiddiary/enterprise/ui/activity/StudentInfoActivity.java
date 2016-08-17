package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.StudentInfoDetailFragment;
import com.bearkiddiary.enterprise.ui.fragment.StudentInfoSettingFragment;

/**
 * Created by YarenChoi on 2016/8/10.
 * 学生信息界面
 */
public class StudentInfoActivity extends BaseFragmentActivity {

    protected RelativeLayout rl_tab1, rl_tab2;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_student_info);
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_student_info_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_student_info_tab2);
        iv_tab = new ImageView[2];
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_student_info_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_student_info_tab2);
        contain = (FrameLayout) findViewById(R.id.activity_student_info_contain);
    }

    @Override
    protected void setFragment() {
        mFragments = new Fragment[2];
        mFragments[TABONE] = new StudentInfoSettingFragment();
        mFragments[TABTWO] = new StudentInfoDetailFragment();
    }

    @Override
    protected void setUpView() {
        findViewById(R.id.iv_title_back_student_info).setOnClickListener(v -> finish());
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, StudentInfoActivity.class));
    }
}
