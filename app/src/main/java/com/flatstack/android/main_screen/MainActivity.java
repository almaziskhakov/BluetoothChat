package com.flatstack.android.main_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.flatstack.android.BluetoothInteractor;
import com.flatstack.android.MvpBaseActivity;
import com.flatstack.android.Navigator;
import com.flatstack.android.R;
import com.flatstack.android.utils.di.Injector;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends MvpBaseActivity implements BluetoothView {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @InjectPresenter(type = PresenterType.GLOBAL, tag =BluetoothPresenter.TAG)
    BluetoothPresenter mBluetoothPresenter;

    @Inject
    BluetoothInteractor bluetoothInteractor;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        Injector.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, MainFragment.newInstance())
                .commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!bluetoothInteractor.isBluetoothEnable())
            bluetoothInteractor.enableBluetooth();
    }

    @OnClick(R.id.bt_turn) void onTurnClick(){
        Navigator.enableBluetooth(this);
    }

    @OnClick(R.id.bt_scan) void onScanClick(){
        Navigator.devicesList(this);
    }

    @OnClick(R.id.send) void onSendClick(){ mBluetoothPresenter.showPairedDevices();

    }

    @Override
    public void enableBluetooth() {

    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showPairedDevices(@Nullable List<String> devices) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        bluetoothInteractor.onChooseDevice(requestCode, resultCode, data);
    }
}