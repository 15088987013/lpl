package com.lplsystem.pojo;

public class Team {
    private int tid;
    private String tname;
    private String tlocation;
    private String desc;

    public int getTid() {
        return tid;
    }
    public void setTid(int tid) {
        this.tid = tid;
    }
    public String getTname() {
        return tname;
    }
    public void setTname(String tname) {
        this.tname = tname;
    }
    public String getTlocation() {
        return tlocation;
    }
    public void setTlocation(String tlocation) {
        this.tlocation = tlocation;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Team() {
    }
    public Team(String tname, String tlocation, String desc) {
        this.tname = tname;
        this.tlocation = tlocation;
        this.desc = desc;
    }
    public Team(int tid, String tname, String tlocation, String desc) {
        this.tid = tid;
        this.tname = tname;
        this.tlocation = tlocation;
        this.desc = desc;
    }
    @Override
    public String toString() {
        return "Team{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tlocation='" + tlocation + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
