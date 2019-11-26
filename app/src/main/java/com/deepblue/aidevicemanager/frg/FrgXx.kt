//
//  FrgLogin
//
//  Created by DELL on 2019-10-15 13:14:05
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.view.View
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaXx
import kotlinx.android.synthetic.main.frg_xx.*


class FrgXx : BaseFrg() {
    var type: Int = 0
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_xx)
    }

    override fun initView() {
        type = arguments?.getInt("type") ?: 0
        chageType(type)

        mLinearLayout_1.setOnClickListener { chageType(0) }
        mLinearLayout_2.setOnClickListener { chageType(1) }
        mLinearLayout_3.setOnClickListener { chageType(2) }
    }

    fun chageType(type: Int) {
        when (type) {
            0 -> {
                mImageView_1.visibility = View.VISIBLE
                mImageView_2.visibility = View.INVISIBLE
                mImageView_3.visibility = View.INVISIBLE
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}task/queryTaskListWithPage",
                    "POST",
                    this,
                    F.mModellogin?.token
                )
            }
            1 -> {
                mImageView_1.visibility = View.INVISIBLE
                mImageView_2.visibility = View.VISIBLE
                mImageView_3.visibility = View.INVISIBLE
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}task/queryAlarmInfos",
                    "POST",
                    this,
                    F.mModellogin?.token
                )
            }
            2 -> {
                mImageView_1.visibility = View.INVISIBLE
                mImageView_2.visibility = View.INVISIBLE
                mImageView_3.visibility = View.VISIBLE
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}task/queryBreakdowns",
                    "POST",
                    this,
                    F.mModellogin?.token
                )
            }
        }
    }

    override fun loaddata() {
        mAbPullListView.setAbOnListViewListener { _, content ->
            AdaXx(context, arrayOf("aa").toMutableList())
        }
    }

    override fun onSuccess(data: String?, method: String) {
    }
}