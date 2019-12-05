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
import com.deepblue.aidevicemanager.F
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
    lateinit var mHead: Head
    fun isHeadInit() = ::mHead.isInitialized
    lateinit var compositeDisposable: CompositeDisposable
    final override fun initV(view: View) {
        compositeDisposable = CompositeDisposable()
        initView()
        loaddata()
    }

    abstract fun initView()
    abstract fun loaddata()
    override fun onClick(v: View) {

    }

    override fun disposeMsg(type: Int, obj: Any?) {
        when (type) {
            1110 -> { //电池
                if (isHeadInit()) mHead?.setStatus()
            }
            1112 -> { //ws state
                if (isHeadInit()) mHead?.setStatus()
            }
        }

    }

    override fun onSuccess(data: String?, method: String) {
    }


    override fun onError(code: String?, msg: String?, data: String?, method: String) {
        if (code != null && (code == "0020" || code == "0021" || code == "0022")) {
            F.logOut(context)
        }
    }

    fun <T> load(o: Observable<HttpResult<T>>, m: String, isShow: Boolean = true) {
        var s = S<T>(
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
            .doFinally { if (s.mProgressDialog.isShowing) s.mProgressDialog.dismiss() }.subscribe(s)
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
        OkHttpUtils.getInstance().cancelTag(this)
        super.onDestroy()
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        mHead = Head(context)
        mHead.canGoBack()
        mHead.setStatus()
        actionBar?.addView(
            mHead,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    private var progressDialog: ProgressDialog? = null
    fun showProgressDialog(title: String, message: String) {
        if (progressDialog == null) {

            progressDialog = ProgressDialog.show(
                context,
                title, message, true, false
            )
        } else if (progressDialog!!.isShowing) {
            progressDialog!!.setTitle(title)
            progressDialog!!.setMessage(message)
        }
        progressDialog?.show()
    }

    /*
     * 隐藏提示加载
     */
    fun hideProgressDialog() {

        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }

    }

}
