import com.demo.annotation.RecordTime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fuwei on 2017/2/4.
 */
public class SortTest {

    public static void main(String[] args) {
        System.out.println("hello world!");
        int[] intsort = new int[]{3, 1, 6, 3, 5, 8, 0};
        out(intsort);
        try {
            Method bubbleSort = SortTest.class.getMethod("bubbleSort", int[].class);
            RecordTime annotation = bubbleSort.getAnnotation(RecordTime.class);
            System.out.println(annotation);
            bubbleSort.invoke(new SortTest(), intsort);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        out(intsort);
    }

    @RecordTime
    public static void bubbleSort(int[] intsort) {
        for (int i = 0; i < intsort.length; i++) {
            for (int j = i + 1; j < intsort.length; j++) {
                if (intsort[i] < intsort[j]) {
                    int temp = intsort[i];
                    intsort[i] = intsort[j];
                    intsort[j] = temp;
                }
            }
        }
    }

    public static void out(int[] intsort) {
        for (int i : intsort)
            System.out.print(i + " ");
        System.out.print("\n");
    }
}