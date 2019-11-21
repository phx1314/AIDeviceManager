//
//  CgqManage
//
//  Created by 86139 on 2019-11-21 13:25:58
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import com.deepblue.aidevicemanager.R

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import android.view.View
import android.widget.GridView
import android.widget.LinearLayout


class CgqManage(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_cgq_manage, this)
    }

    fun set(item: String) {

    }

}