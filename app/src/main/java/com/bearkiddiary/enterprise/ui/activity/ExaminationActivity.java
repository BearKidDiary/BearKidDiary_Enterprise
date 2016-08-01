package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.ExaminedFragment;
import com.bearkiddiary.enterprise.ui.fragment.UnexaminedFragment;

/**
 * Created by YarenChoi on 2016/8/1.
 * 审批界面
 */

public class ExaminationActivity extends BaseActivity {
    private static final int TABONE = 0;
    private static final int TABTWO = 1;

    protected RelativeLayout rl_tab1, rl_tab2;
    protected ImageView iv_tab1, iv_tab2;
    private Fragment[] mFragments = new Fragment[2];
    protected int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        initTabView();
    }

    private void initTabView() {
        findViewById(R.id.iv_title_back_examination).setOnClickListener(view -> finish());

        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_examination_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_examination_tab2);
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
        iv_tab1 = (ImageView) findViewById(R.id.iv_examination_tab1);
        iv_tab2 = (ImageView) findViewById(R.id.iv_examination_tab2);

        selected = -1;
        changeTab(TABONE);
    }

    private void changeTab(int tabNum) {
        if (tabNum == selected) {
            return;
        } else {
            selected = tabNum;
        }
        switch (tabNum) {
            case TABONE:
                iv_tab1.setVisibility(View.VISIBLE);
                iv_tab2.setVisibility(View.GONE);
                if (mFragments[tabNum] == null)
                    mFragments[TABONE] = new UnexaminedFragment();
                break;
            case TABTWO:
                iv_tab1.setVisibility(View.GONE);
                iv_tab2.setVisibility(View.VISIBLE);
                if (mFragments[tabNum] == null)
                    mFragments[TABTWO] = new ExaminedFragment();
                break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_work_attendance_contain, mFragments[tabNum]);
        transaction.commit();
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, ExaminationActivity.class));
    }
}
