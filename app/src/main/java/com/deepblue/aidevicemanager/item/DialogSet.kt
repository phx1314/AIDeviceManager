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
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelCarSet
import com.deepblue.aidevicemanager.model.ModelModels
import com.deepblue.aidevicemanager.pop.PopShowSet
import kotlinx.android.synthetic.main.item_dialog_set.view.*
import timber.log.Timber


class DialogSet(context: Context?) : BaseItem(context) {
    lateinit var mModelCarSets: Array<ModelCarSet>
    var mDc: Dc
    var mCgqManage: CgqManage
    var mCarSet: CarSet

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_dialog_set, this)
        mDc = Dc(context)
        mCgqManage = CgqManage(context)
        mCarSet = CarSet(context)
        mImageButton_dc.setOnClickListener {
            mLinearLayout_content_son.removeAllViewsInLayout()
            mLinearLayout_content_son.addView(mDc)
        }
        mImageButton_cgq.setOnClickListener {
            mLinearLayout_content_son.removeAllViewsInLayout()
            mLinearLayout_content_son.addView(mCgqManage)
        }
        mImageButton_set.setOnClickListener {
            mLinearLayout_content_son.removeAllViewsInLayout()
            mLinearLayout_content_son.addView(mCarSet)
        }
        mLinearLayout_content_son.removeAllViewsInLayout()
        mLinearLayout_content_son.addView(mDc)

        mImageButton_close.setOnClickListener {
            (this@DialogSet.getTag() as PopShowSet).hide()
        }

        load(F.gB().queryDeviceParamList("362"), "queryDeviceParamList")
        load(F.gB().queryDeviceLiveData("362"), "queryDeviceLiveData")
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryDeviceParamList")) {
            var mModelCarSets = F.data2Model(data, Array<ModelCarSet>::class.java)
            mCarSet.set(mModelCarSets)
        } else if (method.equals("queryDeviceLiveData")) {
            var mModelB = F.data2Model(data, ModelB::class.java)
            mDc.set(mModelB)
            mCgqManage.set(mModelB)
        }
    }

    fun set(item: String) {

    }


}