package com.flatstack.android;

import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpDelegate;
import com.f2prateek.dart.Dart;
import com.flatstack.android.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Revern on 24.11.2016.
 */

public class MvpBaseActivity extends BaseActivity {

    private MvpDelegate<? extends MvpActivity> mMvpDelegate;

    public MvpBaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getMvpDelegate().onDetach();
        if(this.isFinishing()) {
            this.getMvpDelegate().onDestroy();
        }

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.getMvpDelegate().onSaveInstanceState(outState);
    }

    protected void onStart() {
        super.onStart();
        this.getMvpDelegate().onAttach();
    }

    public MvpDelegate getMvpDelegate() {
        if(this.mMvpDelegate == null) {
            this.mMvpDelegate = new MvpDelegate(this);
        }

        return this.mMvpDelegate;
    }
}
