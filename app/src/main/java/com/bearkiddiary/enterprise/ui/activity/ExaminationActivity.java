package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.ExaminedFragment;
import com.bearkiddiary.enterprise.ui.fragment.UnexaminedFragment;

/**
 * Created by YarenChoi on 2016/8/1.
 * 审批界面
 */
public class ExaminationActivity extends BaseFragmentActivity {

    protected RelativeLayout rl_tab1, rl_tab2;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_examination);
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_examination_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_examination_tab2);
        iv_tab = new ImageView[2];
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_examination_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_examination_tab2);
        contain = (FrameLayout) findViewById(R.id.activity_examination_contain);
    }

    @Override
    protected void setFragment() {
        mFragments = new Fragment[2];
        mFragments[TABONE] = new UnexaminedFragment();
        mFragments[TABTWO] = new ExaminedFragment();
    }

    @Override
    protected void setUpView() {
        findViewById(R.id.iv_title_back_examination).setOnClickListener(view -> finish());
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ExaminationActivity.class));
    }
}
