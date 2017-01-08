package engine;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertScheduledExecutor {
    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private static AlertScheduledExecutor alertScheduledExecutor = new AlertScheduledExecutor();

    private AlertScheduledExecutor() {
    }

    public static AlertScheduledExecutor getInstance() {
        return alertScheduledExecutor;
    }

    public Future schedule(AlertTask alertTask, long period) {
        return scheduledExecutorService.scheduleAtFixedRate(alertTask, 1, period, TimeUnit.SECONDS);
    }
}
