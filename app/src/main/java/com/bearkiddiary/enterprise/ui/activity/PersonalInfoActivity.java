package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;

/**
 * Created by YarenChoi on 2016/7/22.
 * 个人信息界面
 */
public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener{

    protected ImageView back;
    protected RelativeLayout avatarRL;
    protected LinearLayout nameLL;
    protected LinearLayout genderLL;
    protected LinearLayout phoneLL;
    protected LinearLayout addressLL;
    protected LinearLayout emailLL;

    protected TextView tv_name;
    protected TextView tv_gender;
    protected TextView tv_address;
    protected TextView tv_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.iv_title_back_personal_info);
        avatarRL = (RelativeLayout) findViewById(R.id.rl_personal_info_avatar);
        nameLL = (LinearLayout) findViewById(R.id.ll_personal_info_name);
        genderLL = (LinearLayout) findViewById(R.id.ll_personal_info_gender);
        phoneLL = (LinearLayout) findViewById(R.id.ll_personal_info_phone);
        addressLL = (LinearLayout) findViewById(R.id.ll_personal_info_address);
        emailLL = (LinearLayout) findViewById(R.id.ll_personal_info_email);

        tv_name = (TextView) findViewById(R.id.tv_personal_info_name);
        tv_gender = (TextView) findViewById(R.id.tv_personal_info_gender);
        tv_address = (TextView) findViewById(R.id.tv_personal_info_address);
        tv_email = (TextView) findViewById(R.id.tv_personal_info_email);

        back.setOnClickListener(this);
        avatarRL.setOnClickListener(this);
        nameLL.setOnClickListener(this);
        genderLL.setOnClickListener(this);
        addressLL.setOnClickListener(this);
        emailLL.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back_personal_info:
                finish();
                break;
            case R.id.rl_personal_info_avatar://修改头像
                break;
            case R.id.ll_personal_info_name://修改姓名
                PersonalInfoEditActivity.startActivity(
                        PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_NAME,
                        tv_name.getText().toString());
//                expandView(nameEditLL);
                break;
            case R.id.ll_personal_info_gender://修改性别
                PersonalInfoEditActivity.startActivity(
                        PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_GENDER,
                        tv_gender.getText().toString());
//                expandView(genderEditLL);
                break;
            case R.id.ll_personal_info_address://修改地址
                PersonalInfoEditActivity.startActivity(
                        PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_ADDRESS,
                        tv_address.getText().toString());
//                expandView(addressEditLL);
                break;
            case R.id.ll_personal_info_email://修改Email
                PersonalInfoEditActivity.startActivity(
                        PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_EMAIL,
                        tv_email.getText().toString());
//                expandView(emailEditLL);
                break;
            default:
                break;
        }
    }

//    private void expandView(LinearLayout ll) {
//        hideSoftInputView();
//        if (ll.getLayoutParams().height == 0) {
//            ValueAnimator animator = ValueAnimator.ofInt(0, 200).setDuration(300);
//            animator.addUpdateListener(valueAnimator -> {
//                ViewGroup.LayoutParams lp = ll.getLayoutParams();
//                lp.height = (int) valueAnimator.getAnimatedValue();
//                ll.setLayoutParams(lp);
//            });
//            animator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    EditText et = (EditText) ll.getChildAt(0);
//                    et.requestFocus();
//                    imm = (InputMethodManager) et.getContext().getSystemService(INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                }
//            });
//            animator.start();
//        } else {
//            ValueAnimator animator = ValueAnimator.ofInt(200, 0).setDuration(200);
//            animator.addUpdateListener(valueAnimator -> {
//                ViewGroup.LayoutParams lp = ll.getLayoutParams();
//                lp.height = (int) valueAnimator.getAnimatedValue();
//                ll.setLayoutParams(lp);
//            });
//            animator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                    ViewGroup.LayoutParams lp = ll.getLayoutParams();
//                    lp.height = 0;
//                    ll.setLayoutParams(lp);
//                }
//            });
//            animator.start();
//        }
//    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, PersonalInfoActivity.class));
    }
}
