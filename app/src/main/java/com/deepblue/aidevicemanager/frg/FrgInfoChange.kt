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
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.util.PhoneFormatCheckUtils
import com.google.gson.Gson
import com.mdx.framework.Frame
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_info_change.*
import kotlinx.android.synthetic.main.frg_info_change.mEditText_phone
import kotlinx.android.synthetic.main.frg_info_change.mTextView_get


class FrgInfoChange : BaseFrg() {
    var times = 60
    var mhandler: Handler? = null
    var runnable: Runnable? = null

    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_info_change)
    }

    override fun initView() {
        mEditText_user.setText(F.mModellogin?.user?.name)
        mTextView_cancel.setOnClickListener { Frame.HANDLES.sentAll("FrgMain", 0, null) }
        mTextView_yanzheng.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_phone.text.toString())) {
                Helper.toast(getString(R.string.i_phone))
                return@setOnClickListener
            }
            if (mEditText_phone.text.toString().trim().length != 11) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            if (!PhoneFormatCheckUtils.isPhoneLegal(mEditText_phone.text.toString().trim())) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            Helper.toast(getString(R.string.i_phone_right))
        }

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
            load(F.gB().sendSms(mEditText_phone.text.toString()), "sendSms")

        }
        mTextView_save.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_user.getText().toString())) {
                Helper.toast(getString(R.string.i_uname))
                return@setOnClickListener
            }
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
            if (TextUtils.isEmpty(mEditText_code.text.toString())) {
                Helper.toast(getString(R.string.i_yzm))
                return@setOnClickListener
            }
            load(
                F.gB().modifyUser(
                    mEditText_user.text.toString(),
                    mEditText_phone.text.toString(),
                    mEditText_code.text.toString()
                ), "modifyUser"
            )

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
                mTextView_get.text = getString(R.string.i_sucess)
            }
        }
        handler.post(runnable)
    }

    override fun loaddata() {
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("sendSms")) {
            doTimer()
        } else if (method.equals("modifyUser")) {
            Helper.toast(getString(R.string.i_sucess))
            F.mModellogin?.user?.name = mEditText_user.text.toString()
            F.saveJson("mModellogin", Gson().toJson(F.mModellogin))
            Frame.HANDLES.sentAll("FrgMain", 1, null)
        }
    }

    override fun onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()
    }
}