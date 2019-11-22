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
import kotlinx.android.synthetic.main.frg_main.*


class FrgDeviceMain : BaseFrg() {
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_device_main)
    }

    override fun initView() {
    }


    override fun loaddata() {
        mTextView_gs?.text = F.mModellogin?.merchant?.contactName + " >"
    }

}