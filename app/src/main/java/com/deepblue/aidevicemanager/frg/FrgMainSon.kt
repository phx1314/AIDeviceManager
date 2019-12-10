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
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaMain
import com.deepblue.aidevicemanager.model.ModelMain
import kotlinx.android.synthetic.main.frg_main_son.*


class FrgMainSon : BaseFrg() {
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_main_son)
    }

    override fun initView() {
    }


    override fun loaddata() {
        load(F.gB().queryDeviceSeriesList(), "queryDeviceSeriesList")

//        mMGridView.adapter = AdaMain(context, arrayOf(ModelMain(), ModelMain()).toMutableList())
    }

    override fun onSuccess(data: String?, method: String) {
        var data: Array<ModelMain>? = F.data2Model(data, Array<ModelMain>::class.java)
        mMGridView.adapter = AdaMain(context, data!!.toMutableList())
    }
}