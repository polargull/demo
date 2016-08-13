package com.ssm.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    long id;
    long uid;
    User user;
    double totalPrice;
    List<SubOrder> subLst = new ArrayList<SubOrder>();

    public Order() {
    }

    public Order(long id, User user, double totalPrice) {
        super();
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SubOrder> getSubLst() {
        return subLst;
    }

    public void setSubLst(List<SubOrder> subLst) {
        this.subLst = subLst;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

}