package com.showad;

import android.app.Application;

import com.smaato.sdk.core.SmaatoSdk;

public class ShowAdApplication extends Application {

    private String publisherId = "1100042525";


    @Override
    public void onCreate() {
        super.onCreate();

        SmaatoSdk.init(this, publisherId);
    }
}
