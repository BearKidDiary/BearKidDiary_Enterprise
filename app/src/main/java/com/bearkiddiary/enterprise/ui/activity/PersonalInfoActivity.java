package com.bearkiddiary.enterprise.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;

/**
 * Created by yarenChoi on 2016/7/22.
 * 个人信息界面
 */
public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener{

    protected ImageView back;
    protected RelativeLayout avatarRL;
    protected RelativeLayout nameRL;
    protected RelativeLayout genderRL;
    protected RelativeLayout phoneRL;
    protected RelativeLayout addressRL;
    protected RelativeLayout emailRL;

    protected LinearLayout nameEditLL;
    protected LinearLayout genderEditLL;
    protected LinearLayout addressEditLL;
    protected LinearLayout emailEditLL;

    private InputMethodManager imm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.iv_title_back_personal_info);
        avatarRL = (RelativeLayout) findViewById(R.id.rl_personal_info_avatar);
        nameRL = (RelativeLayout) findViewById(R.id.rl_personal_info_name);
        genderRL = (RelativeLayout) findViewById(R.id.rl_personal_info_gender);
        phoneRL = (RelativeLayout) findViewById(R.id.rl_personal_info_phone);
        addressRL = (RelativeLayout) findViewById(R.id.rl_personal_info_address);
        emailRL = (RelativeLayout) findViewById(R.id.rl_personal_info_email);

        nameEditLL = (LinearLayout) findViewById(R.id.ll_personal_info_edit_name);
        genderEditLL = (LinearLayout) findViewById(R.id.ll_personal_info_edit_gender);
        addressEditLL = (LinearLayout) findViewById(R.id.ll_personal_info_edit_address);
        emailEditLL = (LinearLayout) findViewById(R.id.ll_personal_info_edit_email);

        back.setOnClickListener(this);
        avatarRL.setOnClickListener(this);
        nameRL.setOnClickListener(this);
        genderRL.setOnClickListener(this);
        addressRL.setOnClickListener(this);
        emailRL.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back_personal_info:
                finish();
                break;
            case R.id.rl_personal_info_avatar:
                break;
            case R.id.rl_personal_info_name:
                expandView(nameEditLL);
                break;
            case R.id.rl_personal_info_gender:
                expandView(genderEditLL);
                break;
            case R.id.rl_personal_info_address:
                expandView(addressEditLL);
                break;
            case R.id.rl_personal_info_email:
                expandView(emailEditLL);
                break;
            default:
                break;
        }
    }

    private void expandView(LinearLayout ll) {
        hideSoftInputView();
        if (ll.getLayoutParams().height == 0) {
            ValueAnimator animator = ValueAnimator.ofInt(0, 200).setDuration(300);
            animator.addUpdateListener(valueAnimator -> {
                ViewGroup.LayoutParams lp = ll.getLayoutParams();
                lp.height = (int) valueAnimator.getAnimatedValue();
                ll.setLayoutParams(lp);
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    EditText et = (EditText) ll.getChildAt(0);
                    et.requestFocus();
                    imm = (InputMethodManager) et.getContext().getSystemService(INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });
            animator.start();
        } else {
            ValueAnimator animator = ValueAnimator.ofInt(200, 0).setDuration(200);
            animator.addUpdateListener(valueAnimator -> {
                ViewGroup.LayoutParams lp = ll.getLayoutParams();
                lp.height = (int) valueAnimator.getAnimatedValue();
                ll.setLayoutParams(lp);
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    ViewGroup.LayoutParams lp = ll.getLayoutParams();
                    lp.height = 0;
                    ll.setLayoutParams(lp);
                }
            });
            animator.start();
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, PersonalInfoActivity.class));
    }
}
