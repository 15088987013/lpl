package com.lplsystem.pojo;

import java.util.Date;

public class Couch {
    private int cid;
    private String cname;
    private String csex;
    private String cjob;
    private Date birthdate;
    public String phone;
    private String cdesc;
    public int tid;
    private Team team;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCsex() {
        return csex;
    }

    public void setCsex(String csex) {
        this.csex = csex;
    }

    public String getCjob() {
        return cjob;
    }

    public void setCjob(String cjob) {
        this.cjob = cjob;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Couch(int cid, String cname, String csex, String cjob, Date birthdate,  String phone, String cdesc,int tid) {
        this.cid = cid;
        this.cname = cname;
        this.csex = csex;
        this.cjob = cjob;
        this.birthdate = birthdate;
        this.cdesc = cdesc;
        this.phone = phone;
        this.tid = tid;
    }

    public Couch(int cid, String cname, String csex, String cjob, Date birthdate, String phone, String cdesc, int tid, Team team) {
        this.cid = cid;
        this.cname = cname;
        this.csex = csex;
        this.cjob = cjob;
        this.birthdate = birthdate;
        this.phone = phone;
        this.cdesc = cdesc;
        this.tid = tid;
        this.team = team;
    }

    @Override
    public String toString() {
        return "Couch{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", csex='" + csex + '\'' +
                ", cjob='" + cjob + '\'' +
                ", birthdate=" + birthdate +
                ", phone='" + phone + '\'' +
                ", cdesc='" + cdesc + '\'' +
                ", tid=" + tid +
                ", team=" + team +
                '}';
    }
}
