package com.demo.util;

/**
 * Created by fuwei on 2017/2/4.
 */
public class RandomUtil {
    /*
    公式：(上界-下界+1)*rnd+下界
     */
    public static int random(int beginRang, int endRang) {
        return (int) ((beginRang - endRang + 1) * Math.random() + endRang);
    }
}
