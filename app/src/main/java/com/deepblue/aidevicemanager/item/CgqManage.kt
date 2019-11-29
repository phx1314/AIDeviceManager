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

import android.content.Context
import android.view.LayoutInflater

import android.widget.LinearLayout
import com.deepblue.aidevicemanager.model.ModelB
import kotlinx.android.synthetic.main.frg_cgq_manage.view.*


class CgqManage(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.frg_cgq_manage, this)
    }

    fun set(item: ModelB) {
        mTextView1.text = item.lidar0Status ?: "N/A"
        mTextView2.text = item.lidar1Status ?: "N/A"
        mTextView3.text = item.lidar2Status ?: "N/A"
        mTextView4.text = item.lidar3Status ?: "N/A"
        mTextView5.text = item.lidar5Status ?: "N/A"
        mTextView6.text = item.lidar6Status ?: "N/A"
        mTextView7.text = item.lidar7Status ?: "N/A"
        mTextViewl1.text = item.millimeterWave0Status ?: "N/A"
        mTextViewl2.text = item.millimeterWave1Status ?: "N/A"
        mTextViewl3.text = item.millimeterWave2Status ?: "N/A"
        mTextViewl4.text = item.millimeterWave3Status ?: "N/A"
        mTextViewl5.text = item.millimeterWave4Status ?: "N/A"
        mTextViewl6.text = item.millimeterWave5Status ?: "N/A"
        mTextViewl7.text = item.millimeterWave6Status ?: "N/A"
        mTextViewl8.text = item.millimeterWave7Status ?: "N/A"
        mTextViewc1.text = item.ultrasonicWave0Status ?: "N/A"
        mTextViewc2.text = item.ultrasonicWave1Status ?: "N/A"
        mTextViewc3.text = item.ultrasonicWave2Status ?: "N/A"
        mTextViewc4.text = item.ultrasonicWave3Status ?: "N/A"
        mTextViewc5.text = item.ultrasonicWave4Status ?: "N/A"
        mTextViewc6.text = item.ultrasonicWave5Status ?: "N/A"
        mTextViewc7.text = item.ultrasonicWave6Status ?: "N/A"
        mTextView_zed.text = item.zedStatus ?: "N/A"
//        mTextView_zed.text = item.zedStatus ?: "N/A"
    }

}