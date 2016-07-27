package com.bearkiddiary.enterprise.ui.activity.iactivity;

import android.graphics.Bitmap;

import com.bearkiddiary.enterprise.model.bean.Course;
import com.bearkiddiary.enterprise.model.bean.Kid;
import com.bearkiddiary.enterprise.model.bean.Teacher;

import java.util.List;

public interface ICourseView extends IBaseView {
    void setTeacher(Teacher teacher);

    Teacher getTeacher();

    void setCourse(Course course);

    Course getCourse();

    void updateKidList(List<Kid> list);

    void setKidList(List<Kid> list);

    void updateKid(Kid kid);

    void notifyChanged();
}
