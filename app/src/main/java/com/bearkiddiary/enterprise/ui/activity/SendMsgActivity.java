package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;

/**
 * Created by YarenChoi on 2016/8/16.
 * 发送信息界面
 */
public class SendMsgActivity extends BaseActivity implements View.OnClickListener{
    private static final String TYPE = "sendMsgType";
    public static final int ADD_EMPLOYEE = 0;//添加员工
    public static final int ADD_STUDENT = 1;//添加学生
    public static final int MASS = 2;//群发
    private int type;

    protected TextView title;
    protected TextView showContact;
    protected EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.tv_title_send_msg);
        content = (EditText) findViewById(R.id.et_send_msg_content);
        showContact = (TextView) findViewById(R.id.tv_send_msg_show);

        findViewById(R.id.iv_send_msg).setOnClickListener(this);
        findViewById(R.id.btn_send_msg).setOnClickListener(this);

        switch (type = getIntent().getIntExtra(TYPE, ADD_EMPLOYEE)) {
            case ADD_EMPLOYEE:
                title.setText(R.string.add_employee);
                break;
            case ADD_STUDENT:
                title.setText(R.string.add_student);
                break;
            case MASS:
                title.setText(R.string.mass);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_send_msg://添加联系人
                break;
            case R.id.btn_send_msg://发送信息
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, SendMsgActivity.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }
}
