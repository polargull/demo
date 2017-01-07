package step5;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class TaskIniter {
    public List<TimerTask> list = new ArrayList<TimerTask>();

    public void init() {
        //获取打开报警任务
        MericTask task1 = new MericTask("task1", 11, 10);
        MericTask task2 = new MericTask("task2", 12, 5);
        MericTask task3 = new MericTask("task3", 13, 2);

        AlertTimerSingleton.getInstance().schedule(new AlertTask(task1), 1000, task1.getPeriodMs());
        AlertTimerSingleton.getInstance().schedule(new AlertTask(task2), 1000, task2.getPeriodMs());
        AlertTimerSingleton.getInstance().schedule(new AlertTask(task3), 1000, task3.getPeriodMs());
    }

    public static void main(String[] args) throws InterruptedException {
        new TaskIniter().init();
        Thread.sleep(2000);
//        AlertTask task1 = (AlertTask) AlertTaskContainer.getInstance().get("task1");
//        AlertTask task2 = (AlertTask) AlertTaskContainer.getInstance().get("task2");
//        AlertTask task3 = (AlertTask) AlertTaskContainer.getInstance().get("task3");
//        task1.cancel();
//        task2.cancel();
//        task3.cancel();
//        AlertTimerSingleton.getInstance().cancel();
    }
}
