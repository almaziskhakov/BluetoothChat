package com.flatstack.android.utils.di;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.flatstack.android.App;
import com.flatstack.android.main_screen.MainActivity;

/**
 * Created by almaziskhakov on 24/11/2016.
 */

public class Injector {
    private static AppComponent getAppComponent(@NonNull FragmentActivity initialScreen) {
        return ((App) initialScreen.getApplicationContext()).getAppComponent();
    }

    public static void inject(MainActivity mainActivity){
        getAppComponent(mainActivity).inject(mainActivity);
    }
}
