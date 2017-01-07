package step5;

import java.time.LocalDateTime;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class MericTask {
    /*id*/
    long id;
    /*任务名*/
    String name;
    /*阈值*/
    double threshold;
    /*轮训间隔*/
    long period;
    /*开关状态*/
    int status;
    /*通知手机号*/
    String cellphones[] = new String[5];
    /*创建时间*/
    LocalDateTime createTime;
    /*更新时间*/
    LocalDateTime updateTime;

    public MericTask(){}
    public MericTask(String name, double threshold, long period) {
        this.name = name;
        this.threshold = threshold;
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public double getThreshold() {
        return threshold;
    }

    public long getPeriod() {
        return period;
    }

    public long getPeriodMs() {
        return period * 1000l;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MericTask{" +
                "name='" + name + '\'' +
                ", threshold=" + threshold +
                ", period=" + period +
                '}';
    }
}
