package step6;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class Test {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread1 t2 = new MyThread1();
        MyThread1 t3 = new MyThread1();
        MyThread1 t4 = new MyThread1();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    static class MyThread1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println(Singleton.getInstance().hashCode());
        }
    }
}
