package com.basti.gankmvp.view;

import com.basti.gankmvp.model.Gank;

import java.util.List;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public interface IStarView extends IBaseView {

    void showGankListView(List<Gank> list);
    void showProgressBar();
    void hideProgressBar();
    void showErrorView();

}
