package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;

/**
 * Created by YarenChoi on 2016/7/24.
 * 机构信息界面
 */
public class OrganizationInfoActivity extends BaseActivity implements View.OnClickListener{
    protected ImageView avatar;
    protected TextView name;
    protected TextView createTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_info);
        initView();
    }

    private void initView() {
        avatar = (ImageView) findViewById(R.id.iv_organization_info_avatar);
        name = (TextView) findViewById(R.id.tv_organization_info_name);
        createTime = (TextView) findViewById(R.id.tv_organization_info_time);

        ImageView back = (ImageView) findViewById(R.id.iv_title_back_organization_info);
        LinearLayout info = (LinearLayout) findViewById(R.id.ll_organization_info);
        TextView home = (TextView) findViewById(R.id.tv_organization_info_home);
        TextView employee = (TextView) findViewById(R.id.tv_organization_info_employee);
        TextView qr_code = (TextView) findViewById(R.id.tv_organization_info_qr_code);
        TextView identification = (TextView) findViewById(R.id.tv_organization_info_identification);

        back.setOnClickListener(this);
        info.setOnClickListener(this);
        home.setOnClickListener(this);
        employee.setOnClickListener(this);
        qr_code.setOnClickListener(this);
        identification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back_organization_info:
                finish();
                break;
            case R.id.ll_organization_info:
                break;
            case R.id.tv_organization_info_home:
                break;
            case R.id.tv_organization_info_employee:
                break;
            case R.id.tv_organization_info_qr_code:
                break;
            case R.id.tv_organization_info_identification:
                break;
            case R.id.btn_organization_info_dismiss:
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, OrganizationInfoActivity.class));
    }
}
