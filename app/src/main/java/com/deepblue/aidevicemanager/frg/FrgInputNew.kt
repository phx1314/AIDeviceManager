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
import android.text.InputType
import android.text.TextUtils
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.R
import com.mdx.framework.Frame
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_input_new.*
import kotlinx.android.synthetic.main.frg_input_new.mEditText_pass
import kotlinx.android.synthetic.main.frg_input_new.mImageView_kan
import kotlinx.android.synthetic.main.frg_login.*


class FrgInputNew : BaseFrg() {
    lateinit var mobile: String
    lateinit var smsCode: String
    var isChecked = true
    var isChecked2 = true
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_input_new)
        mobile = activity?.intent?.getStringExtra("mobile") ?: ""
        smsCode = activity?.intent?.getStringExtra("smsCode") ?: ""
    }

    override fun initView() {
        mTextView_yz.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_pass.text.toString())) {
                Helper.toast("请输入密码")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(mEditText_pass_2.text.toString())) {
                Helper.toast("请确认输入新密码")
                return@setOnClickListener
            }
            if (mEditText_pass.text.toString() != mEditText_pass_2.text.toString()) {
                Helper.toast("两次密码输入不一致")
                return@setOnClickListener
            }
            if (mEditText_pass.text.toString().length < 6) {
                Helper.toast("请输入至少六位数密码")
                return@setOnClickListener
            }
            load(gB().forgetPassword(mEditText_pass.text.toString(), mobile, smsCode), "forgetPassword")
        }

        mImageView_kan.setOnClickListener {
            if (isChecked) {
                // 显示密码
                mEditText_pass.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                mEditText_pass.setSelection(mEditText_pass.text.length)
                mImageView_kan.setImageResource(R.drawable.mima2)
            } else {
                // 隐藏密码
                mEditText_pass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                mEditText_pass.setSelection(mEditText_pass.text.length)
                mImageView_kan.setImageResource(R.drawable.mima1)
            }
            isChecked = !isChecked
        }
        mImageView_kan2.setOnClickListener {
            if (isChecked2) {
                // 显示密码
                mEditText_pass_2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                mEditText_pass_2.setSelection(mEditText_pass.text.length)
                mImageView_kan2.setImageResource(R.drawable.mima2)
            } else {
                // 隐藏密码
                mEditText_pass_2.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                mEditText_pass_2.setSelection(mEditText_pass.text.length)
                mImageView_kan2.setImageResource(R.drawable.mima1)
            }
            isChecked2 = !isChecked2
        }
    }


    override fun loaddata() {
    }

    override fun onSuccess(data: String?, method: String) {
        Helper.toast("设置成功")
        Frame.HANDLES.close("FrgForget")
        finish()
    }
}