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
import com.deepblue.aidevicemanager.model.ModelCarSet
import com.mdx.framework.activity.BaseActivity
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_car_set.view.*
import kotlinx.android.synthetic.main.item_car_set_son.view.*
import java.util.*


class CarSetSon(context: Context?) : LinearLayout(context) {

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_car_set_son, this)

    }


    fun set(item: ModelCarSet) {
        if (item.paramShowName.equals("定时启动")) {
            mLinearLayout_1.visibility = View.GONE
            mLinearLayout_2.visibility = View.VISIBLE
        } else {
            mLinearLayout_1.visibility = View.VISIBLE
            mLinearLayout_2.visibility = View.GONE
            mTextView_name.text = item.paramShowName
            mTextView_dw.text = item.paramUnit
            mEditText_1.setText(item.rpParamValue)
        }
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