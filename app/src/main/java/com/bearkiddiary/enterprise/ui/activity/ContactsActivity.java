package com.bearkiddiary.enterprise.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.fragment.ContactsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YarenChoi on 2016/8/16.
 * 联系人界面
 */
public class ContactsActivity extends BaseFragmentActivity {

    protected RelativeLayout rl_tab1, rl_tab2;
    public ArrayList<Long> chooseContacts;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_contacts);
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_contacts_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_contacts_tab2);
        iv_tab = new ImageView[2];
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_contacts_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_contacts_tab2);
        contain = (FrameLayout) findViewById(R.id.activity_contacts_contain);

        chooseContacts = new ArrayList<>();
    }

    @Override
    protected void setFragment() {
        mFragments = new Fragment[2];
        mFragments[TABONE] = ContactsFragment.newInstance(ContactsFragment.COLLEAGUE);
        mFragments[TABTWO] = ContactsFragment.newInstance(ContactsFragment.PARENTS);
    }

    @Override
    protected void setUpView() {
        findViewById(R.id.iv_title_back_contacts).setOnClickListener(view -> finish());
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
    }

    public void returnContacts(View view) {
        Intent intent = this.getIntent();
        intent.putExtra("contactsId", chooseContacts);
        this.setResult(RESULT_OK, intent);
        finish();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ContactsActivity.class));
    }

    public static void startActivityForResult(Activity activity, int RESULT_CODE) {
        Intent intent = new Intent(activity, ContactsActivity.class);
        activity.startActivityForResult(intent, RESULT_CODE);
    }
}
