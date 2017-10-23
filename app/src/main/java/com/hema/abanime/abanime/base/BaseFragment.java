package com.hema.abanime.abanime.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Homa on 2017/9/1.
 * 基础Fragment类
 */

public abstract class BaseFragment extends com.trello.rxlifecycle2.components.support.RxFragment implements BaseInit {
    protected Unbinder unbinder;
    protected FragmentActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mActivity=getActivity();
        onInit();
        setListener();
        return view;
    }
    /*获取LayoutId*/
    public abstract
    @LayoutRes
    int getLayoutResId();

    /*界面操作*/
    @Override
    public void onInit() {
    }
    @Override
    public void setListener() {

    }

}
