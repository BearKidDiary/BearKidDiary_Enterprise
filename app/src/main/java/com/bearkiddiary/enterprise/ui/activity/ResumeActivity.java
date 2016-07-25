package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.activity.iactivity.IResumeView;

public class ResumeActivity extends BaseActivity implements IResumeView, View.OnClickListener{
    protected ImageView avatarIv;
    protected TextView nameTv;
    protected TextView majorTv;
    protected TextView addressTv;

    protected TextView emailTv;
    protected TextView phoneNumTv;
    protected TextView qqTv;

    protected LinearLayout ll_work;
    protected LinearLayout ll_edu;
    protected TextView specialtiesTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        initView();
    }

    private void initView() {
        avatarIv = (ImageView) findViewById(R.id.iv_resume_avatar);
        nameTv = (TextView) findViewById(R.id.tv_resume_name);
        majorTv = (TextView) findViewById(R.id.tv_resume_major);
        addressTv = (TextView) findViewById(R.id.tv_resume_address);
        emailTv = (TextView) findViewById(R.id.tv_resume_email);
        phoneNumTv = (TextView) findViewById(R.id.tv_resume_phonenum);
        qqTv = (TextView) findViewById(R.id.tv_resume_qq);

        ll_work = (LinearLayout) findViewById(R.id.ll_resume_work);
        ll_edu = (LinearLayout) findViewById(R.id.ll_resume_education);
        specialtiesTv = (TextView) findViewById(R.id.tv_resume_specialties);

        ImageView back = (ImageView) findViewById(R.id.iv_title_back_resume);
        TextView editInfoTv = (TextView) findViewById(R.id.tv_resume_edit_info);
        TextView editContactTv = (TextView) findViewById(R.id.tv_resume_edit_contact);

        back.setOnClickListener(this);
        editInfoTv.setOnClickListener(this);
        editContactTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back_resume:
                finish();
                break;
            case R.id.tv_resume_edit_info:
                break;
            case R.id.tv_resume_edit_contact:
                break;
            default:
                break;
        }
    }

    @Override
    public void setAvatar(int imgResource) {
        avatarIv.setImageResource(imgResource);
    }

    @Override
    public void setName(String name) {
        nameTv.setText(name);
    }

    @Override
    public void setMajor(String major) {
        majorTv.setText(major);
    }

    @Override
    public void setAddress(String address) {
        addressTv.setText(address);
    }

    @Override
    public void setEmail(String email) {
        emailTv.setText(email);
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        phoneNumTv.setText(phoneNum);
    }

    @Override
    public void setQQ(String qq) {
        qqTv.setText(qq);
    }

    @Override
    public void showWorkExperience() {

    }

    @Override
    public void showEduExperience() {

    }

    @Override
    public void setSpecialties(String specialties) {
        specialtiesTv.setText(specialties);
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, ResumeActivity.class));
    }
}
