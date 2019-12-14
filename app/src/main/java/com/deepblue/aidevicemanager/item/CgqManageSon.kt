//
//  CgqManageSon
//
//  Created by 86139 on 2019-11-21 13:27:24
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.item_cgq_manage_son.view.*


class CgqManageSon(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_cgq_manage_son, this)
    }

    fun set(item: Character, start: String, position: Int) {
        mTextView_key.text = start + (position+1)
        mTextView_value.text = getRightS2(item.toString())
    }


}