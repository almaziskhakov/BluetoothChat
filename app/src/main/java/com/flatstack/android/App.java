package com.flatstack.android;

import android.app.Application;

import com.flatstack.android.utils.TimberCrashReportingTree;
import com.flatstack.android.utils.di.AppComponent;
import com.flatstack.android.utils.di.AppModule;
import com.flatstack.android.utils.di.DaggerAppComponent;

import timber.log.Timber;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        appComponent = DaggerAppComponent.builder()
//             list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .build();

        Timber.plant(BuildConfig.DEBUG
                ? new Timber.DebugTree()
                : new TimberCrashReportingTree());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
