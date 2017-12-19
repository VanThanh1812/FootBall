package com.codedao;

import android.app.Application;

/**
 * Created by Ha Nguyen on 12/13/17.
 */

public class App extends Application {
    private static App mSelf;

    public static App self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
    }
}
