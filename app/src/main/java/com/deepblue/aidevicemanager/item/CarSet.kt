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
import cn.qqtheme.framework.picker.TimePicker
import cn.qqtheme.framework.util.ConvertUtils
import com.deepblue.aidevicemanager.R
import com.mdx.framework.activity.BaseActivity
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_car_set.view.*
import java.util.*


class CarSet(context: Context?) : LinearLayout(context) {

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.frg_car_set, this)

        mLinearLayout_time.setOnClickListener {
            onTimePicker()
        }
    }


    fun set(item: String) {
    }

    fun onTimePicker() {
        val picker = TimePicker(context as BaseActivity, TimePicker.HOUR_24)
        picker.setUseWeight(false)
        picker.setCycleDisable(false)
        picker.setRangeStart(0, 0)//00:00
        picker.setRangeEnd(23, 59)//23:59
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
        picker.setSelectedItem(currentHour, currentMinute)
        picker.setTopLineVisible(false)
        picker.setTextPadding(ConvertUtils.toPx(context, 15f))
        picker.setOnTimePickListener { hour, minute -> Helper.toast("$hour:$minute") }
        picker.show()
    }
}