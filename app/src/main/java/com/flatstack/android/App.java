package com.flatstack.android;

import android.app.Application;

import com.flatstack.android.utils.TimberCrashReportingTree;

import timber.log.Timber;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME

        Timber.plant(BuildConfig.DEBUG
                ? new Timber.DebugTree()
                : new TimberCrashReportingTree());
    }

}
