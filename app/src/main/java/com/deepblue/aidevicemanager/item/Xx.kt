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
import com.deepblue.aidevicemanager.model.ModelBrokenXx
import com.deepblue.aidevicemanager.model.ModelMain
import com.deepblue.aidevicemanager.model.ModelTaskXx
import com.deepblue.aidevicemanager.model.ModelWaringXx
import kotlinx.android.synthetic.main.item_main.view.*
import kotlinx.android.synthetic.main.item_xx.view.*


class Xx(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_xx, this)
    }

    fun set(item: Any) {
        if (item is ModelTaskXx.RowsBean) {
            mTextView_1.text = item.id.toInt().toString()
            mTextView_2.text = item.createTime.toString()
            mTextView_3.text = item.taskName.toString()
            mTextView_4.text = item.taskLoopName + item.taskTypeName
        } else if (item is ModelWaringXx.RowsBean) {
            mTextView_1.text = item.alarmRecordId.toString()
            mTextView_2.text = item.alarmTime.toString()
            mTextView_3.text = item.alarmTypeChildName.toString()
            mTextView_4.text = item.alarmTypeParentName
        } else if (item is ModelBrokenXx.RowsBean) {
            mTextView_1.text = item.seriesCode
            mTextView_2.text = item.breakdownTime.toString()
            mTextView_3.text = item.breakdownDesc.toString()
            mTextView_4.text = item.investigationSuggestion
        }
    }


}