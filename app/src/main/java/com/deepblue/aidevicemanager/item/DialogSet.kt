//
//  DialogSet
//
//  Created by 86139 on 2019-11-21 10:03:46
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R


class DialogSet(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_dialog_set, this)
    }

    fun set(item: String) {

    }
}