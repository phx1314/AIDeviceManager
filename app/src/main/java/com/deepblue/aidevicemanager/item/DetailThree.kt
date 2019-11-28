//
//  DetailTwo
//
//  Created by 86139 on 2019-11-20 19:06:06
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


class DetailThree(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_detail_three, this)
    }

    fun set(item: String) {

    }
}