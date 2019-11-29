//
//  Dian
//
//  Created by DELL on 2019-10-15 13:05:56
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelB
import kotlinx.android.synthetic.main.frg_dc.view.*


class Dc(context: Context?) : LinearLayout(context) {

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.frg_dc, this)
    }


    fun set(item: ModelB) {
        mTextView_dc.text = item.batteryRemaining ?: "N/A"
        mTextView_dy.text = item.batteryVoltage ?: "N/A"
        mTextView_dl.text = item.batteryCurrent ?: "N/A"
        mTextView_wd.text = item.batteryTemperature ?: "N/A"
        mTextView_dliang.text = item.batteryRemaining ?: "N/A"
        mTextView_dcrl.text = item.batteryCapacity ?: "N/A"
        mTextView_xhcsh.text = item.batteryCycle ?: "N/A"
        mTextView_dccode.text = item.batterySeriesNumber ?: "N/A"
    }

}