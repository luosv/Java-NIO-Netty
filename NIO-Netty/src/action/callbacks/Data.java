package action.callbacks;

/**
 * Data
 * Created by luosv on 2016/12/6 0006.
 */
public class Data {

    private int n;
    private int m;

    public Data(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public String toString() {
        int r = n / m;
        return n + "/" + m + " = " + r;
    }

}
