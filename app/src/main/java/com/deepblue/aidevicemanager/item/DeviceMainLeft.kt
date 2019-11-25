//
//  DeviceMainLeft
//
//  Created by 86139 on 2019-11-20 17:00:09
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
import android.widget.LinearLayout
import android.widget.TextView
import com.deepblue.aidevicemanager.model.ModelModels
import kotlinx.android.synthetic.main.item_device_main_left.view.*


class DeviceMainLeft(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_device_main_left, this)
    }

    fun set(item: ModelModels) {
        mTextView.text = item.modelName
    }


}