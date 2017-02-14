package sort;

import java.util.*;

/**
 * Created by fuwei on 2017/2/14.
 */
public class ReverseSortTest {
    public static void main(String[] args) {
        //China. from come I
        String str = "I come from China.";
        System.out.println(str);
        method1(str);
        method2(str);
    }

    private static void method1(String str) {
        System.out.println("method1");
        StringBuilder sb = new StringBuilder(str);
        String[] tmp = sb.reverse().toString().split(" ");
        for (String word : tmp) {
            String w = "";
            for (int i = word.length() - 1; i >= 0; i--) {
                w += word.charAt(i);
            }
            System.out.print(w + " ");
        }
        System.out.println("\n");
    }

    private static void method2(String str) {
        System.out.println("method2");
        String[] tmp = str.split(" ");
        Stack<String> stack = new Stack<String>();
        for (String word : tmp) {
            stack.push(word);
        }
        for (int i = 0; i < tmp.length; i++) {
            System.out.print(stack.pop() + " ");
        }
    }
}
