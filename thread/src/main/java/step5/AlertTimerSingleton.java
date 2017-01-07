package step5;

import java.util.Timer;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertTimerSingleton extends Timer {
    private static AlertTimerSingleton alertTimerSingleton = new AlertTimerSingleton();

    private AlertTimerSingleton() {
    }

    public static AlertTimerSingleton getInstance() {
        return alertTimerSingleton;
    }

    public void schedule(AlertTask alertTask, long delay, long period) {
        AlertTaskContainer.getInstance().put(alertTask.getName(), alertTask);
        super.schedule(alertTask, delay, period);
    }
}
