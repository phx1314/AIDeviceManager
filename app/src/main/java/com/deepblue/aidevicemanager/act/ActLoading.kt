//
//  ActLoading
//
//  Created by 86139 on 2019-11-19 14:09:05
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.act


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.deepblue.aidevicemanager.F.init
import com.deepblue.aidevicemanager.F.mModelStatus
import com.deepblue.aidevicemanager.F.mModellogin
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.frg.FrgLogin
import com.deepblue.aidevicemanager.frg.FrgMain
import com.deepblue.aidevicemanager.model.ModelStatus
import com.deepblue.aidevicemanager.service.BatteryService
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.util.Helper

class ActLoading : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_loading)
        loaddata()
    }


    fun loaddata() {
        mModelStatus = ModelStatus()
        startService(Intent(this, BatteryService::class.java))
        init()
        Handler().postDelayed({
            if (mModellogin == null) {
                Helper.startActivity(this, FrgLogin::class.java, IndexAct::class.java)
            } else {
                Helper.startActivity(this, FrgMain::class.java, IndexAct::class.java)
            }
            //finish()
        }, 3000)
    }

}
