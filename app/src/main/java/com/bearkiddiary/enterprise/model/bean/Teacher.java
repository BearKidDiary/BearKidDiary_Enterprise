package com.bearkiddiary.enterprise.model.bean;

public class Teacher {
    public static final String NAME = "Tname";
    public static final String SEX = "Tsex";
    public static final String PHONE = "Tphone";
    public static final String AREA = "Tarea";
    public static final String PASSWORD = "Tpsw";
    public static final String AVATAR = "Tavatar";
    public static final String WORKEXPERIENCE = "Tworkexperience";
    public static final String SPECIALTY = "Tspecialty";
    public static final String EDUEXPERIENCE = "Teduexperience";
    /**
     * 教师名称
     */
    private String Tname;
    /**
     * 教师性别
     */
    private String Tsex;
    /**
     * 教师手机号码
     */
    private String Tphone;
    /**
     * 所在地区
     */
    private String Tarea;
    /**
     * 登陆密码
     */
    private String Tpsw;
    /**
     * 教师头像
     */
    private String Tavatar;
    /**
     * 工作经验
     */
    private String Tworkexperience;
    /**
     * 教师特长
     */
    private String Tspecialty;
    /**
     * 教师教育经验
     */
    private String Teduexperience;

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTsex() {
        return Tsex;
    }

    public void setTsex(String tsex) {
        Tsex = tsex;
    }

    public String getTphone() {
        return Tphone;
    }

    public void setTphone(String tphone) {
        Tphone = tphone;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    public String getTpsw() {
        return Tpsw;
    }

    public void setTpsw(String tpsw) {
        Tpsw = tpsw;
    }

    public String getTavatar() {
        return Tavatar;
    }

    public void setTavatar(String tavatar) {
        Tavatar = tavatar;
    }

    public String getTworkexperience() {
        return Tworkexperience;
    }

    public void setTworkexperience(String tworkexperience) {
        Tworkexperience = tworkexperience;
    }

    public String getTspecialty() {
        return Tspecialty;
    }

    public void setTspecialty(String tspecialty) {
        Tspecialty = tspecialty;
    }

    public String getTeduexperience() {
        return Teduexperience;
    }

    public void setTeduexperience(String teduexperience) {
        Teduexperience = teduexperience;
    }
}