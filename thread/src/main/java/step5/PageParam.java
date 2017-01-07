package step5;

/**
 * Created by fuyuanpu on 2017/1/7.
 */
public class PageParam<T> {
    T t;
    int pageNo;
    int pageSize = 10;

    public PageParam(int pageNo, T t) {
        this.pageNo = pageNo;
        this.t = t;
    }

    public PageParam(int pageNo, int pageSize, T t) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }
}
