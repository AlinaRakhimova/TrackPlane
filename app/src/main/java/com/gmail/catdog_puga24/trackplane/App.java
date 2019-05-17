package com.gmail.catdog_puga24.trackplane;

import android.app.Application;

import com.gmail.catdog_puga24.trackplane.di.modules.AppComponent;
import com.gmail.catdog_puga24.trackplane.di.modules.DaggerAppComponent;

public class App extends Application {

    public static App app;
    private AppComponent appComponent;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
