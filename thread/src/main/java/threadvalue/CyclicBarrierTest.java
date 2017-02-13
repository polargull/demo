package threadvalue;

import com.demo.util.TestDataCreater;
import lombok.extern.slf4j.Slf4j;
import sort.SortTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.System.currentTimeMillis;
import static sort.SortTest.testMethod;

/**
 * Created by fuwei on 2017/2/13.
 */
@Slf4j
public class CyclicBarrierTest {
    static AtomicLong totalMax = new AtomicLong();
    static long startTime;

    /**
     * -Xmn16m -Xms64m -Xmx2014m
     * @param args
     * @throws BrokenBarrierException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        SortTest.printOnOff = false;
        SortTest.outOnOff = false;
        int[] intsort1 = TestDataCreater.createRandomTestIntArray(10000 * 10000);
        int[] intsort2 = TestDataCreater.createRandomTestIntArray(10000 * 9999);
        int[] intsort3 = TestDataCreater.createRandomTestIntArray(10000 * 9998);
        int[] intsort4 = TestDataCreater.createRandomTestIntArray(10000 * 9990);

        CyclicBarrier barrier = new CyclicBarrier(4 + 1);
        startTime = currentTimeMillis();
        new Thread(new Calc(barrier, "sort1", intsort1)).start();
        new Thread(new Calc(barrier, "sort2", intsort2)).start();
        new Thread(new Calc(barrier, "sort3", intsort3)).start();
        new Thread(new Calc(barrier, "sort4", intsort4)).start();
        barrier.await();
        log.info("总耗时:" + (currentTimeMillis() - startTime) / 1000 + "s");
        log.info("总大小:" + totalMax.get());
    }

    static class Calc implements Runnable {
        CyclicBarrier barrier;
        String name;
        int[] intsort;

        public Calc(CyclicBarrier barrier, String name, int[] intsort) {
            this.barrier = barrier;
            this.name = name;
            this.intsort = intsort;
        }

        public void run() {
            long max = 0;
            try {
                testMethod("javaApiSort", intsort);
                max = intsort[intsort.length - 1];
                log.info("name:" + name + " compute complete, max:" + max + ". await...");
            } catch (Exception e) {
                e.printStackTrace();
            }
            await();
            totalMax.addAndGet(max);
        }

        private void await() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
