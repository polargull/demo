package step5;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class MericTask {
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

    @Override
    public String toString() {
        return "MericTask{" +
                "name='" + name + '\'' +
                ", threshold=" + threshold +
                ", period=" + period +
                '}';
    }
}
