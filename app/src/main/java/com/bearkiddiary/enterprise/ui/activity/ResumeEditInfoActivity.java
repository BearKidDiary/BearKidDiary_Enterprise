package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bearkiddiary.enterprise.R;

public class ResumeEditInfoActivity extends BaseActivity {
    private static final String NAME = "name";
    private static final String GENDER = "gender";
    private static final String ADDRESS = "address";
    private static final String MAJOR = "major";

    public static final int FEMALE = 0;
    public static final int MALE = 1;

    protected EditText nameEt;
    protected Button maleBtn;
    protected Button femaleBtn;
    protected EditText addressEt;
    protected EditText majorEt;

    protected int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_edit_info);
        initView();
    }

    private void initView() {
        nameEt = (EditText) findViewById(R.id.et_resume_name);
        maleBtn = (Button) findViewById(R.id.btn_resume_gender_male);
        femaleBtn = (Button) findViewById(R.id.btn_resume_gender_female);
        addressEt = (EditText) findViewById(R.id.et_resume_address);
        majorEt = (EditText) findViewById(R.id.et_resume_major);

        ImageView back = (ImageView) findViewById(R.id.iv_title_back_resume_edit_info);
        Button save = (Button) findViewById(R.id.btn_resume_info_save);

        back.setOnClickListener(view -> finish());
        save.setOnClickListener(view -> {

        });
        maleBtn.setOnClickListener(view -> {
            maleBtn.setBackgroundResource(R.color.gender_choose);
            femaleBtn.setBackgroundResource(R.color.gender_normal);
            gender = MALE;
        });
        femaleBtn.setOnClickListener(view -> {
            maleBtn.setBackgroundResource(R.color.gender_normal);
            femaleBtn.setBackgroundResource(R.color.gender_choose);
            gender = FEMALE;
        });

        nameEt.setText(getIntent().getStringExtra(NAME));
        nameEt.setSelection(getIntent().getStringExtra(NAME).length());
        addressEt.setText(getIntent().getStringExtra(ADDRESS));
        majorEt.setText(getIntent().getStringExtra(MAJOR));

        if (getIntent().getIntExtra(GENDER, MALE) == MALE) {
            maleBtn.callOnClick();
        } else {
            femaleBtn.callOnClick();
        }
    }

    public static void startActivity(Context context, String name, int gender, String address, String major) {
        Intent intent = new Intent(context, ResumeEditInfoActivity.class);
        intent.putExtra(NAME, name);
        intent.putExtra(GENDER, gender);
        intent.putExtra(ADDRESS, address);
        intent.putExtra(MAJOR, major);
        context.startActivity(intent);
    }
}
