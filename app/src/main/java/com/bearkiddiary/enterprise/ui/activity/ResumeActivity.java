package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    protected ImageView genderIv;

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
        genderIv = (ImageView) findViewById(R.id.iv_resume_gender);
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
        TextView editSpecialtiesTv = (TextView) findViewById(R.id.tv_resume_edit_specialties);
        TextView addWorkExp = (TextView) findViewById(R.id.tv_resume_add_work_exp);
        TextView addEduExp = (TextView) findViewById(R.id.tv_resume_add_edu_exp);

        back.setOnClickListener(this);
        editInfoTv.setOnClickListener(this);
        editContactTv.setOnClickListener(this);
        editSpecialtiesTv.setOnClickListener(this);
        addWorkExp.setOnClickListener(this);
        addEduExp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back_resume:
                finish();
                break;
            case R.id.tv_resume_edit_info:
                int gender = ResumeEditInfoActivity.MALE;
                ResumeEditInfoActivity.startActivity(ResumeActivity.this,
                        nameTv.getText().toString(),
                        gender,
                        addressTv.getText().toString(),
                        majorTv.getText().toString());
                break;
            case R.id.tv_resume_edit_contact:
                ResumeEditContactActivity.startActivity(ResumeActivity.this,
                        phoneNumTv.getText().toString(),
                        emailTv.getText().toString(),
                        qqTv.getText().toString());
                break;
            case R.id.tv_resume_edit_specialties:
                ResumeEditSpecActivity.startActivity(ResumeActivity.this,
                        specialtiesTv.getText().toString());
                break;
            case R.id.tv_resume_add_work_exp:
                break;
            case R.id.tv_resume_add_edu_exp:
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
    public void setGender(int imgResource) {
        genderIv.setImageResource(imgResource);
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
    public void showWorkExperience(String startDate, String endDate, String job, String company, String desc) {
        LinearLayout ll_exp = new LinearLayout(ResumeActivity.this);
        View expView = LayoutInflater.from(ResumeActivity.this).inflate(R.layout.item_resume_experience, ll_exp);
        ((TextView)expView.findViewById(R.id.item_resume_start_date)).setText(startDate);
        ((TextView)expView.findViewById(R.id.item_resume_end_date)).setText(endDate);
        ((TextView)expView.findViewById(R.id.item_resume_tv_1)).setText(job);
        ((TextView)expView.findViewById(R.id.item_resume_tv_2)).setText(company);
        ((TextView)expView.findViewById(R.id.item_resume_tv_3)).setText(desc);
        expView.findViewById(R.id.item_resume_edit).setOnClickListener(view -> {

        });
        expView.findViewById(R.id.item_resume_delete).setOnClickListener(view -> {

        });
        ll_work.addView(ll_exp);
    }

    @Override
    public void showEduExperience(String startDate, String endDate, String major, String school) {
        LinearLayout ll_exp = new LinearLayout(ResumeActivity.this);
        View expView = LayoutInflater.from(ResumeActivity.this).inflate(R.layout.item_resume_experience, ll_exp);
        ((TextView)expView.findViewById(R.id.item_resume_start_date)).setText(startDate);
        ((TextView)expView.findViewById(R.id.item_resume_end_date)).setText(endDate);
        ((TextView)expView.findViewById(R.id.item_resume_tv_1)).setText(major);
        ((TextView)expView.findViewById(R.id.item_resume_tv_2)).setText(school);
        expView.findViewById(R.id.item_resume_edit).setOnClickListener(view -> {

        });
        expView.findViewById(R.id.item_resume_delete).setOnClickListener(view -> {

        });
        ll_edu.addView(ll_exp);
    }

    @Override
    public void setSpecialties(String specialties) {
        specialtiesTv.setText(specialties);
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, ResumeActivity.class));
    }
}
