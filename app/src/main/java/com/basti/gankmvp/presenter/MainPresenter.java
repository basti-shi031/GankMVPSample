package com.basti.gankmvp.presenter;

import android.content.Context;

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
public class MainPresenter extends BasePresenter<IMainView>{

    public MainPresenter(Context context, IMainView iView) {
        super(context, iView);
    }

    public void fetchMeiziData(int page){
        mView.showErrorView();

        mSubscription = Observable.zip(BastiGankClient.getBastiGankRetrofit().fetchMeiziData(page),
                BastiGankClient.getBastiGankRetrofit().getGankData(page),
                new Func2<MeiziData, GankData, MeiziData>() {
                    @Override
                    public MeiziData call(MeiziData meiziData, GankData gankData) {
                        return createMeiziDataWithGank(meiziData,gankData);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MeiziData>() {
                    @Override
                    public void call(MeiziData meiziData) {
                        if (meiziData.getMeiziList().size() == 0 ){
                            mView.showNoMoreData();
                        }else {
                            mView.showMeiziList(meiziData.getMeiziList());
                        }
                        mView.hideProgressView();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showErrorView();
                        mView.hideProgressView();
                    }
                });
    }

    private MeiziData createMeiziDataWithGank(MeiziData meiziData, GankData gankData) {
        int size = Math.min(meiziData.getMeiziList().size(),gankData.getGankData().size());

        for (int i = 0;i<size;i++){
            meiziData.getMeiziList().get(i).desc = meiziData.getMeiziList().get(i).desc + "，" + gankData.getGankData().get(i).desc;
            meiziData.getMeiziList().get(i).who = gankData.getGankData().get(i).who;
        }

        return meiziData;
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }
}
