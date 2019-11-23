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
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaMain
import com.deepblue.aidevicemanager.model.ModelMain
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_main.*


class FrgMain : BaseFrg() {
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_main)
    }

    override fun initView() {
    }


    override fun loaddata() {
        mTextView_gs.text = F.mModellogin?.merchant?.contactName + " >"
//        mMGridView.adapter = AdaMain(context, List<ModelMain>(5) { ModelMain() })
        load(gB().queryDeviceSeriesList(), "queryDeviceSeriesList")
    }

    override fun onSuccess(data: String?, method: String) {
        var data: Array<ModelMain> = F.data2Model(data, Array<ModelMain>::class.java)
        mMGridView.adapter = AdaMain(context, data.toMutableList())
    }
}