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
import com.bearkiddiary.enterprise.ui.fragment.ContactsFragment;

public class ContactsActivity extends BaseActivity {
    private static final int TABONE = 0;
    private static final int TABTWO = 1;

    protected RelativeLayout rl_tab1, rl_tab2;
    protected ImageView[] iv_tab = new ImageView[2];
    private Fragment[] mFragments = new Fragment[2];
    protected int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        initView();
        initContain();
    }

    private void initView() {
        findViewById(R.id.iv_title_back_contacts).setOnClickListener(view -> finish());
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_contacts_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_contacts_tab2);
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_contacts_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_contacts_tab2);

        mFragments[TABONE] = ContactsFragment.newInstance(ContactsFragment.COLLEAGUE);
        mFragments[TABTWO] = ContactsFragment.newInstance(ContactsFragment.PARENTS);
    }

    private void initContain() {
        selected = TABONE;
        iv_tab[selected].setVisibility(View.VISIBLE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_contacts_contain, mFragments[TABONE]);
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
                        .add(R.id.activity_contacts_contain, mFragments[tabNum])
                        .commit();
            }
            selected = tabNum;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ContactsActivity.class));
    }
}
