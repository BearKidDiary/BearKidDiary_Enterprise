package com.bearkiddiary.enterprise.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.utils.DateTimePickerUtil;

/**
 * Created by YarenChoi on 2016/8/1.
 * 请假界面
 */

public class AskForLeaveActivity extends BaseActivity implements View.OnClickListener{
    private static final String TOAST_ERROR = "您选择的日期有误，请重新选择";
    private static final String TYPE_SICK = "病假";
    private static final String TYPE_AFFAIRS = "事假";
    private static final String TYPE_ELSE = "其他";

    protected EditText et_reason;
    protected ImageView iv_add;

    protected TextView tv_type;
    protected TextView tv_start_time;
    protected TextView tv_end_time;
    protected TextView tv_day;

    protected static boolean conflict;//日期选择是否正确

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_leave);
        initView();
    }

    private void initView() {
        tv_type = (TextView) findViewById(R.id.tv_leave_type);
        tv_start_time = (TextView) findViewById(R.id.tv_leave_start_time);
        tv_end_time = (TextView) findViewById(R.id.tv_leave_end_time);
        tv_day = (TextView) findViewById(R.id.tv_leave_day);
        et_reason = (EditText) findViewById(R.id.et_leave_reason);
        iv_add = (ImageView) findViewById(R.id.iv_leave_add_approver);

        findViewById(R.id.ll_leave_type).setOnClickListener(this);
        findViewById(R.id.ll_leave_start_time).setOnClickListener(this);
        findViewById(R.id.ll_leave_end_time).setOnClickListener(this);
        findViewById(R.id.btn_leave_submit).setOnClickListener(this);
        iv_add.setOnClickListener(this);

        conflict = false;
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, AskForLeaveActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_leave_type://请假类型
                showDialog();
                break;
            case R.id.ll_leave_start_time://请假开始时间
                DateTimePickerUtil.showDatePicker(AskForLeaveActivity.this, date -> {
                    tv_start_time.setText(date);
                    setDayCount();
                });
                break;
            case R.id.ll_leave_end_time://请假结束时间
                DateTimePickerUtil.showDatePicker(AskForLeaveActivity.this, date -> {
                    tv_end_time.setText(date);
                    setDayCount();
                });
                break;
            case R.id.iv_leave_add_approver://添加审批人
                break;
            case R.id.btn_leave_submit://提交
                if (!conflict) {//提交操作

                } else {
                    showToast(TOAST_ERROR);
                }
                break;
            default:
                break;
        }
    }

    private void setDayCount() {
        String startTime = tv_start_time.getText().toString();
        String endTime = tv_end_time.getText().toString();
        if (!TextUtils.isEmpty(startTime)
                && !TextUtils.isEmpty(endTime)) {
            long dayCount = DateTimePickerUtil.getComparedDayCount(startTime, endTime);
            analyseDayCount(dayCount);
            String result = dayCount + "天";
            tv_day.setText(result);
        }
    }

    private void analyseDayCount(long dayCount) {
        if (dayCount <= 0) {
            conflict = true;
            tv_day.setTextColor(getResources().getColor(R.color.warning));
        } else {
            conflict = false;
            tv_day.setTextColor(getResources().getColor(R.color.text_gray));
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("请选择请假类型")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setSingleChoiceItems(new String[]{TYPE_SICK, TYPE_AFFAIRS, TYPE_ELSE}, 0, (dialog, which) -> {
                    switch (which) {
                        case 0://病假
                            tv_type.setText(TYPE_SICK);
                            break;
                        case 1://事假
                            tv_type.setText(TYPE_AFFAIRS);
                            break;
                        case 2://其他
                            tv_type.setText(TYPE_ELSE);
                            break;
                    }
                    dialog.dismiss();
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
