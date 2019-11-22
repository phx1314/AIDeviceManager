//
//  Main
//
//  Created by 86139 on 2019-11-22 15:09:45
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
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.model.ModelMain
import kotlinx.android.synthetic.main.item_main.view.*


class Main(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_main, this)
    }

    fun set(item: ModelMain) {
        mTextView_title.setText(item.seriesName)
        mTextView_num.setText(item.deviceNum.toInt().toString())
    }


}