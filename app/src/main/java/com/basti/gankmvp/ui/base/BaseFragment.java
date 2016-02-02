package com.basti.gankmvp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basti.gankmvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(provideLayoutId(),container,false);
        ButterKnife.bind(this,v);
        initPresenter();
        return v;
    }

    protected abstract void initPresenter();

    protected abstract int provideLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
