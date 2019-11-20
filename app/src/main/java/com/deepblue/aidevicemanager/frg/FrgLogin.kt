//
//  FrgLogin
//
//  Created by DELL on 2019-10-15 13:14:05
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import com.mdx.framework.view.Headlayout
import android.view.View
import com.deepblue.aidevicemanager.R
import com.mdx.framework.permissions.PermissionRequest
import com.mdx.framework.util.Helper
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber


class FrgLogin : BaseFrg() {
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
                    Timber.w("")

                }
            })
    }


    override fun loaddata() {
    }

}