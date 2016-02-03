package com.basti.gankmvp.presenter;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.basti.gankmvp.utils.webutils.ChromeClient;
import com.basti.gankmvp.utils.webutils.GankWebView;
import com.basti.gankmvp.view.IWebView;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public class WebPresenter extends BasePresenter<IWebView> {
    public WebPresenter(Context context, IWebView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    public void refresh(WebView webView) {
        webView.reload();
    }

    public void setWebSettings(WebView webview, String url) {
        WebSettings settings = webview.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        //与屏幕等宽
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持缩放
        settings.setSupportZoom(true);
        //WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        /*
         * Using WebChromeClient allows you to handle Javascript dialogs, favicons, titles, and the progress.
         * Take a look of this example: Adding alert() support to a WebView

         At first glance, there are too many differences WebViewClient & WebChromeClient.
         But, basically: if you are developing a WebView that won't require too many features but rendering HTML,
         you can just use a WebViewClient. On the other hand, if you want to (for instance) load the favicon of the page you are rendering,
         you should use a WebChromeClient object and override the onReceivedIcon(WebView view, Bitmap icon).

         Most of the times, if you don't want to worry about those things... you can just do this:

         webView= (WebView) findViewById(R.id.webview);
         webView.setWebChromeClient(new WebChromeClient());
         webView.setWebViewClient(new WebViewClient());
         webView.getSettings().setJavaScriptEnabled(true);
         webView.loadUrl(url);
         And your WebView will (in theory) have all features implemented (as the android native browser).
        * */
        webview.setWebChromeClient(new ChromeClient());
        //webview.setWebViewClient(new GankClient1());
        webview.setWebViewClient(new GankWebView());
        webview.loadUrl(url);
    }

    private class GankClient1 extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }
}
