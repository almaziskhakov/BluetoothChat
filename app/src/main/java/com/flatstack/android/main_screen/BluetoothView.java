package com.flatstack.android.main_screen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.arellomobile.mvp.MvpView;

import java.util.List;

/**
 * Created by Revern on 24.11.2016.
 */

public interface BluetoothView extends MvpView {
    void enableBluetooth();
    void showMessage();
    void showPairedDevices(@Nullable List<String> devices);
}
