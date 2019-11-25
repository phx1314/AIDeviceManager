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
import android.text.InputType
import android.text.TextUtils
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.data2Model
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.F.mModellogin
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelLogin
import com.deepblue.aidevicemanager.util.DesEncryptDecrypt
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.permissions.PermissionRequest
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_login.*
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
import com.deepblue.aidevicemanager.util.PhoneFormatCheckUtils


class FrgCopy : BaseFrg() {
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_login)
    }

    override fun initView() {
    }


    override fun loaddata() {
    }

    override fun onSuccess(data: String?, method: String) {
    }
}