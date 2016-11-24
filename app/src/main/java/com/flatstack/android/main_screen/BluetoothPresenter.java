package com.flatstack.android.main_screen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Revern on 24.11.2016.
 */

@InjectViewState
public class BluetoothPresenter extends MvpPresenter<BluetoothView> {

    public static final String TAG = "BluetoothPresenter";

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(!mBluetoothAdapter.isEnabled()){
            getViewState().enableBluetooth();
        }
    }

    public void showPairedDevices(){
        List<String> devicesNames = new ArrayList<>();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                devicesNames.add(device.getName() + "\n" + device.getAddress());
            }
        }
        getViewState().showPairedDevices(devicesNames);
    }

    public void startFindingDevices(){
        if(mBluetoothAdapter.isDiscovering())
            mBluetoothAdapter.cancelDiscovery();
        mBluetoothAdapter.startDiscovery();
    }

}
