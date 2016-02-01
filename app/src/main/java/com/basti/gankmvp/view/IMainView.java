package com.basti.gankmvp.view;

import com.basti.gankmvp.model.Meizi;

import java.util.List;

/**
 *
 * 主界面的接口
 * Created by SHIBW-PC on 2016/1/29.
 */
public interface IMainView extends IBaseView {

    void showProgressView();
    void hideProgressView();
    void showErrorView(Throwable throwable);
    void showNoMoreData();
    void showMeiziList(List<Meizi> meiziList);

}
