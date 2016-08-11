package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bearkiddiary.enterprise.R;

public class AskForLeaveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_leave);
        initView();
    }

    private void initView() {

    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, AskForLeaveActivity.class));
    }
}
