package action.callbacks;

/**
 * FetcherCallback
 * Created by luosv on 2016/12/6 0006.
 */
interface FetcherCallback {

    void onData(Data data) throws Exception;

    void onError(Throwable cause);

}
