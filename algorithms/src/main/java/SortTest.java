import com.demo.annotation.RecordTime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fuwei on 2017/2/4.
 */
public class SortTest {
    public static void main(String[] args) {
        testMethod("bubbleSort", new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0});
        testMethod("xuanzeSort", new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0});
    }

    private static void testMethod(String method, int[] intsort) {
        System.out.println(">>>>>> " + method);
        System.out.print("src array:");
        out(intsort);
        try {
            Method bubbleSort = SortTest.class.getMethod(method, int[].class);
            RecordTime annotation = bubbleSort.getAnnotation(RecordTime.class);
            long startTime = System.currentTimeMillis();
            bubbleSort.invoke(new SortTest(), intsort);
            if (annotation != null) {
                System.out.println("cost time:" + (System.currentTimeMillis() - startTime));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.print("sorted array:");
        out(intsort);
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
                out(intsort);
            }
        }
    }

    @RecordTime
    public static void bubbleSort(int[] intsort) {
        for (int i = intsort.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (intsort[j] > intsort[j + 1]) {
                    int temp = intsort[j];
                    intsort[j] = intsort[j + 1];
                    intsort[j + 1] = temp;
                }
                out(intsort);
            }
        }
    }

    public static void out(int[] intsort) {
        for (int i : intsort)
            System.out.print(i + " ");
        System.out.print("\n");
    }
}