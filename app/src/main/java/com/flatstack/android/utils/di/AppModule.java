package com.flatstack.android.utils.di;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.flatstack.android.BluetoothInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by almaziskhakov on 24/11/2016.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public BluetoothInteractor providesBluetoothInteractor(@NonNull Context context){
        return new BluetoothInteractor(context);
    }


}
