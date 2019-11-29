//
//  BaseItem
//
//  Created by 86139 on 2019-11-20 15:47:07
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.app.ProgressDialog
import android.content.Context
import android.os.Message
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.frg.BaseFrg
import com.mdx.framework.Frame
import com.mdx.framework.activity.BaseActivity
import com.mdx.framework.handle.MHandler
import com.mdx.framework.service.subscriber.HttpResult
import com.mdx.framework.service.subscriber.HttpResultSubscriberListener
import com.mdx.framework.service.subscriber.S
import com.mdx.framework.util.AbAppUtil
import com.mdx.framework.util.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseItem(context: Context?) : LinearLayout(context), HttpResultSubscriberListener {

    init {
        var handler = MHandler()
        val className = this.javaClass.simpleName
        handler.setId(className)
        handler.setMsglisnener { msg ->
            when (msg.what) {
                201 -> this@BaseItem.disposeMsg(msg.arg1, msg.obj)
            }
        }
        if (Frame.HANDLES.get(className).size > 0) {
            Frame.HANDLES.get(className).forEach {
                Frame.HANDLES.remove(it)
            }
        }
        Frame.HANDLES.add(handler)
    }

    open fun disposeMsg(type: Int, obj: Any) {}

    override fun onError(code: String?, msg: String?) {
        if (code != null && (code == "0020" || code == "0021" || code == "0022")) {
            F.logOut(context)
        }
    }


    override fun onSuccess(data: String?, method: String) {
    }

    fun <T> load(o: Observable<HttpResult<T>>, m: String, isShow: Boolean = true) {
        var s = S<T>(
            this,
            ProgressDialog(context).apply { this.setMessage(context.resources.getString(R.string.loading)) },
            m,
            isShow
        )
        ((context as BaseActivity).mFragment as BaseFrg).compositeDisposable.add(s)
        if (!AbAppUtil.isNetworkAvailable(Frame.CONTEXT)) {
            Helper.toast(context.resources.getString(R.string.net_error))
        }
        o.subscribeOn(Schedulers.newThread()).unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (s.isShow) s.mProgressDialog.show() }
            .doFinally { if (s.mProgressDialog.isShowing) s.mProgressDialog.dismiss() }
            .subscribe(s)
    }
}

