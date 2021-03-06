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
import android.text.InputType.*
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import cn.jpush.android.api.JPushInterface
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.data2Model
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.F.mModellogin
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelLogin
import com.deepblue.aidevicemanager.util.DesEncryptDecrypt
import com.deepblue.aidevicemanager.util.PhoneFormatCheckUtils
import com.mdx.framework.Frame
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.utility.Helper
import com.mdx.framework.utility.permissions.PermissionRequest
import kotlinx.android.synthetic.main.frg_login.*
import kotlinx.android.synthetic.main.item_head.view.*


class FrgLogin : BaseFrg() {
    //    18151735217
    //    123456
//    15510255253 12345678
    var isChecked = true

    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_login)
        Frame.HANDLES.closeWidthOut("FrgLogin")
    }

    override fun initView() {

        Helper.requestPermissions(arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ), object : PermissionRequest() {
            override fun onGrant(var1: Array<out String>?, var2: IntArray?) {

            }
        })

        mImageView_kan.setOnClickListener {
            if (isChecked) {
                // 显示密码
                mEditText_pass.setInputType(TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                mEditText_pass.setSelection(mEditText_pass.text.length)
                mImageView_kan.setImageResource(R.drawable.mima2)
            } else {
                // 隐藏密码
                mEditText_pass.setInputType(TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD)
                mEditText_pass.setSelection(mEditText_pass.text.length)
                mImageView_kan.setImageResource(R.drawable.mima1)
            }
            isChecked = !isChecked
        }
        mTextView.setOnClickListener(Helper.delayClickLitener {
            if (TextUtils.isEmpty(mEditText_phone.text.toString())) {
                Helper.toast(getString(R.string.i_phone))
                return@delayClickLitener
            }
            if (mEditText_phone.text.toString().trim().length != 11) {
                Helper.toast(getString(R.string.i_phone_length))
                return@delayClickLitener
            }
            if (!PhoneFormatCheckUtils.isPhoneLegal(mEditText_phone.text.toString().trim())) {
                Helper.toast(getString(R.string.i_phone_length))
                return@delayClickLitener
            }
            if (TextUtils.isEmpty(mEditText_pass.text.toString())) {
                Helper.toast(getString(R.string.i_porc_length))
                return@delayClickLitener
            }
            load(
                gB().login(
                    mEditText_phone.getText().toString(),
                    DesEncryptDecrypt.getInstance().encrypt(mEditText_pass.getText().toString()),
                    ""
                ), "login"
            )
        })
        mTextView_forget.setOnClickListener(Helper.delayClickLitener {
            Helper.startActivity(
                context,
                FrgForget::class.java,
                TitleAct::class.java
            )
        })
    }


    override fun loaddata() {
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("login")) {
            mModellogin = data2Model(data, ModelLogin::class.java)
            F.saveJson("mModellogin", data)
            Helper.startActivity(context, FrgMain::class.java, IndexAct::class.java)
            JPushInterface.resumePush(activity!!)
            JPushInterface.setAlias(
                activity!!,
                mModellogin?.merchant?.id?.toInt().toString()
            ) { code, s, set ->
                when (code) {
                    0 -> Log.i("JPush", "设置别名成功")
                    6002 -> Log.i("JPush", "失败,错误码= $code")
                    else -> Log.i("JPush", "失败,错误码= $code")
                }
                this@FrgLogin.finish()
            }
        }
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        mHead.mImageView_logo.visibility = View.GONE
        mHead.canGoBack(false)
    }
}