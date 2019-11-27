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
import com.deepblue.aidevicemanager.pop.PopShowSet
import kotlinx.android.synthetic.main.item_dialog_set.view.*


class DialogSet(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_dialog_set, this)
        mImageButton_dc.setOnClickListener {
            mLinearLayout_content_son.removeAllViewsInLayout()
            mLinearLayout_content_son.addView(Dc(context))
        }
        mImageButton_cgq.setOnClickListener {
            mLinearLayout_content_son.removeAllViewsInLayout()
            mLinearLayout_content_son.addView(CgqManage(context))
        }
        mImageButton_set.setOnClickListener {
            mLinearLayout_content_son.removeAllViewsInLayout()
            mLinearLayout_content_son.addView(CarSet(context))
        }
        mLinearLayout_content_son.removeAllViewsInLayout()
        mLinearLayout_content_son.addView(Dc(context))

        mImageButton_close.setOnClickListener {
            (this@DialogSet.getTag() as PopShowSet).hide()
        }
    }

    fun set(item: String) {

    }

//    fun chageFrgment(fragment: Fragment) {
//        val transaction = mFragmentManager.beginTransaction()
//        transaction.replace(R.id.mLinearLayout_content_son, fragment)
//        transaction.commit()
//    }

}