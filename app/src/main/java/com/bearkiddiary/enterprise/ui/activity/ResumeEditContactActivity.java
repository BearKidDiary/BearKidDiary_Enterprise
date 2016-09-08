package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.bearkiddiary.enterprise.R;

/**
 * Created by YarenChoi on 2016/7/27.
 * 修改个人简历联系方式界面
 */
public class ResumeEditContactActivity extends BaseActivity {
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String QQ = "qq";

    protected EditText phoneEt;
    protected EditText emailEt;
    protected EditText qqEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_edit_contact);
        initView();
    }

    private void initView() {
        phoneEt = (EditText) findViewById(R.id.et_resume_phone);
        emailEt = (EditText) findViewById(R.id.et_resume_mail);
        qqEt = (EditText) findViewById(R.id.et_resume_qq);

        Button save = (Button) findViewById(R.id.btn_resume_contact_save);

        save.setOnClickListener(view -> {

        });

        phoneEt.setText(getIntent().getStringExtra(PHONE));
        phoneEt.setSelection(getIntent().getStringExtra(PHONE).length());
        emailEt.setText(getIntent().getStringExtra(EMAIL));
        qqEt.setText(getIntent().getStringExtra(QQ));
    }

    public static void startActivity(Context context, String phone, String email, String qq){
        Intent intent = new Intent(context, ResumeEditContactActivity.class);
        intent.putExtra(PHONE, phone);
        intent.putExtra(EMAIL, email);
        intent.putExtra(QQ, qq);
        context.startActivity(intent);
    }
}
