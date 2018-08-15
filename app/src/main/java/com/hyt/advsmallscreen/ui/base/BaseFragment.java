package com.hyt.advsmallscreen.ui.base;

import android.app.Activity;
 
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

 
/**
 * Created by Tao on 2018/8/8 0008.
 */

public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    public View mRootView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        mRootView = getView();
        ui();
        data();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        
        return creatView(inflater,container);
    }

    protected abstract View creatView(LayoutInflater inflater, ViewGroup container);

    public abstract void data();
    public abstract void ui();

}
