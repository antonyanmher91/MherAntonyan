package com.example.myapplication.model;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends WebViewClient {


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
