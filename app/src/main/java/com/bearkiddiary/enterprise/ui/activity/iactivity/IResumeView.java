package com.bearkiddiary.enterprise.ui.activity.iactivity;

/**
 * Created by admin on 2016/7/25.
 */
public interface IResumeView extends IBaseView {
    void setAvatar(int imgResource);

    void setName(String name);

    void setMajor(String major);

    void setAddress(String address);

    void setEmail(String email);

    void setPhoneNum(String phoneNum);

    void setQQ(String qq);

    void showWorkExperience();

    void showEduExperience();

    void setSpecialties(String specialties);

}
