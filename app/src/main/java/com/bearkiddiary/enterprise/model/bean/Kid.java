package com.bearkiddiary.enterprise.model.bean;

public class Kid {
    public static final String NAME = "Kname";
    public static final String SEX = "Ksex";
    public static final String BIRTHDAY = "Kbirthday";
    public static final String ASK = "Kask";
    public static final String FAMILY = "family";
    public static final String AVATAR = "Kavatar";

    private String Kname;
    private String Ksex;
    private long Kbirthday;
    private String Kask;
    //private Family family;
    private String Kavatar;
    //private BmobRelation Ktimeline;


    public String getKname() {
        return Kname;
    }

    public void setKname(String kname) {
        Kname = kname;
    }

    public String getKsex() {
        return Ksex;
    }

    public void setKsex(String ksex) {
        Ksex = ksex;
    }

    public long getKbirthday() {
        return Kbirthday;
    }

    public void setKbirthday(long kbirthday) {
        Kbirthday = kbirthday;
    }

    public String getKask() {
        return Kask;
    }

    public void setKask(String kask) {
        Kask = kask;
    }

    public String getKavatar() {
        return Kavatar;
    }

    public void setKavatar(String kavatar) {
        Kavatar = kavatar;
    }
}