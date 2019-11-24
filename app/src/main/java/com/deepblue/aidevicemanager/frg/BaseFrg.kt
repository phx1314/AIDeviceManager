//
//  BaseFrg
//
//  Created by DELL on 2019-10-15 13:07:28
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.app.ProgressDialog
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.item.Head
import com.mdx.framework.Frame
import com.mdx.framework.activity.MFragment
import com.mdx.framework.service.subscriber.HttpResult
import com.mdx.framework.service.subscriber.HttpResultSubscriberListener
import com.mdx.framework.service.subscriber.S
import com.mdx.framework.util.AbAppUtil
import com.mdx.framework.util.Helper
import com.zhy.http.okhttp.OkHttpUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


abstract class BaseFrg : MFragment(), View.OnClickListener, HttpResultSubscriberListener {
    var compositeDisposable = CompositeDisposable()
    final override fun initV(view: View) {
        initView()
        loaddata()
    }

    abstract fun initView()
    abstract fun loaddata()
    override fun onClick(v: View) {

    }

    override fun onSuccess(data: String?, method: String) {
    }

    override fun onError(status: String?, msg: String?) {
//        Log.e("onError", "status：$status  msg:$msg");
    }

    fun <T> load(o: Observable<HttpResult<T>>, m: String, isShow: Boolean = true) {
        var s =
            S<T>(
                this,
                ProgressDialog(context).apply { this.setMessage(getString(R.string.loading)) },
                m,
                isShow
            )
        compositeDisposable.add(s)
        if (!AbAppUtil.isNetworkAvailable(Frame.CONTEXT)) {
            Helper.toast(getString(R.string.net_error))
        }
        o.subscribeOn(Schedulers.newThread()).unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (s.isShow) s.mProgressDialog.show() }
            .doFinally { if (s.mProgressDialog.isShowing) s.mProgressDialog.dismiss() }
            .subscribe(s)
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
        OkHttpUtils.getInstance().cancelTag(this)
        super.onDestroy()
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        actionBar?.addView(
            Head(context),
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }


}
