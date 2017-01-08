package engine;

import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fuyuanpu on 2017/1/8.
 */
public class AlertOnOff {
    private AlertTaskContainer container = AlertTaskContainer.getInstance();
    private AlertScheduledExecutor alertScheduledExecutor = AlertScheduledExecutor.getInstance();

    public void start(MericTask mericTask) throws AlertException {
        Object o = container.get(mericTask.getName());
        if (o != null)
            throw new AlertException("报警已经打开！");
        Future future = alertScheduledExecutor.schedule(new AlertTask(mericTask), mericTask.getPeriod());
        container.put(mericTask, future);
    }

    public void close(MericTask mericTask) {
        Future future = (Future) container.remove(mericTask.getName());
        if (future != null)
            future.cancel(false);

    }

    public void restart(MericTask mericTask) throws AlertException {
        close(mericTask);
        start(mericTask);
    }
}
