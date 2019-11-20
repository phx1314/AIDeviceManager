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
import android.os.Bundle


import com.deepblue.aidevicemanager.R
import com.deepblue.library.tcp.TcpClient

class ActLoading : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_loading)
        loaddata()
    }


    fun loaddata() {
    }


}
