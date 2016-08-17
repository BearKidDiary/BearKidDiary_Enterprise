package com.bearkiddiary.enterprise.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by YarenChoi on 2016/8/17.
 *
 */
public abstract class BaseFragmentActivity extends BaseActivity{
    public static final int TABONE = 0;
    public static final int TABTWO = 1;
    public static final int TABTHREE = 2;

    protected ImageView[] iv_tab;
    protected Fragment[] mFragments;
    protected FrameLayout contain;

    protected int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        setFragment();
        initContain();
        setUpView();
    }

    private void initContain() {
        selected = TABONE;
        iv_tab[selected].setVisibility(View.VISIBLE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(contain.getId(), mFragments[TABONE]);
        transaction.commit();
    }

    public void changeTab(int tabNum) {
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
    public void changeFragment(int tabNum) {
        if (mFragments[tabNum].isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(mFragments[selected])
                    .show(mFragments[tabNum])
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .hide(mFragments[selected])
                    .add(contain.getId(), mFragments[tabNum])
                    .commit();
        }
        selected = tabNum;
    }

    /**
     * 初始化界面，获取控件操作
     */
    protected abstract void initContentView();

    /**
     * 获取Fragment实例对象
     */
    protected abstract void setFragment();

    /**
     * 设置属性、监听
     */
    protected abstract void setUpView();
}
