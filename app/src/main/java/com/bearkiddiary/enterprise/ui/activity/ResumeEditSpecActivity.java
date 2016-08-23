package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bearkiddiary.enterprise.R;

/**
 * Created by YarenChoi on 2016/7/27.
 * 修改个人简历专长界面
 */
public class ResumeEditSpecActivity extends BaseActivity {
    private static final String SPEC = "spec";

    protected EditText specEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_edit_spec);
        initView();
    }

    private void initView() {
        specEt = (EditText) findViewById(R.id.et_resume_spec);

        ImageView back = (ImageView) findViewById(R.id.iv_title_back_resume_edit_spec);
        Button save = (Button) findViewById(R.id.btn_resume_spec_save);

        back.setOnClickListener(view -> finish());
        save.setOnClickListener(view -> {

        });

        specEt.setText(getIntent().getStringExtra(SPEC));
        specEt.setSelection(getIntent().getStringExtra(SPEC).length());
    }

    public static void startActivity(Context context, String spec){
        Intent intent = new Intent(context, ResumeEditSpecActivity.class);
        intent.putExtra(SPEC, spec);
        context.startActivity(intent);
    }
}
