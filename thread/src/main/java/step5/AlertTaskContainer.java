package step5;

import java.util.HashMap;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertTaskContainer<K, V> extends HashMap {
    private static AlertTaskContainer<String, AlertTask> container = new AlertTaskContainer< String,AlertTask>();

    private AlertTaskContainer() {
    }

    public static AlertTaskContainer<String, AlertTask> getInstance() {
        return container;
    }
}
