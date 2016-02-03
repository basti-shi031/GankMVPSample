package com.basti.gankmvp.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.basti.gankmvp.BastiApplication;
import com.basti.gankmvp.R;
import com.basti.gankmvp.model.Gank;
import com.basti.gankmvp.presenter.WebPresenter;
import com.basti.gankmvp.ui.adpater.StarAdapter;
import com.basti.gankmvp.ui.base.ToolBarActivity;
import com.basti.gankmvp.utils.SnackUtils;
import com.basti.gankmvp.view.IWebView;
import com.squareup.leakcanary.RefWatcher;

import butterknife.Bind;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public class WebViewActivity extends ToolBarActivity<WebPresenter> implements IWebView {

    @Bind(R.id.webview)
    WebView webView;
    @Bind(R.id.contentview)
    LinearLayout contentView;

    private Gank gank;

    @Override
    protected void initPresenter() {
        presenter = new WebPresenter(this, this);
        presenter.initPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    public void showProgress(int progress) {

    }

    @Override
    public void setWebTitle(String title) {
        setTitle(title);
    }

    @Override
    public void openFailed() {
        SnackUtils.show(mToolbar,getResources().getString(R.string.open_url_failed));
    }

    @Override
    public void init() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gank = getGank(bundle, StarAdapter.WEBTAG);
        }

        setTitle(gank.desc);

        presenter.setWebSettings(webView,gank.url);

    }

    private Gank getGank(Bundle bundle, String key) {
        return (Gank) bundle.getSerializable(key);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            contentView.removeView(webView);
            webView.removeAllViews();
            webView.destroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }
}
