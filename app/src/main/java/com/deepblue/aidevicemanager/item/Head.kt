//
//  Head
//
//  Created by 86139 on 2019-11-22 08:25:55
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
import com.deepblue.aidevicemanager.pop.PopShowSet
import com.mdx.framework.Frame
import com.mdx.framework.activity.BaseActivity
import kotlinx.android.synthetic.main.item_head.view.*


class Head(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_head, this)

        mImageView_logo.setOnClickListener {
            Frame.HANDLES.closeWidthOut("FrgMain,FrgMainSon,FrgInfoChange,FrgMimaChange,FrgXx,FrgWebView")
            Frame.HANDLES.sentAll("FrgMain", 0, null)
        }
    }

    fun set(item: String) {
    }

    fun setShowPop(view: View) {
        mImageButton_set.visibility = View.VISIBLE
        mImageButton_set.setOnClickListener {
            var mPopShowSet = PopShowSet(getContext(), mImageButton_set, view)
            view.tag = mPopShowSet
            mPopShowSet.show()
        }
    }

    fun canGoBack(b: Boolean = true) {
        if (b) mImageButton_back.visibility = View.VISIBLE else mImageButton_back.visibility =
            View.GONE
        mImageButton_back.setOnClickListener {
            (context as BaseActivity).finish()
        }
    }

    fun setStatusShow() {
        mLinearLayout_status.visibility = View.VISIBLE
    }

    fun setTitle(s: String) {
        mTextView_title.visibility = View.VISIBLE
        mTextView_title.text = s
    }


}