package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.utils.DateTimePickerUtil;

public class ResumeEditJobActivity extends BaseActivity {
    private static final int EDITEXP = 0;//编辑操作
    private static final int ADDEXP = 1;//新增操作
    private static final String OPTYPE = "type";
    private static final String STARTDATE = "startDate";
    private static final String ENDDATE = "endDate";
    private static final String JOB = "job";
    private static final String COMPANY = "company";
    private static final String DESC = "desc";

    protected EditText et_company;
    protected EditText et_job_name;
    protected EditText et_desc;

    protected TextView tv_start_date;
    protected TextView tv_end_date;

    protected int opType;//判断是“新增”还是“编辑”操作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_edit_job);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_title_back_resume_edit_job).setOnClickListener(view -> finish());

        tv_start_date = (TextView) this.findViewById(R.id.tv_resume_job_start_date);
        tv_end_date = (TextView) this.findViewById(R.id.tv_resume_job_end_date);

        tv_start_date.setOnClickListener(view ->
                DateTimePickerUtil.showMonthPicker(
                        ResumeEditJobActivity.this,
                        monthOfYear -> tv_start_date.setText(monthOfYear)));
        tv_end_date.setOnClickListener(view ->
                DateTimePickerUtil.showMonthPicker(
                        ResumeEditJobActivity.this,
                        monthOfYear -> tv_end_date.setText(monthOfYear)));

        et_company = (EditText) findViewById(R.id.et_resume_company);
        et_job_name = (EditText) findViewById(R.id.et_resume_job_name);
        et_desc = (EditText) findViewById(R.id.et_resume_desc);

        opType = getIntent().getIntExtra(OPTYPE, ADDEXP);
        if (opType == ADDEXP) {

        } else if (opType == EDITEXP){
            et_company.setText(getIntent().getStringExtra(COMPANY));
            et_job_name.setText(getIntent().getStringExtra(JOB));
            et_desc.setText(getIntent().getStringExtra(DESC));
        }
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ResumeEditJobActivity.class);
        intent.putExtra(OPTYPE, ADDEXP);
        context.startActivity(intent);
    }

    public static void startActivity(Context context,
                                     String startDate,
                                     String endDate,
                                     String job,
                                     String company,
                                     String desc){
        Intent intent = new Intent(context, ResumeEditJobActivity.class);
        intent.putExtra(OPTYPE, EDITEXP);
        intent.putExtra(STARTDATE, startDate);
        intent.putExtra(ENDDATE, endDate);
        intent.putExtra(JOB, job);
        intent.putExtra(COMPANY, company);
        intent.putExtra(DESC, desc);
        context.startActivity(intent);
    }
}
