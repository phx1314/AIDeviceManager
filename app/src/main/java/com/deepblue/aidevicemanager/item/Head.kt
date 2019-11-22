//
//  Head
//
//  Created by 86139 on 2019-11-22 08:25:55
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
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class Head(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_head, this)


    }

    fun set(item: String) {
    }

}