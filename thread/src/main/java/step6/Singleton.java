package step6;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fuyuanpu on 2017/1/7.
 * 线程安全的单例
 */
public class Singleton {
    private static Singleton ourInstance;
    private static ReentrantLock lock = new ReentrantLock();
    public static Singleton getInstance() {
        lock.lock();
        if (ourInstance == null) {
            //测试使用
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            ourInstance = new Singleton();
        }
        lock.unlock();
        return ourInstance;
    }

    private Singleton() {
    }
}
