package action.callbacks;

/**
 * MyFetcher
 * Created by luosv on 2016/12/6 0006.
 */
class MyFetcher implements Fetcher {

    private final Data data;

    MyFetcher(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetcherCallback callback) {
        try {
            callback.onData(data);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

}
