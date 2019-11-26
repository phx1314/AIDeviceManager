//
//  DeviceMainRight
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
import android.widget.ImageView
import android.widget.TextView
import com.baidu.location.b.i
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDevices
import kotlinx.android.synthetic.main.item_device_main_right.view.*


class DeviceMainRight(context: Context?) : LinearLayout(context) {
    lateinit var item: ModelData<ModelDevices.DataBean.RowsBean>

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_device_main_right, this)
    }

    fun set(item: ModelData<ModelDevices.DataBean.RowsBean>) {
        this.item = item
        setShow(item.mList.size)
        item.mList.forEachIndexed { i, it ->
            if (i == 0) {
                mTextView_num_1.text = it.deviceName
                mImageView_status_1.background
            } else if (i == 1) {
                mTextView_num_2.text = it.deviceName
            } else if (i == 2) {
                mTextView_num_3.text = it.deviceName
            } else if (i == 3) {
                mTextView_num_4.text = it.deviceName
            }
        }
    }

    fun setShow(count: Int) {
        mLinearLayout_1.visibility = if (count > 0) View.VISIBLE else View.INVISIBLE
        mLinearLayout_2.visibility = if (count > 1) View.VISIBLE else View.INVISIBLE
        mLinearLayout_3.visibility = if (count > 2) View.VISIBLE else View.INVISIBLE
        mLinearLayout_4.visibility = if (count > 3) View.VISIBLE else View.INVISIBLE
    }

}