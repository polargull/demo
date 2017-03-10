package threadvalue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by fuwei on 2017/3/10.
 */
public class DelayQTest {
    static DelayQueue<Task> queue = new DelayQueue<>();

    public static void main(String[] args) {
        queue.put(new Task(TimeUnit.NANOSECONDS.convert(5*1000, TimeUnit.MILLISECONDS), "5s"));
        queue.put(new Task(TimeUnit.NANOSECONDS.convert(10*1000, TimeUnit.MILLISECONDS), "10s"));
        new Thread(() -> {
            long start = System.currentTimeMillis();
            while (true) {
                try {
                    Task take = queue.take();
                    System.out.println(">>> cost time:" + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start));
                    System.out.println(">>> Task time:" + take.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Task implements Delayed {
        private long timeoutSec;
        private String name;

        public Task(long timeoutSec, String name) {
            this.timeoutSec = System.nanoTime() + timeoutSec;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.timeoutSec - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
            return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
        }
    }
}
