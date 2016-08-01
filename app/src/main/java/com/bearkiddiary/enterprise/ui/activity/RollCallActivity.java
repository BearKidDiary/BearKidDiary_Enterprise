package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bearkiddiary.enterprise.R;

public class RollCallActivity extends AppCompatActivity {

    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_call);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RollCallActivity.class));
    }
}
