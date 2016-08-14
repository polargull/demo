package com.ssm.dto.order;

public class OrderParam {
    long uid;
    long pid[];
    int nums[];

    public OrderParam(long uid, long[] pid, int[] nums) {
        super();
        this.uid = uid;
        this.pid = pid;
        this.nums = nums;
    }

    public OrderParam() {
    }

    public long[] getPid() {
        return pid;
    }

    public void setPid(long[] pid) {
        this.pid = pid;
    }

    public int[] getNums() {
        return nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

}
