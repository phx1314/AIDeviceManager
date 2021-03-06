//
//  Main
//
//  Created by 86139 on 2019-11-22 15:09:45
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelBrokenXx
import com.deepblue.aidevicemanager.model.ModelTaskXx
import kotlinx.android.synthetic.main.item_xx.view.*


class Xx(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_xx, this)
    }

    fun set(item: Any) {
        if (item is ModelTaskXx.PageInfoBean.RowsBean) {
            mTextView_1.text = item.deviceNames
            mTextView_2.text = item.createTime.toString()
            mTextView_3.text = item.taskName.toString()
            mTextView_4.text = item.taskLoopName + item.taskTypeName
            setRead(item.isRead?.equals("0") ?: true)
        } else if (item is ModelBrokenXx.PageInfoBean.RowsBean) {
            mTextView_1.text = item.deviceName
            mTextView_2.text = item.breakdownTime.toString()
            mTextView_3.text = item.breakdownDesc.toString()
            mTextView_4.text = item.investigationSuggestion
            setRead(item.isRead?.equals("0") ?: true)
        }
    }

    fun setRead(isRead: Boolean) {
        if (isRead) mTextView_1.setTextColor(resources.getColor(R.color.black)) else mTextView_1.setTextColor(
            resources.getColor(R.color.gray)
        )
        if (isRead) mTextView_2.setTextColor(resources.getColor(R.color.black)) else mTextView_2.setTextColor(
            resources.getColor(R.color.gray)
        )
        if (isRead) mTextView_3.setTextColor(resources.getColor(R.color.black)) else mTextView_3.setTextColor(
            resources.getColor(R.color.gray)
        )
        if (isRead) mTextView_4.setTextColor(resources.getColor(R.color.black)) else mTextView_4.setTextColor(
            resources.getColor(R.color.gray)
        )

    }


}