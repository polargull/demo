package threadvalue;

import com.demo.util.TestDataCreater;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static sort.SortTest.testMethod;

/**
 * Created by fuwei on 2017/2/13.
 */
@Slf4j
public class CountDownLatchTest {
    static AtomicLong totalMax = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        int[] intsort1 = TestDataCreater.createRandomTestIntArray(10000 * 10000);
        int[] intsort2 = TestDataCreater.createRandomTestIntArray(10000 * 9999);
        int[] intsort3 = TestDataCreater.createRandomTestIntArray(10000 * 9998);
        int[] intsort4 = TestDataCreater.createRandomTestIntArray(10000 * 9990);
        long startTime = System.currentTimeMillis();
        new Thread(new Calc(latch, "name1", intsort1)).start();
        new Thread(new Calc(latch, "name2", intsort2)).start();
        new Thread(new Calc(latch, "name3", intsort3)).start();
        new Thread(new Calc(latch, "name4", intsort4)).start();
        latch.await();
        log.info("总耗时" + (System.currentTimeMillis() - startTime) / 1000);
        log.info("总大小:" + totalMax.get());
    }

    static class Calc implements Runnable {
        CountDownLatch countDownLatch;
        String name;
        int[] intsort;

        public Calc(CountDownLatch countDownLatch, String name, int[] intsort) {
            this.countDownLatch = countDownLatch;
            this.name = name;
            this.intsort = intsort;
        }

        public void run() {
            long max = 0;
            try {
                testMethod("javaApiSort", intsort);
                max = intsort[intsort.length - 1];
                log.info("name:" + name + " compute complete, max:" + max + ". await...");
                totalMax.addAndGet(max);
            } catch (Exception e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }
}
