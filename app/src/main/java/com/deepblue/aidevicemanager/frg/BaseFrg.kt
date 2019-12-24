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
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.item.Head
import com.deepblue.aidevicemanager.ws.WsStatus
import com.mdx.framework.Frame
import com.mdx.framework.activity.MFragment
import com.mdx.framework.service.subscriber.HttpResult
import com.mdx.framework.service.subscriber.HttpResultSubscriberListener
import com.mdx.framework.service.subscriber.S
import com.mdx.framework.utility.AbAppUtil
import com.mdx.framework.utility.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_head.view.*


abstract class BaseFrg : MFragment(), View.OnClickListener, HttpResultSubscriberListener {
    lateinit var mHead: Head


    fun isHeadInit() = ::mHead.isInitialized
    @JvmField
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    final override fun initV(view: View) {
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
                if (isHeadInit()) mHead?.setStatus(this.javaClass.simpleName)
            }
            1112 -> { //ws
                try {
                    F.s = obj as Int
                    Log.i("状态", F.s.toString())
                    if (isHeadInit()) {
                        mHead?.setStatus(this.javaClass.simpleName)
                        changeSate(F.s)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
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
        super.onDestroy()
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        mHead = Head(context)
        mHead.canGoBack()
        mHead.setStatus(this.javaClass.simpleName)
        actionBar?.addView(
            mHead,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        Log.i("赋值状态", F.s.toString())
        changeSate(F.s)

    }

    fun changeSate(s: Int) {
        when (s) {
            WsStatus.CONNECTED -> {
                mHead.mImageView.setImageResource(R.drawable.u1844)
                mHead.mTextView_d_status.text = getString(R.string.d_connect)
            }
            else -> {
                mHead.mImageView.setImageResource(R.drawable.u1814)
                mHead.mTextView_d_status.text = getString(R.string.d_no_connect)
            }
        }

    }


}
