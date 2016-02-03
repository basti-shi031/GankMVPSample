package com.basti.gankmvp.utils.webutils;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by SHIBW-PC on 2016/2/3.
 */
public class GankWebView extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url != null && !TextUtils.isEmpty(url)){
            view.loadUrl(url);
        }
        return true;
    }
}
