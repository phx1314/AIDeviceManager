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
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.mdx.framework.Frame
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_info_change.*
import kotlinx.android.synthetic.main.frg_input_new.mImageView_kan2
import kotlinx.android.synthetic.main.frg_mima_change.*
import kotlinx.android.synthetic.main.frg_mima_change.mTextView_cancel
import kotlinx.android.synthetic.main.frg_mima_change.mTextView_save


class FrgMimaChange : BaseFrg() {
    var isChecked = true
    var isChecked2 = true
    var isChecked3 = true

    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_mima_change)
    }

    override fun initView() {
        mTextView_cancel.setOnClickListener { Frame.HANDLES.sentAll("FrgMain", 0, null) }
        mImageView_kan1.setOnClickListener {
            if (isChecked) {
                // 显示密码
                mEditText_pass1.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                mEditText_pass1.setSelection(mEditText_pass1.text.length)
                mImageView_kan1.setImageResource(R.drawable.mima2)
            } else {
                // 隐藏密码
                mEditText_pass1.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                mEditText_pass1.setSelection(mEditText_pass1.text.length)
                mImageView_kan1.setImageResource(R.drawable.mima1)
            }
            isChecked = !isChecked
        }
        mImageView_kan2.setOnClickListener {
            if (isChecked2) {
                // 显示密码
                mEditText_pass2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                mEditText_pass2.setSelection(mEditText_pass2.text.length)
                mImageView_kan2.setImageResource(R.drawable.mima2)
            } else {
                // 隐藏密码
                mEditText_pass2.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                mEditText_pass2.setSelection(mEditText_pass2.text.length)
                mImageView_kan2.setImageResource(R.drawable.mima1)
            }
            isChecked2 = !isChecked2
        }
        mImageView_kan3.setOnClickListener {
            if (isChecked3) {
                // 显示密码
                mEditText_pass3.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                mEditText_pass3.setSelection(mEditText_pass3.text.length)
                mImageView_kan3.setImageResource(R.drawable.mima2)
            } else {
                // 隐藏密码
                mEditText_pass3.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                mEditText_pass3.setSelection(mEditText_pass3.text.length)
                mImageView_kan3.setImageResource(R.drawable.mima1)
            }
            isChecked3 = !isChecked3
        }

        mTextView_save.setOnClickListener {
            if (TextUtils.isEmpty(mEditText_pass1.text.toString())) {
                Helper.toast("请输入旧密码")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(mEditText_pass2.text.toString())) {
                Helper.toast("请输入新密码")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(mEditText_pass3.text.toString())) {
                Helper.toast("请确认输入新密码")
                return@setOnClickListener
            }
            if (mEditText_pass2.text.toString() != mEditText_pass3.text.toString()) {
                Helper.toast("两次新密码输入不一致")
                return@setOnClickListener
            }
            if (mEditText_pass2.text.toString().length < 6) {
                Helper.toast("请输入至少六位数密码")
                return@setOnClickListener
            }
            load(
                F.gB().modifyPassword(
                    mEditText_pass1.text.toString(),
                    mEditText_pass2.text.toString()
                ), "modifyPassword"
            )
        }
    }


    override fun loaddata() {
    }

    override fun onSuccess(data: String?, method: String) {
        Helper.toast(getString(R.string.i_sucess))
        F.logOut(context)
    }
}