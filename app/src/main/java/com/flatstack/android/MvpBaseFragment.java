package com.flatstack.android;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.MvpFragment;

/**
 * Created by Revern on 24.11.2016.
 */

public class MvpBaseFragment extends Fragment{
    private MvpDelegate<? extends MvpFragment> mMvpDelegate;

    public MvpBaseFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    public void onStart() {
        super.onStart();
        this.getMvpDelegate().onAttach();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.getMvpDelegate().onDetach();
    }

    public void onDestroy() {
        super.onDestroy();
        boolean anyParentIsRemoving = false;
        if(Build.VERSION.SDK_INT >= 17) {
            for(Fragment parent = this.getParentFragment(); !anyParentIsRemoving && parent != null; parent = parent.getParentFragment()) {
                anyParentIsRemoving = parent.isRemoving();
            }
        }

        if(this.isRemoving() || anyParentIsRemoving || this.getActivity().isFinishing()) {
            this.getMvpDelegate().onDestroy();
        }

    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.getMvpDelegate().onSaveInstanceState(outState);
    }

    public MvpDelegate getMvpDelegate() {
        if(this.mMvpDelegate == null) {
            this.mMvpDelegate = new MvpDelegate(this);
        }

        return this.mMvpDelegate;
    }
}
