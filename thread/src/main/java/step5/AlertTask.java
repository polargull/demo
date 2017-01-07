package step5;

import java.time.LocalDateTime;
import java.util.TimerTask;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertTask extends TimerTask {
    MericTask task;

    public AlertTask(MericTask mericTask) {
        this.task = mericTask;
    }

    public void run() {
        handle(task);
    }

    private void handle(MericTask task) {
        if (task.getPeriod() == 2) {
            try {
                Thread.sleep(4000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("时间:" + LocalDateTime.now() + " " + task.toString());
    }

    public String getName() {
        return task.getName();
    }
}
