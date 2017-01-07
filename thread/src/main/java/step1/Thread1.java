package step1;

/**
 * Created by fuyuanpu on 2017/1/7.
 * 创建、启动线程
 */
public class Thread1 {
    public static void main(String[] args) {
        System.out.println("当前线程名：" + Thread.currentThread().getName());
        new MyThread1().start();
        new Thread(new MyThread2()).start();
    }

    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("My Thread1!");
        }
    }

    public static class MyThread2 implements Runnable {
        public void run() {
            System.out.println("My Thread2!");
        }
    }
}
