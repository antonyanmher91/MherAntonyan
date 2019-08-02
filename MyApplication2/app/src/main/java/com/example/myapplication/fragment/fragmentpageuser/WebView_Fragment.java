package com.example.myapplication.fragment.fragmentpageuser;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.myapplication.R;
import com.example.myapplication.model.MyWebView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebView_Fragment extends Fragment {

    public WebView_Fragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_web_view_, container, false);
        assert getArguments() != null;
        String url = getArguments().getString("KEY");
        WebView webView = view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebView());
        webView.loadUrl("https://www." + url);

        return view;
    }

}
