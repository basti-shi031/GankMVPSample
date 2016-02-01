package com.basti.gankmvp.view;

import com.basti.gankmvp.model.Gank;

import java.util.List;

/**
 * Created by SHIBW-PC on 2016/2/1.
 */
public interface IGankView extends IBaseView {

    void showGankListView(List<Gank> listview);
    void showProgressBar();
    void hideProgressBar();
    void showErrorView();

}
