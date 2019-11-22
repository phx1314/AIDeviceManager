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
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaMain
import com.deepblue.aidevicemanager.model.ModelMain
import com.deepblue.aidevicemanager.model.Modellogin
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frg_login.*
import kotlinx.android.synthetic.main.frg_main.*
import timber.log.Timber
import com.google.gson.reflect.TypeToken
import android.R.attr.data
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper


class FrgMain : BaseFrg() {
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_main)
    }

    override fun initView() {
    }


    override fun loaddata() {
        mTextView_gs.text = F.mModellogin?.merchant?.contactName + " >"

        load(gB().queryDeviceSeriesList(), "queryDeviceSeriesList")
    }

    override fun onSuccess(data: String?, method: String) {
        var data:Array<ModelMain> = F.data2Model(data, Array<ModelMain>::class.java)
        mMGridView.adapter = AdaMain(context, data.toMutableList())
    }
}