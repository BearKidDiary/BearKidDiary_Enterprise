package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;

public class AddEmployeeActivity extends BaseActivity implements View.OnClickListener{
    protected TextView showContact;
    protected EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        initView();
    }

    private void initView() {
        content = (EditText) findViewById(R.id.et_add_employee_content);
        showContact = (TextView) findViewById(R.id.tv_add_employee_show);

        findViewById(R.id.iv_title_back_add_employee).setOnClickListener(this);
        findViewById(R.id.iv_add_employee).setOnClickListener(this);
        findViewById(R.id.btn_add_employee).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back_add_employee:
                finish();
                break;
            case R.id.iv_add_employee://添加联系人
                break;
            case R.id.btn_add_employee://发送信息
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AddEmployeeActivity.class));
    }
}
