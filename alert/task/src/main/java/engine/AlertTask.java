package engine;

import java.time.LocalDateTime;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertTask implements Runnable {
    MericTask task;

    public AlertTask(MericTask mericTask) {
        this.task = mericTask;
    }

    public void run() {
        handle(task);
    }

    private void handle(MericTask task) {
        if (task.getPeriod() == 10) {
            try {
                Thread.sleep(9000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("时间:" + LocalDateTime.now() + " " + task.toString());
    }

}
