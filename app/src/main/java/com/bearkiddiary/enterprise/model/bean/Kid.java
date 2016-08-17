package com.bearkiddiary.enterprise.model.bean;

public class Kid implements Comparable {
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
    private String pingyin;


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

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }

    @Override
    public int compareTo(Object o) {
        if (pingyin.equals("@")
                || ((Kid)o).getPingyin().equals("#")) {
            return -1;
        } else if (pingyin.equals("#")
                || ((Kid)o).getPingyin().equals("@")) {
            return 1;
        }else {
            return pingyin.compareToIgnoreCase(((Kid)o).getPingyin());
        }

    }
}