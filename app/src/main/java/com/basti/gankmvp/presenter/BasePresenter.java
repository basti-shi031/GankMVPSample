package com.basti.gankmvp.presenter;

import android.content.Context;

import com.basti.gankmvp.view.IBaseView;

import rx.Subscription;

/**
 * 基础Presenter
 * Created by SHIBW-PC on 2016/1/28.
 */
public abstract class BasePresenter<T extends IBaseView> {

    protected Subscription mSubscription;
    protected Context mContext;
    protected T mView;

    public BasePresenter(Context context,T iView){
        mContext =context;
        mView = iView;
    }

    public void initPresenter(){
        mView.init();
    }

    public abstract void release();
}
