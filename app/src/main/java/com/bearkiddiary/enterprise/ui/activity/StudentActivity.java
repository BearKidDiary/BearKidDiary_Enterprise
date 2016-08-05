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
import com.bearkiddiary.enterprise.ui.fragment.StudentAssignedFragment;
import com.bearkiddiary.enterprise.ui.fragment.StudentGraduatedFragment;
import com.bearkiddiary.enterprise.ui.fragment.StudentUnassignedFragment;

/**
 * Created by YarenChoi on 2016/8/1.
 * 学生界面
 */

public class StudentActivity extends BaseActivity {
    private static final int TABONE = 0;
    private static final int TABTWO = 1;
    private static final int TABTHREE = 2;

    protected RelativeLayout rl_tab1, rl_tab2, rl_tab3;
    protected ImageView[] iv_tab = new ImageView[3];
    private Fragment[] mFragments = new Fragment[3];
    protected int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_title_back_student).setOnClickListener(view -> finish());

        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_student_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_student_tab2);
        rl_tab3 = (RelativeLayout) findViewById(R.id.rl_student_tab3);
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
        rl_tab3.setOnClickListener(view -> changeTab(TABTHREE));
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_student_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_student_tab2);
        iv_tab[TABTHREE] = (ImageView) findViewById(R.id.iv_student_tab3);

        mFragments[TABONE] = new StudentGraduatedFragment();
        mFragments[TABTWO] = new StudentAssignedFragment();
        mFragments[TABTHREE] = new StudentUnassignedFragment();

        initContain();
    }

    private void initContain() {
        selected = TABONE;
        iv_tab[selected].setVisibility(View.VISIBLE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_student_contain, mFragments[TABONE]);
        transaction.commit();
    }

    private void changeTab(int tabNum) {
        if (tabNum == selected) {
            return;
        }
        iv_tab[selected].setVisibility(View.GONE);
        iv_tab[tabNum].setVisibility(View.VISIBLE);
        changeFragment(tabNum);
    }

    /**
     * 切换fragment
     * @param tabNum 目标fragment
     */
    public void changeFragment(int tabNum){
        if (mFragments[tabNum] != mFragments[selected]){
            if (mFragments[tabNum].isAdded()){
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[selected])
                        .show(mFragments[tabNum])
                        .commit();
            }else {
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[selected])
                        .add(R.id.activity_student_contain, mFragments[tabNum])
                        .commit();
            }
            selected = tabNum;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, StudentActivity.class));
    }
}
