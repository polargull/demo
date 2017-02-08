package com.demo.util;

/**
 * Created by fuwei on 2017/2/8.
 */
public class TestDataCreater {
    public static int[] createRandomTestIntArray(int num) {
        int[] test = new int[num];
        for (int i = 0; i < num; i++) {
            test[i] = RandomUtil.random(1, num);
        }
        return test;
    }

    public static void main(String[] args) {
        int test[] = createRandomTestIntArray(10000);
        for (int i = 1; i < test.length + 1; i++) {
            System.out.print(test[i - 1] + " ");
            if (i % 10 == 0)
                System.out.println("\n");

        }
    }
}