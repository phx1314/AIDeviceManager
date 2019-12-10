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
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.util.HtmlFormat
import com.mdx.framework.util.AbLogUtil
import kotlinx.android.synthetic.main.frg_webview.*


class FrgWebView : BaseFrg() {
    var url: String? = null
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_webview)
    }

    override fun initView() {
        url = arguments?.getString("url")
        AbLogUtil.d(url)
    }


    override fun loaddata() {
//        mWebView.loadUrl(url)
        mWebView.getSettings().setJavaScriptEnabled(true)
        mWebView.getSettings().setDomStorageEnabled(true)
        mWebView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url)
                return true
            }
        })

        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        if ((F.baseUrl + "configuration_help.html").equals(url)) {
            load(F.gB().queryContentConfigForHelp(), "queryContentConfigForHelp")
        } else if ((F.baseUrl + "privacy_policy.html").equals(url)) {
            load(F.gB().queryContentConfigForPrivacyPolicy(), "queryContentConfigForPrivacyPolicy")
        } else if ((F.baseUrl + "user_item.html").equals(url)) {
            load(F.gB().queryContentConfigForUserTerm(), "queryContentConfigForUserTerm")
        } else if ((F.baseUrl + "about.html").equals(url)) {
            load(F.gB().queryContentConfigForAbout(), "queryContentConfigForAbout")
        }

    }


    override fun onSuccess(data: String?, method: String) {
        var content = F.data2Model(data, Array<String>::class.java)
        content?.forEach {
//            mTextView.setText(Html.fromHtml(it))

            mWebView.loadDataWithBaseURL(null, HtmlFormat.getNewContent(it),"text/html","utf-8",null);
        }

    }
}