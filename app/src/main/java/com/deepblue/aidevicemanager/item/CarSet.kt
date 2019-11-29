//
//  Dian
//
//  Created by DELL on 2019-10-15 13:05:56
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item


import android.content.Context
import android.view.LayoutInflater
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaCarSetSon
import com.deepblue.aidevicemanager.model.ModelCarSet
import com.deepblue.aidevicemanager.model.ModelDeviceDetail
import com.deepblue.aidevicemanager.util.DesEncryptDecrypt
import com.google.gson.Gson
import com.mdx.framework.Frame
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_car_set.view.*


class CarSet(context: Context?) : BaseItem(context) {
    lateinit var item: ArrayList<ModelCarSet>
    lateinit var mModelDeviceDetail: ModelDeviceDetail

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.frg_car_set, this)

        mTextView_update4.setOnClickListener {
            Frame.HANDLES.sentAll("DialogSet", 0, "")
        }
        mTextView_update5.setOnClickListener {
            Frame.HANDLES.sentAll("DialogSet", 1, "")
        }
    }


    fun set(item: ArrayList<ModelCarSet>, mModelDeviceDetail: ModelDeviceDetail) {
        this.item = item
        this.mModelDeviceDetail = mModelDeviceDetail
        mListView.adapter = AdaCarSetSon(context, item)
    }
}