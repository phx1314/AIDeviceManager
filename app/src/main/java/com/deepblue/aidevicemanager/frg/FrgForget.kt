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
import android.os.Handler
import android.text.TextUtils
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.util.DesEncryptDecrypt
import com.deepblue.aidevicemanager.util.PhoneFormatCheckUtils
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_forget.*


class FrgForget : BaseFrg() {
    var times = 60
    var mhandler: Handler? = null
    var runnable: Runnable? = null
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_forget)
    }

    override fun initView() {
        mTextView_get.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_phone.getText().toString())) {
                Helper.toast(getString(R.string.i_phone))
                return@setOnClickListener
            }
            if (mEditText_phone.getText().toString().length != 11) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            if (!PhoneFormatCheckUtils.isPhoneLegal(mEditText_phone.getText().toString().trim())) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            load(gB().sendSms(mEditText_phone.getText().toString()), "sendSms")

        }
        mImageView_clear.setOnClickListener {
            mEditText_phone.text.clear()
        }
        mTextView_yz.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_phone.getText().toString())) {
                Helper.toast(getString(R.string.i_phone))
                return@setOnClickListener
            }
            if (mEditText_phone.getText().toString().trim().length != 11) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            if (!PhoneFormatCheckUtils.isPhoneLegal(mEditText_phone.getText().toString().trim())) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(mEditText_code.text.toString())) {
                Helper.toast(getString(R.string.i_yzm))
                return@setOnClickListener
            }
            Helper.startActivity(context, FrgInputNew::class.java, TitleAct::class.java, "mobile", mEditText_phone.getText().toString(), "smsCode", mEditText_code.text.toString())
        }
    }

    fun doTimer() {
        mhandler = Handler()
        runnable = Runnable {
            if (times > 0) {
                times--
                mTextView_get.text = times.toString() + "s"
                mTextView_get.isClickable = false
                handler.postDelayed(runnable, 1000)
            } else if (times == 0) {
                mTextView_get.isClickable = true
                mTextView_get.text = "获取验证码"
            }
        }
        handler.post(runnable)
    }

    override fun loaddata() {
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("sendSms")) {
            doTimer()
        }
    }

    override fun onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()
    }
}