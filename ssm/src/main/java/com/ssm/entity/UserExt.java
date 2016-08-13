package com.ssm.entity;

import java.util.Date;

public class UserExt {
    long id;
    String accountNo;
    Date birthDate;

    public UserExt() {
    }

    public UserExt(long id, String accountNo, Date birthDate) {
        super();
        this.id = id;
        this.accountNo = accountNo;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}