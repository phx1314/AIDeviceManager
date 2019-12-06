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
import android.text.TextUtils
import android.view.LayoutInflater
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.bean.BeanParam1
import com.deepblue.aidevicemanager.bean.BeanParam2
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelCarSet
import com.deepblue.aidevicemanager.model.ModelDevices
import com.deepblue.aidevicemanager.pop.PopShowSet
import com.google.gson.Gson
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.item_dialog_set.view.*


class DialogSet(context: Context?, var mdata: ModelDevices.RowsBean) : BaseItem(context) {
    var mModelCarSets_new = ArrayList<ModelCarSet>()
    var mDc: Dc
    var mCgqManage: CgqManage
    var mCarSet: CarSet
    val carsetPZ = arrayOf(
        resources.getString(R.string.d_spkd),
        resources.getString(R.string.d_qscs),
        resources.getString(R.string.d_dsqd),
        resources.getString(R.string.d_vcu),
        resources.getString(R.string.d_sfbb),
        resources.getString(R.string.d_rjbb)
    )

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

        load(F.gB().queryDeviceParamList(mdata.id.toString()), "queryDeviceParamList")
    }

    fun set(mModelB: ModelB) {
        mDc.set(mModelB)
        mCgqManage.set(mModelB)
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryDeviceParamList")) {
            var mModelCarSets = F.data2Model(data, Array<ModelCarSet>::class.java)
            mModelCarSets_new = ArrayList<ModelCarSet>()
            mModelCarSets?.forEach {
                if (carsetPZ.contains(it.paramShowName)) {
                    mModelCarSets_new.add(it)
                }
                if (it.paramShowName.equals(resources.getString(R.string.d_ddlbj)) || it.paramShowName.equals(
                        resources.getString(R.string.d_yzddlbj)
                    )
                ) {
                    mDc.set(it)
                }
            }
            mCarSet.set(mModelCarSets_new)
        } else if (method.equals("configDeviceParamBatch")) {
            Helper.toast("同步成功")
            load(
                F.gB().queryDeviceParamList(mdata.id.toString()),
                "queryDeviceParamList"
            )
        }
    }

    fun set(item: String) {

    }

    override fun disposeMsg(type: Int, obj: Any) {
        when (type) {
            0 -> {
                var data = ArrayList<Any>()
                mModelCarSets_new.forEach {
                    if (TextUtils.isEmpty(it.rpParamMin)) {
                        data.add(BeanParam1(it.rpParamId.toString(), it.rpParamValue))
                    } else {
                        data.add(
                            BeanParam2(
                                it.rpParamId.toString(),
                                it.rpParamValue,
                                it.rpParamMin,
                                it.rpParamMax
                            )
                        )
                    }
                }
                if (data.size > 0)
                    load(
                        F.gB().configDeviceParamBatch(
                            mdata.deviceVersionId.toString(),
                            mdata.id.toString(),
                            Gson().toJson(data)
                        ), "configDeviceParamBatch"
                    )
            }
            1 -> {
                load(
                    F.gB().queryDeviceParamList(mdata.id.toString()),
                    "queryDeviceParamList"
                )
            }
            2 -> {
                load(
                    F.gB().configDeviceParamBatch(
                        mdata.deviceVersionId.toString(),
                        mdata.id.toString(),
                        obj.toString()
                    ), "configDeviceParamBatch"
                )
            }

        }
    }
}