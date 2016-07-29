package com.bearkiddiary.enterprise.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.bearkiddiary.enterprise.R;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by yarenChoi on 2016/7/29.
 * 日期选择器、时间选择器工具类
 *
 */
public class DateTimePickerUtil {
    public static final String TAG = "DateTimePickerUtil";

    public static DecimalFormat decimalFormat = new DecimalFormat("00");

    /**
     *
     * @param context The context the dialog is to run in.
     * @param onDateSetListener How the parent is notified that the date is set.
     */
    public static void showDatePicker(Context context, OnDateSetListener onDateSetListener) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, year, monthOfYear, dayOfMonth) ->
                        onDateSetListener.onDateSet(getFormatDate(year, monthOfYear, dayOfMonth)),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public static void showMonthPicker(Context context, OnMonthSetListener onMonthSetListener) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                R.style.AppTheme,
                (view, year, monthOfYear, dayOfMonth) ->
                        onMonthSetListener.onMonthSet(getFormatDate(year, monthOfYear)),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker datePicker = datePickerDialog.getDatePicker();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
            if (daySpinnerId != 0) {
                Log.e(TAG, "daySpinnerId != 0");
                View daySpinner = datePicker.findViewById(daySpinnerId);
                if (daySpinner != null) {
                    Log.e(TAG, "daySpinner != null");
                    daySpinner.setVisibility(View.GONE);
                }
            }
        } else {
            ((ViewGroup) ((ViewGroup) datePickerDialog.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        }

        datePickerDialog.show();
    }

    /**
     *
     * @param context The context the dialog is to run in.
     * @param onTimeSetListener How the parent is notified that the time is set.
     */
    public static void showTimePicker(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                context,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false);
        timePickerDialog.show();
    }

    /**
     *
     * @param year 年份
     * @param monthOfYear 月份（需+1）
     * @param dayOfMonth 日期
     * @return 格式化后的日期字符串
     */
    public static String getFormatDate(int year, int monthOfYear, int dayOfMonth) {
        return year + "-" + decimalFormat.format(monthOfYear + 1) + "-" + decimalFormat.format(dayOfMonth);
    }

    /**
     *
     * @param year 年份
     * @param monthOfYear 月份（需+1）
     * @return 格式化后的年/月字符串
     */
    public static String getFormatDate(int year, int monthOfYear) {
        return year + "-" + decimalFormat.format(monthOfYear + 1);
    }

    /**
     *
     * @param hour 小时
     * @param minute 分钟
     * @return 格式化后的时/分
     */
    public static String getFormatTime(int hour, int minute) {
        return decimalFormat.format(hour) + ":" + decimalFormat.format(minute);
    }

    public interface OnDateSetListener {
        void onDateSet(String date);
    }

    public interface OnMonthSetListener {
        void onMonthSet(String monthOfYear);
    }

    public interface OnTimeSetListener {
        void onTimeSet(String time);
    }

}
