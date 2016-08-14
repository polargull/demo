package com.ssm.entity;

public class SubOrder {
    long id;
    long productId;
    int nums;
    long orderId;
    Order order;

    public SubOrder() {
    }

    public SubOrder(long productId, int nums, long orderId) {
        super();
        this.productId = productId;
        this.nums = nums;
        this.orderId = orderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

}