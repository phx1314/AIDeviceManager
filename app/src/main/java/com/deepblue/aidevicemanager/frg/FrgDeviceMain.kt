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
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_device_main.*
import com.deepblue.aidevicemanager.ada.AdaMain
import android.R.attr.data
import java.nio.file.Files.size
import com.deepblue.aidevicemanager.model.ModelMain.DeviceListBean
import com.mdx.framework.adapter.MAdapter
import com.mdx.framework.view.listener.AbOnListViewListener
import android.R.attr.data
import android.R.attr.data
import java.nio.file.Files.size


class FrgDeviceMain : BaseFrg() {
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_device_main)
    }

    override fun initView() {
    }


    override fun loaddata() {
        mAbPullListView.setApiLoadParams(
            "https://iot.pettime.info:8443/mobile?methodno=MSchDevice&pageNumber=1&pageSize=20&verify=74d932821c724dad8db95a91ec7b74af&device=andriod&deviceid=ERMDU17629001416&userid=a9844d99877a49c284aa926a6bb7e146&md5=de3b9f2f889505d730b19d8c146017d4",
            "GET",
            this,
            ""
        )
        mAbPullListView.setAbOnListViewListener(object : AbOnListViewListener {
            override fun onSuccess(methodName: String, content: String): MAdapter<*> {

                return AdaMain(context, null)
            }
        })
        mTextView_gs?.text = F.mModellogin?.merchant?.contactName + " >"
    }

}