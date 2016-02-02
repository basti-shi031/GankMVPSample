package com.basti.gankmvp.presenter;

import android.content.Context;

import com.basti.gankmvp.R;
import com.basti.gankmvp.http.BastiGankClient;

import com.basti.gankmvp.model.GankData;
import com.basti.gankmvp.view.IStarView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public class StarPresenter extends BasePresenter<IStarView> {
    public StarPresenter(Context context, IStarView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void fetchGankData(String type, int page) {
        mView.showProgressBar();

        mSubscription = BastiGankClient.getBastiGankRetrofit().fetchTypeData(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GankData>() {
                    @Override
                    public void call(GankData gankData) {
                        mView.hideProgressBar();
                        mView.showGankListView(gankData.results);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showErrorView();
                        mView.hideProgressBar();
                    }
                });


    }


    public String[] getTitles() {

        String[] titles = {
                getTitle(R.string.Android),
                getTitle(R.string.iOS),
                getTitle(R.string.web),
                getTitle(R.string.recommend),
                getTitle(R.string.resource)};

        return titles;
    }

    private String getTitle(int id) {

        return mContext.getResources().getString(id);
    }
}
