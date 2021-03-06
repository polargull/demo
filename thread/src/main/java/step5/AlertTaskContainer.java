package step5;

import java.util.HashMap;
import java.util.concurrent.Future;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class AlertTaskContainer<K, V> extends HashMap {
    private static AlertTaskContainer<String, Future> container = new AlertTaskContainer<String, Future>();

    private AlertTaskContainer() {
    }

    public static AlertTaskContainer getInstance() {
        return container;
    }
}
