package com.lplsystem.pojo;

public class User {
    private int uid;
    private String uname;
    private String pwd;
    private String realname;
    private int identity;

    public int getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRealname() {
        return realname;
    }

    public int getIdentity() {
        return identity;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public User() {
    }

    public User(int uid, String uname,  String pwd,String realname, int identity) {
        this.uid = uid;
        this.realname = realname;
        this.identity = identity;
        this.uname = uname;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", realname='" + realname + '\'' +
                ", identity=" + identity +
                '}';
    }
}
