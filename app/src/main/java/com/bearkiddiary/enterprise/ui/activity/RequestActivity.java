package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;

public class RequestActivity extends BaseActivity {
    private static final int TABONE = 0;
    private static final int TABTWO = 1;

    protected RelativeLayout rl_tab1, rl_tab2;
    protected ImageView[] iv_tab = new ImageView[2];
    protected int selected;

    protected LinearLayout ll_module;
    protected ListView lv_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        initView();
        initContain();
    }

    private void initView() {
        findViewById(R.id.iv_title_back_request).setOnClickListener(v -> finish());
        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_request_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_request_tab2);
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_request_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_request_tab2);

        ll_module = (LinearLayout) findViewById(R.id.ll_request_module);
        lv_request = (ListView) findViewById(R.id.lv_my_request);
    }

    private void initContain() {
        selected = TABONE;
        iv_tab[selected].setVisibility(View.VISIBLE);
    }

    private void changeTab(int tabNum) {
        if (tabNum == selected) {
            return;
        }
        iv_tab[selected].setVisibility(View.GONE);
        iv_tab[tabNum].setVisibility(View.VISIBLE);
        switch (tabNum) {
            case TABONE:
                ll_module.setVisibility(View.VISIBLE);
                lv_request.setVisibility(View.GONE);
                break;
            case TABTWO:
                ll_module.setVisibility(View.GONE);
                lv_request.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, RequestActivity.class));
    }
}
