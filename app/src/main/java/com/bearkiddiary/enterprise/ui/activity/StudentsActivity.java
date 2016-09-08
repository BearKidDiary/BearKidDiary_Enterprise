package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.ContactsFragment;

/**
 * Created by YarenChoi on 2016/8/10.
 * 学生列表界面
 */
public class StudentsActivity extends BaseFragmentActivity {

    protected RelativeLayout rl_tab1, rl_tab2, rl_tab3;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_student);
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_student_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_student_tab2);
        rl_tab3 = (RelativeLayout) findViewById(R.id.rl_student_tab3);
        iv_tab = new ImageView[3];
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_student_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_student_tab2);
        iv_tab[TABTHREE] = (ImageView) findViewById(R.id.iv_student_tab3);
        contain = (FrameLayout) findViewById(R.id.activity_student_contain);
    }

    @Override
    protected void setFragment() {
        mFragments = new Fragment[3];
        mFragments[TABONE] = ContactsFragment.newInstance(ContactsFragment.GRADUATED);
        mFragments[TABTWO] = ContactsFragment.newInstance(ContactsFragment.ASSIGNED);
        mFragments[TABTHREE] = ContactsFragment.newInstance(ContactsFragment.UNASSIGNED);
    }

    @Override
    protected void setUpView() {
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
        rl_tab3.setOnClickListener(view -> changeTab(TABTHREE));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, StudentsActivity.class));
    }
}
