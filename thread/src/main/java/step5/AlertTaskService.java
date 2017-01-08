package step5;

import sun.rmi.runtime.Log;

import java.util.List;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertTaskService {
    private Log log;

    public List listAlertTask(PageParam<MericTask> param) {
        return null;
    }

    public void addAlertTask(MericTask mericTask) {
    }

    public void deleteAlertTask(MericTask mericTask) {
        AlertTask alertTask = (AlertTask) AlertTaskContainer.getInstance().get(mericTask.getName());
        //delete db record
    }
    public void modifyAlertTask(MericTask mericTask) {
    }

    public MericTask getMericTaskById(long id) {
        return null;
    }

    public void onOff(long id, String onOff) {
        MericTask mericTask = getMericTaskById(id);
        if ("on".equals(onOff)) {
            mericTask.setStatus(1);
            AlertTimerSingleton.getInstance().schedule(new AlertTask(mericTask), mericTask.getPeriodMs());
        } else {
            mericTask.setStatus(0);
            AlertTask alertTask = (AlertTask) AlertTaskContainer.getInstance().get(mericTask.getName());
        }
        modifyAlertTask(mericTask);
    }
}
