package com.basti.gankmvp.presenter;

import android.content.Context;

import com.basti.gankmvp.http.BastiGankClient;
import com.basti.gankmvp.model.DailyData;
import com.basti.gankmvp.model.Gank;
import com.basti.gankmvp.model.GankData;
import com.basti.gankmvp.model.Result;
import com.basti.gankmvp.view.IGankView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by SHIBW-PC on 2016/2/1.
 */
public class GankPresenter extends BasePresenter<IGankView> {

    public GankPresenter(Context context, IGankView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void fetchGankData(int year,int month,int day){
        mView.showProgressBar();

        mSubscription = BastiGankClient.getBastiGankRetrofit().getDailyData(year,month,day)
                .map(new Func1<DailyData, List<Gank>>() {
                    @Override
                    public List<Gank> call(DailyData dailyData) {
                        return addAllResults(dailyData.results);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Gank>>() {
                    @Override
                    public void call(List<Gank> ganks) {
                        mView.hideProgressBar();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.hideProgressBar();
                        mView.showErrorView();
                    }
                });
    }

    private List<Gank> addAllResults(Result dailyData) {

        List<Gank> list = new ArrayList<>();
        if (dailyData.androidList != null) list.addAll(dailyData.androidList);
        if (dailyData.iOSList != null) list.addAll(dailyData.iOSList);
        if (dailyData.webList != null) list.addAll(dailyData.webList);
        if (dailyData.appList != null) list.addAll(dailyData.appList);
        if (dailyData.resourceList != null) list.addAll(dailyData.resourceList);
        if (dailyData.recommendList != null) list.addAll(dailyData.recommendList);
        if (dailyData.videoList != null) list.addAll(0, dailyData.videoList);

        return list;

    }
}
