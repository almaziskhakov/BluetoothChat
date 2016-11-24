package com.flatstack.android.devices_screen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.flatstack.android.MvpBaseActivity;
import com.flatstack.android.R;
import com.flatstack.android.main_screen.BluetoothPresenter;
import com.flatstack.android.main_screen.BluetoothView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class DevicesActivity extends MvpBaseActivity implements BluetoothView {

    @Bind(R.id.paired)
    ListView uiPairedDevices;
    @Bind(R.id.found)
    ListView uiFoundDevices;

    @InjectPresenter(type = PresenterType.GLOBAL, tag = BluetoothPresenter.TAG)
    BluetoothPresenter mBluetoothPresenter;

    private List<String> mPairedDevices = new ArrayList<>();
    private List<String> mFoundDevices = new ArrayList<>();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                mFoundDevices.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        mBluetoothPresenter.showPairedDevices();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        if(ba.isDiscovering())
            ba.cancelDiscovery();
        ba.startDiscovery();

        showDevices();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void enableBluetooth() {

    }

    private void showDevices(){
        ArrayAdapter<String> pairedDeviceAdapter = new ArrayAdapter<>(this, R.layout.list_item, mPairedDevices);
        ArrayAdapter<String> pairedFoundAdapter = new ArrayAdapter<>(this, R.layout.list_item, mFoundDevices);
        uiPairedDevices.setAdapter(pairedDeviceAdapter);
        uiFoundDevices.setAdapter(pairedFoundAdapter);
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showPairedDevices(@Nullable List<String> devices) {
        mPairedDevices = devices;
    }
}
