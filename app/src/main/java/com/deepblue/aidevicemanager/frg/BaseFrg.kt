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
import android.view.View
import android.widget.LinearLayout
import com.mdx.framework.activity.MFragment
import com.mdx.framework.service.ApiService
import com.mdx.framework.service.subscriber.HttpResult
import com.mdx.framework.service.subscriber.HttpResultSubscriberListener
import com.mdx.framework.service.subscriber.S
import com.mdx.framework.util.AbAppUtil
import com.mdx.framework.util.Frame
import com.mdx.framework.util.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


fun gB() = com.mdx.framework.service.gB(ApiService::class.java, "http://www.wanandroid.com/")
abstract class BaseFrg : MFragment(), View.OnClickListener, HttpResultSubscriberListener {
    var compositeDisposable = CompositeDisposable()
    final override fun initV() {
        initView()
        loaddata()
    }

    abstract fun initView()
    abstract fun loaddata()
    override fun onClick(v: View) {

    }

    override fun onSuccess(data: Any?, method: String) {

    }

    override fun onError(status: String?, msg: String?) {
//        Log.e("onError", "status：$status  msg:$msg");
    }

    fun <T> load(o: Observable<HttpResult<T>>, m: String, isShow: Boolean = true) {
        var s =
            S<T>(this, ProgressDialog(context).apply { this.setMessage("加载中,请稍后...") }, m, isShow)
        compositeDisposable.add(s)
        if (!AbAppUtil.isNetworkAvailable(Frame.CONTEXT)) {
            Helper.toast("无可用网络，请检查网络连接")
        }
        o.subscribeOn(Schedulers.newThread()).unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (s.isShow) s.mProgressDialog.show() }
            .doFinally { if (s.mProgressDialog.isShowing) s.mProgressDialog.dismiss() }
            .subscribe(s)
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
        Thread(Runnable {}).start()
        super.onDestroy()
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        actionBar?.visibility = View.GONE
    }
}
