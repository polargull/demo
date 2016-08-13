package com.ssm.entity;

public class User {
    long id;
    String name;
    String pwd;
    UserExt userExt;

    public User() {
    }

    public User(String name, String pwd) {
        super();
        this.name = name;
        this.pwd = pwd;
    }
    
    public UserExt getUserExt() {
        return userExt;
    }

    public void setUserExt(UserExt userExt) {
        this.userExt = userExt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}