package com.basti.gankmvp.view;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public interface IWebView extends IBaseView {

    void showProgress(int progress);

    void setWebTitle(String title);

    void openFailed();
}
