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



class Dian(context: Context?) : LinearLayout(context) {

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_dian, this)
    }


    fun set(item: String) {
    }

}