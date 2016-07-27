package com.bearkiddiary.enterprise.ui.activity.iactivity;

/**
 * Created by admin on 2016/7/25.
 */
public interface IResumeView extends IBaseView {
    void setAvatar(int imgResource);

    void setName(String name);

    void setGender(int imgResource);

    void setMajor(String major);

    void setAddress(String address);

    void setEmail(String email);

    void setPhoneNum(String phoneNum);

    void setQQ(String qq);

    void showWorkExperience(String startDate, String endDate, String job, String company, String desc);

    void showEduExperience(String startDate, String endDate, String major, String school);

    void setSpecialties(String specialties);

}
