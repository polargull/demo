package step5;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class TaskIniter {
    public List<TimerTask> list = new ArrayList<TimerTask>();
    static MericTask task1 = new MericTask("task1", 11, 5);
    static MericTask task2 = new MericTask("task2", 12, 2);

    public void init() {
        //获取打开报警任务

//        MericTask task3 = new MericTask("task3", 13, 2);

        Future<AlertTask> future = AlertTimerSingleton.getInstance().schedule(new AlertTask(task1), task1.getPeriod());
        AlertTaskContainer.getInstance().put(task1.getName(), future);
        AlertTimerSingleton.getInstance().schedule(new AlertTask(task2), task2.getPeriod());
//        AlertTimerSingleton.getInstance().schedule(new AlertTask(task3), task3.getPeriodMs());
    }

    public static void main(String[] args) throws InterruptedException {
        new TaskIniter().init();
        Thread.sleep(3000);
        Future future = (Future) AlertTaskContainer.getInstance().remove(task1.getName());
        System.out.println(future);
//        future.cancel(false);
        System.out.println("close task1");
    }
}
