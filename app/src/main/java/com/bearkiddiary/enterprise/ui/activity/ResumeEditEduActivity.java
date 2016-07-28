package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.bearkiddiary.enterprise.R;

public class ResumeEditEduActivity extends BaseActivity {
    private static final int EDITEXP = 0;//编辑操作
    private static final int ADDEXP = 1;//新增操作
    private static final String OPTYPE = "type";
    private static final String STARTDATE = "startDate";
    private static final String ENDDATE = "endDate";
    private static final String MAJOR = "major";
    private static final String SCHOOL = "school";
    private static final String DESC = "desc";

    protected EditText et_school;
    protected EditText et_major;
    protected EditText et_desc;

    protected int opType;//判断是“新增”还是“编辑”操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_edit_edu);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_title_back_resume_edit_edu).setOnClickListener(view -> finish());

        et_school = (EditText) findViewById(R.id.et_resume_school);
        et_major = (EditText) findViewById(R.id.et_resume_edu_major);
        et_desc = (EditText) findViewById(R.id.et_resume_edu_desc);

        opType = getIntent().getIntExtra(OPTYPE, ADDEXP);
        if (opType == ADDEXP) {

        } else if (opType == EDITEXP) {
            et_school.setText(getIntent().getStringExtra(SCHOOL));
            et_major.setText(getIntent().getStringExtra(MAJOR));
            et_desc.setText(getIntent().getStringExtra(DESC));
        }
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ResumeEditEduActivity.class);
        intent.putExtra(OPTYPE, ADDEXP);
        context.startActivity(intent);
    }

    public static void startActivity(Context context,
                                     String startDate,
                                     String endDate,
                                     String major,
                                     String school,
                                     String desc){
        Intent intent = new Intent(context, ResumeEditEduActivity.class);
        intent.putExtra(OPTYPE, EDITEXP);
        intent.putExtra(STARTDATE, startDate);
        intent.putExtra(ENDDATE, endDate);
        intent.putExtra(MAJOR, major);
        intent.putExtra(SCHOOL, school);
        intent.putExtra(DESC, desc);
        context.startActivity(intent);
    }
}
