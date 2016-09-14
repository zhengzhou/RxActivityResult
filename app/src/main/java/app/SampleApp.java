package app;

import android.app.Application;

import rx_activity_result2.RxActivityResult;

public class SampleApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        RxActivityResult.register(this);
    }
}
