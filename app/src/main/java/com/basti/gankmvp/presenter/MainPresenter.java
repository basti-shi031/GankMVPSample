package com.basti.gankmvp.presenter;

import android.content.Context;
import android.util.Log;

import com.basti.gankmvp.http.BastiGankClient;
import com.basti.gankmvp.model.GankData;
import com.basti.gankmvp.model.MeiziData;
import com.basti.gankmvp.view.IMainView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by SHIBW-PC on 2016/1/29.
 */
public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(Context context, IMainView iView) {
        super(context, iView);
    }

    public void fetchMeiziData(int page) {
        mView.showProgressView();

        mSubscription = Observable.zip(BastiGankClient.getBastiGankRetrofit().fetchMeiziData(page),
                BastiGankClient.getBastiGankRetrofit().getGankData(page),
                new Func2<MeiziData, GankData, MeiziData>() {
                    @Override
                    public MeiziData call(MeiziData meiziData, GankData gankData) {
                        return createMeiziDataWithGank(meiziData, gankData);
                    }

                    ;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MeiziData>() {
                    @Override
                    public void call(MeiziData meiziData) {
                        if (meiziData.results.size() == 0) {
                            mView.showNoMoreData();
                        } else {
                            mView.showMeiziList(meiziData.results);
                        }
                        mView.hideProgressView();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showErrorView(throwable);
                        mView.hideProgressView();
                    }
                });


    }

    private MeiziData createMeiziDataWithGank(MeiziData meiziData, GankData gankData) {
        int size = Math.min(meiziData.results.size(), gankData.results.size());

        for (int i = 0; i < size; i++) {
            meiziData.results.get(i).desc = meiziData.results.get(i).desc + "，" + gankData.results.get(i).desc;
            meiziData.results.get(i).who = gankData.results.get(i).who;
        }

        return meiziData;
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }
}
