package com.lplsystem.pojo;

import java.util.Date;

public class Player {
    private int pid;
    private String pname;
    private String position;
    private String hobby;
    private Date birthdate;
    private String pdesc;
    public String phone;
    public int tid;
    private Team team;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
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

    public Player() {
    }

    public Player(int pid, String pname, String position, String hobby, Date birthdate,String phone, String pdesc,  int tid) {
        this.pid = pid;
        this.pname = pname;
        this.position = position;
        this.hobby = hobby;
        this.birthdate = birthdate;
        this.pdesc = pdesc;
        this.phone = phone;
        this.tid = tid;
    }

    public Player(int pid, String pname, String position, String hobby, Date birthdate, String pdesc, String phone, int tid, Team team) {
        this.pid = pid;
        this.pname = pname;
        this.position = position;
        this.hobby = hobby;
        this.birthdate = birthdate;
        this.pdesc = pdesc;
        this.phone = phone;
        this.tid = tid;
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", position='" + position + '\'' +
                ", hobby='" + hobby + '\'' +
                ", birthdate=" + birthdate +
                ", pdesc='" + pdesc + '\'' +
                ", phone='" + phone + '\'' +
                ", tid=" + tid +
                '}';
    }
}
