package com.flatstack.android.utils.di;

import android.app.Application;

import com.flatstack.android.main_screen.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by almaziskhakov on 24/11/2016.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
