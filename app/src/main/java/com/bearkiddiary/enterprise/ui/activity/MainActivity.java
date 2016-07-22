package com.bearkiddiary.enterprise.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.MessageFragment;
import com.bearkiddiary.enterprise.ui.fragment.OrganizationFragment;
import com.bearkiddiary.enterprise.ui.fragment.IndividualFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout[] tab = new LinearLayout[3];
    private int selected;
    private static int[] tabIcon = {R.drawable.main_msg, R.drawable.main_msg, R.drawable.main_setting};
    private static int[] selectedIcon = {R.drawable.main_selected_msg, R.drawable.main_selected_msg, R.drawable.main_selected_setting};
    private Fragment[] mFragments = new Fragment[3];
    //private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initPresenter();
        //initAccount();//判断是否登陆，跳转登陆界面
        initTabView();
        initContain();//设置导航栏图标
    }

    private void initPresenter() {
        //presenter = new MainPresenter(this);
    }

    //private void initAccount() {
    //    presenter.init();
    //}

    private void initTabView() {
        tab[0] = (LinearLayout) findViewById(R.id.activity_main_tab1);
        tab[1] = (LinearLayout) findViewById(R.id.activity_main_tab2);
        tab[2] = (LinearLayout) findViewById(R.id.activity_main_tab3);
        tab[0].setOnClickListener(this);
        tab[1].setOnClickListener(this);
        tab[2].setOnClickListener(this);
    }

    private void initContain() {
        for (int i = 0; i < 3; i++) {
            ((ImageView) tab[i].getChildAt(0)).setImageResource(tabIcon[i]);
        }

        selected = 0;
        ((ImageView) tab[selected].getChildAt(0)).setImageResource(selectedIcon[selected]);
        ((TextView) tab[selected].getChildAt(1)).setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mFragments[0] = new MessageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_contain, mFragments[0]);
        transaction.commit();
    }

    private void changeTab(int tabNum) {
        onTabSelected(tabNum);
        if (mFragments[tabNum] == null) {
            switch (tabNum) {
                case 0:
                    mFragments[0] = new MessageFragment();
                    break;
                case 1:
                    mFragments[1] = new OrganizationFragment();
                    break;
                case 2:
                    mFragments[2] = new IndividualFragment();
                    break;
                default:
                    break;
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_contain, mFragments[tabNum]);
        transaction.commit();
    }

    private void onTabSelected(int tabNum) {
        if (selected == tabNum) return;
        ((ImageView) tab[selected].getChildAt(0)).setImageResource(tabIcon[selected]);
        ((TextView) tab[selected].getChildAt(1)).setTextColor(Color.BLACK);
        ((ImageView) tab[tabNum].getChildAt(0)).setImageResource(selectedIcon[tabNum]);
        ((TextView) tab[tabNum].getChildAt(1)).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        selected = tabNum;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_tab1:
                changeTab(0);
                break;
            case R.id.activity_main_tab2:
                changeTab(1);
                break;
            case R.id.activity_main_tab3:
                changeTab(2);
                break;
            default:
                break;
        }
    }
}
