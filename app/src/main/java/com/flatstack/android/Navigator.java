package com.flatstack.android;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.flatstack.android.devices_screen.DevicesActivity;

/**
 * Created by Revern on 24.11.2016.
 */

public class Navigator {
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    public static void enableBluetooth(@NonNull FragmentActivity activity){
        activity.startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
    }

    public static void devices(@NonNull FragmentActivity activity){
        activity.startActivityForResult(new Intent(activity, DevicesActivity.class), REQUEST_CONNECT_DEVICE_INSECURE);
    }
}
