package threadvalue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by fuwei on 2017/2/14.
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            final int ON = i;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("Accesss:" + ON);
                        Thread.sleep((long) (Math.random() * 10000));
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(runnable);
        }
    }
}
