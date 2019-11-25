//
//  EpOne
//
//  Created by 86139 on 2019-11-20 15:47:07
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.item_ep_one.view.*


class EpTwo(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_ep_two, this)
    }

    fun set(item: String) {
        mTextView.text = item
    }


}