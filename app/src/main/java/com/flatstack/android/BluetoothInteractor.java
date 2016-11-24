package com.flatstack.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

/**
 * Created by almaziskhakov on 24/11/2016.
 */

public class BluetoothInteractor {

    private Context mContext;
    private BluetoothSPP mBluetooth;

    public BluetoothInteractor(@NonNull Context context){
        mContext = context;
        mBluetooth = new BluetoothSPP(context);
    }

    public boolean isBluetoothAvalible(){
        return mBluetooth.isBluetoothAvailable();
    }

    public boolean isBluetoothEnable(){
        return mBluetooth.isBluetoothEnabled();
    }

    public void startBluetoothService(){
        mBluetooth.startService(BluetoothState.DEVICE_ANDROID);
    }

    public void stopBluetoothService(){
        mBluetooth.stopService();
    }

    public void enableBluetooth(){
        mBluetooth.enable();
    }

    public void onChooseDevice(int requestCode, int resultCode, Intent data){
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if(resultCode == Activity.RESULT_OK)
                mBluetooth.connect(data);
        } else if(requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if(resultCode == Activity.RESULT_OK) {
                mBluetooth.setupService();
                mBluetooth.startService(BluetoothState.DEVICE_ANDROID);
//                setup();
            } else {
                // Do something if user doesn't choose any device (Pressed back)
            }
        }
    }


}
