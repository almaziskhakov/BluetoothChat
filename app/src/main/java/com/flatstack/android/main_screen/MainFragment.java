package com.flatstack.android.main_screen;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.flatstack.android.MvpBaseFragment;
import com.flatstack.android.Navigator;
import com.flatstack.android.R;
import com.flatstack.android.utils.HomeAsUp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainFragment extends MvpBaseFragment implements BluetoothView {

    @Bind(R.id.chat)
    ListView uiChat;

    List<String> mFoundDevices = new ArrayList<>();
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

    @InjectPresenter(type= PresenterType.GLOBAL, tag = BluetoothPresenter.TAG)
    BluetoothPresenter mBluetoothPresenter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.app_name);
        setHasOptionsMenu(true);
        HomeAsUp.disable((AppCompatActivity) getActivity());
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getActivity().registerReceiver(mReceiver, filter);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item, mFoundDevices);
        uiChat.setAdapter(adapter);
    }

    @Override
    public void enableBluetooth() {
        Navigator.enableBluetooth(getActivity());
    }

    @Override
    public void showMessage() {
        Toast.makeText(getActivity(), "Hello Pridurok", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPairedDevices(@Nullable List<String> devices) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, devices);
        uiChat.setAdapter(adapter);
    }
}
