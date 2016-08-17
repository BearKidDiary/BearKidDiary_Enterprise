package com.bearkiddiary.enterprise.model.bean;

/**
 * Created by YarenChoi on 2016/8/17.
 * 联系人（员工、学生）
 */
public class Contact implements Comparable {
    private String name;
    private String pingyin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                || ((Contact) o).getPingyin().equals("#")) {
            return -1;
        } else if (pingyin.equals("#")
                || ((Contact) o).getPingyin().equals("@")) {
            return 1;
        } else {
            return pingyin.compareToIgnoreCase(((Contact) o).getPingyin());
        }

    }
}
