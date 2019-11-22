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
import com.deepblue.aidevicemanager.F.data2Model
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.F.mModellogin
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.Modellogin
import com.deepblue.aidevicemanager.util.DesEncryptDecrypt
import com.google.gson.Gson
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.permissions.PermissionRequest
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_login.*


class FrgLogin : BaseFrg() {

    var times = 60
    var mhandler: Handler? = null
    var runnable: Runnable? = null
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_login)
    }

    override fun initView() {
        Helper.requestPermissions(
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), object : PermissionRequest() {
                override fun onGrant(var1: Array<out String>?, var2: IntArray?) {

                }
            })
        mTextView_get.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_phone.getText().toString())) {
                Helper.toast(getString(R.string.i_phone))
                return@setOnClickListener
            }
            if (mEditText_phone.getText().toString().length != 11) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            load(
                gB().sendSms(
                    mEditText_phone.getText().toString()
                ), "sendSms"
            )

        }
        mTextView.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_phone.getText().toString())) {
                Helper.toast(getString(R.string.i_phone))
                return@setOnClickListener
            }
            if (mEditText_phone.getText().toString().length != 11) {
                Helper.toast(getString(R.string.i_phone_length))
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(mEditText_code.getText().toString()) && TextUtils.isEmpty(
                    mEditText_pass.getText().toString()
                )
            ) {
                Helper.toast(getString(R.string.i_porc_length))
                return@setOnClickListener
            }
            load(
                gB().login(
                    mEditText_phone.getText().toString(),
                    DesEncryptDecrypt.getInstance().encrypt(mEditText_pass.getText().toString()),
                    mEditText_code.getText().toString()
                ), "login"
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
        } else if (method.equals("login")) {
            mModellogin = data2Model(data, Modellogin::class.java)
            F.saveJson("mModellogin", data)
            Helper.startActivity(context, FrgMain::class.java, IndexAct::class.java)
            finish()
        }
    }
}