package com.bearkiddiary.enterprise.model.bean;


/**
 * 课程信息
 */
public class Course {
    /**
     * 开课时间
     */
    private long Cclasstime;
    /**
     * 结束时间
     */
    private long Cendtime;
    /**
     * 开学时间
     */
    private long Ctime;
    /**
     * 毕业时间
     */
    private long Cofftime;
    /**
     * 开课审批人
     */
    private Teacher Capprover;
    /**
     * 课程的开课背景、原因
     */
    private String Cbackground;
    /**
     * 课程描述
     */
    private String Cdesc;
    /**
     * 课程名称
     */
    private String Cname;
    /**
     * 周一到周日是否需要上课
     */
    private Boolean Cmonday, Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday;

    public long getCclasstime() {
        return Cclasstime;
    }

    public void setCclasstime(long cclasstime) {
        Cclasstime = cclasstime;
    }

    public long getCendtime() {
        return Cendtime;
    }

    public void setCendtime(long cendtime) {
        Cendtime = cendtime;
    }

    public long getCtime() {
        return Ctime;
    }

    public void setCtime(long ctime) {
        Ctime = ctime;
    }

    public long getCofftime() {
        return Cofftime;
    }

    public void setCofftime(long cofftime) {
        Cofftime = cofftime;
    }

    public Teacher getCapprover() {
        return Capprover;
    }

    public void setCapprover(Teacher capprover) {
        Capprover = capprover;
    }

    public String getCbackground() {
        return Cbackground;
    }

    public void setCbackground(String cbackground) {
        Cbackground = cbackground;
    }

    public String getCdesc() {
        return Cdesc;
    }

    public void setCdesc(String cdesc) {
        Cdesc = cdesc;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Boolean getCmonday() {
        return Cmonday;
    }

    public void setCmonday(Boolean cmonday) {
        Cmonday = cmonday;
    }

    public Boolean getCtuesday() {
        return Ctuesday;
    }

    public void setCtuesday(Boolean ctuesday) {
        Ctuesday = ctuesday;
    }

    public Boolean getCwednesday() {
        return Cwednesday;
    }

    public void setCwednesday(Boolean cwednesday) {
        Cwednesday = cwednesday;
    }

    public Boolean getCthursday() {
        return Cthursday;
    }

    public void setCthursday(Boolean cthursday) {
        Cthursday = cthursday;
    }

    public Boolean getCfriday() {
        return Cfriday;
    }

    public void setCfriday(Boolean cfriday) {
        Cfriday = cfriday;
    }

    public Boolean getCsaturday() {
        return Csaturday;
    }

    public void setCsaturday(Boolean csaturday) {
        Csaturday = csaturday;
    }
}
