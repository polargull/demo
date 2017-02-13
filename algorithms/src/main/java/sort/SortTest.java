package sort;

import com.demo.annotation.RecordTime;
import com.demo.util.TestDataCreater;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by fuwei on 2017/2/4.
 */
@Slf4j
public class SortTest {
    public static boolean printOnOff = false;
    public static boolean outOnOff = false;

    public static void main(String[] args) {
        int[] intsort1 = TestDataCreater.createRandomTestIntArray(10000 * 10000);
        int[] intsort2 = TestDataCreater.createRandomTestIntArray(10000 * 10000);
        int[] intsort3 = TestDataCreater.createRandomTestIntArray(10000 * 10000);
        long startTime = System.currentTimeMillis();
        testMethod("javaApiSort", intsort1);
        testMethod("javaApiSort", intsort2);
        testMethod("javaApiSort", intsort3);
        log.info("总耗时:" + (System.currentTimeMillis() - startTime));
//        testMethod("bubbleSort", intsort);
//        testMethod("xuanzeSort", intsort);
    }

    public static void testMethod(String method, int[] intsort) {
        log(intsort);
        try {
            Method bubbleSort = SortTest.class.getMethod(method, int[].class);
            RecordTime annotation = bubbleSort.getAnnotation(RecordTime.class);
            long startTime = System.currentTimeMillis();
            bubbleSort.invoke(new SortTest(), intsort);
            if (annotation != null) {
                log.info(">>>>>> cost time:" + ((System.currentTimeMillis() - startTime)) / 1000);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        log(intsort);
    }

    private static void log(int[] intsort) {
        if (!outOnOff) {
            return;
        }
        log.info(out(intsort));
    }

    @RecordTime
    public static void xuanzeSort(int[] intsort) {
        for (int i = 0; i < intsort.length; i++) {
            for (int j = i + 1; j < intsort.length; j++) {
                if (intsort[i] > intsort[j]) {
                    int temp = intsort[i];
                    intsort[i] = intsort[j];
                    intsort[j] = temp;
                }
                if (printOnOff) out(intsort);
            }
        }
    }

    /**
     * 从数组第一个元素开始,依次比较元素,若左值大于右值交换位置,否则不交换,然后右边值继续和后面的元素比较,重复这个步骤,直到比较完所有元素。
     *
     * @param intsort
     */
    @RecordTime
    public static void bubbleSort(int[] intsort) {
        for (int i = intsort.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (intsort[j] > intsort[j + 1]) {
                    int temp = intsort[j];
                    intsort[j] = intsort[j + 1];
                    intsort[j + 1] = temp;
                }
                if (printOnOff) out(intsort);
            }
        }
    }

    /**
     * i5-5300U 2.3G 2.29G 大约1s 1000万
     *
     * @param intsort
     */
    @RecordTime
    public static void javaApiSort(int[] intsort) {
        Arrays.sort(intsort);
    }

    public static String out(int[] intsort) {
        StringBuilder sb = new StringBuilder("array:");
        for (int i = 0; i < intsort.length; i++) {
            sb.append(intsort[i]).append(" ");
            if (i == 1000) {
                sb.append("...");
                break;
            }
        }
        return sb.toString();
    }
}