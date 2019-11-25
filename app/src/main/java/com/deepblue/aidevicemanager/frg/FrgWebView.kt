//
//  FrgLogin
//
//  Created by DELL on 2019-10-15 13:14:05
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.frg_webview.*
import timber.log.Timber


class FrgWebView  : BaseFrg() {
    var url: String? = null
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_webview)
    }

    override fun initView() {
        url = arguments?.getString("url")
        Timber.d(url)
    }


    override fun loaddata() {
        mWebView.loadUrl(url)
        mWebView.getSettings().setJavaScriptEnabled(true)
        mWebView.getSettings().setDomStorageEnabled(true)
        mWebView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url)
                return true
            }
        })
//        mWebView.setWebChromeClient(object : WebChromeClient() {
//            override fun onProgressChanged(view: WebView, newProgress: Int) {
//                if (newProgress == 100) {
//                    mProgressBar.setVisibility(View.GONE)//加载完网页进度条消失
//                } else {
//                    mProgressBar.setVisibility(View.VISIBLE)//开始加载网页时显示进度条
//                    mProgressBar.setProgress(newProgress)//设置进度值
//                }
//            }
//        })
//        mWebView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
//            if (event.action == KeyEvent.ACTION_DOWN) {
//                if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) { // 表示按返回键
//                    mWebView.goBack() // 后退
//                    return@OnKeyListener true // 已处理
//                }
//            }
//            false
//        })

    }

    override fun onSuccess(data: String?, method: String) {
    }
}