package com.basti.gankmvp.utils.webutils;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.basti.gankmvp.view.IWebView;

/**
 * Created by SHIBW-PC on 2016/2/3.
 */
public class ChromeClient extends WebChromeClient {

   // private IWebView iView;

    /*public ChromeClient(IWebView view){
        iView = view;
    }*/

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        //iView.showProgress(newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        //iView.setWebTitle(title);
    }
}
