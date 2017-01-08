package step5;

import java.util.concurrent.*;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertTimerSingleton {
    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private static AlertTimerSingleton alertTimerSingleton = new AlertTimerSingleton();

    private AlertTimerSingleton() {
    }

    public static AlertTimerSingleton getInstance() {
        return alertTimerSingleton;
    }

    public Future<AlertTask> schedule(AlertTask alertTask, long period) {
        return (Future<AlertTask>) scheduledExecutorService.scheduleAtFixedRate(alertTask, 1, period, TimeUnit.SECONDS);
    }
}
