package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bearkiddiary.enterprise.R;

public class GradeKidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_kid);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, GradeKidActivity.class));
    }
}
