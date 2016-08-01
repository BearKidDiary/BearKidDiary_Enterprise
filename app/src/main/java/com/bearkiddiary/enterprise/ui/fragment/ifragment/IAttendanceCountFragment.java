package com.bearkiddiary.enterprise.ui.fragment.ifragment;


/**
 * Created by YarenChoi on 2016/8/1.
 *
 */
public interface IAttendanceCountFragment extends IBaseFragment {
    void showCalendar();

    void setCount(int normal, int absence, int leave, int late, int absenteeism);
}
