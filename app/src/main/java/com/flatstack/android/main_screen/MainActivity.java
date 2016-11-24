package com.flatstack.android.main_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.flatstack.android.Navigator;
import com.flatstack.android.R;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BluetoothBaseActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, MainFragment.newInstance())
                .commit();
        }

    }

    @OnClick(R.id.bt_turn) void onTurnClick(){
        Navigator.enableBluetooth(this);
    }

    @OnClick(R.id.bt_scan) void onScanClick(){
        showDeviceListDialog();
    }

    @Override
    public void onBluetoothDisabled() {

    }

    @Override
    public void onBluetoothDeviceDisconnected() {

    }

    @Override
    public void onConnectingBluetoothDevice() {

    }

    @Override
    public void onBluetoothDeviceConnected(String name, String address) {

    }

    @Override
    public void onBluetoothSerialRead(String message) {

    }

    @Override
    public void onBluetoothSerialWrite(String message) {

    }
}