package com.flatstack.android.main_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.flatstack.android.MvpBaseFragment;
import com.flatstack.android.Navigator;
import com.flatstack.android.R;
import com.flatstack.android.utils.HomeAsUp;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainFragment extends MvpBaseFragment implements BluetoothView {

    @Bind(R.id.chat)
    ListView uiChat;

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
    }
}
