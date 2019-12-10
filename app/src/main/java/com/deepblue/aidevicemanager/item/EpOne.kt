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
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.item_ep_one.view.*


class EpOne(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_ep_one, this)
    }

    fun set(item: String, isExpanded: Boolean, groupPosition: Int, checked: Boolean) {
        mTextView.text = item
        if (groupPosition > 1) mImageView_fx.visibility = View.GONE else mImageView_fx.visibility = View.VISIBLE
        if (isExpanded) mImageView_fx.setBackgroundResource(R.drawable.ic_expand_more_black_24dp) else mImageView_fx.setBackgroundResource(R.drawable.ic_expand_more_fan)
        if (checked) mTextView.setTextColor(Color.parseColor("#ffffff")) else mTextView.setTextColor(Color.parseColor("#888888"))
    }


}