//
//  CgqManageSon
//
//  Created by 86139 on 2019-11-21 13:27:24
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R


class CgqManageSon2(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_cgq_manage_son2, this)
    }

    fun set(item: String) {

    }


}