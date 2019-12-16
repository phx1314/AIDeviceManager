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
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.baseUrl
import com.deepblue.aidevicemanager.F.mModellogin
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaDeviceMainLeft
import com.deepblue.aidevicemanager.ada.AdaDeviceMainRight
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDevices
import com.deepblue.aidevicemanager.model.ModelMain
import com.deepblue.aidevicemanager.model.ModelModels
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frg_device_main.*
import java.util.*


class FrgDeviceMain : BaseFrg() {
    lateinit var item: ModelMain
    var position = 1

    lateinit var mModelModels_one: ModelModels
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_device_main)
    }


    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            0 -> {
                mModelModels_one = obj as ModelModels
                mAbPullListView.setApiLoadParams(
                    "${baseUrl}device/queryCleanRobotDeviceListByModel",
                    "POST",
                    this,
                    mModellogin?.token, "deviceModelId", mModelModels_one.id.toInt()
                )
            }
        }

    }

    override fun initView() {
        item = activity?.intent?.getSerializableExtra("item") as ModelMain
        mImageButton_top.setOnClickListener {
            if (position > 1) {
                --position
                mAbPullListView.postDelayed(Runnable {
                    mAbPullListView.smoothScrollToPositionFromTop(
                        position,
                        mAbPullListView.height / 2
                    )
                }, 100)

            }
        }
        mImageButton_bottom.setOnClickListener {
            if (position < mAbPullListView.adapter.count) {
                ++position
                mAbPullListView.postDelayed(Runnable {
                    mAbPullListView.smoothScrollToPosition(
                        position
                    )
                }, 100)
            }
        }
    }


    override fun loaddata() {
        load(F.gB().queryAllModelBySeries(item.id.toInt()), "queryAllModelBySeries")
        mTextView_title.text = item.seriesName
        mTextView_gs.text = F.mModellogin?.merchant?.name + " >"
        mTextView_name.text = F.mModellogin?.user?.name
        mAbPullListView.setPageSize(16)
        mAbPullListView.setGridCount(4)
        mAbPullListView.setAbOnListViewListener { _, content ->
            val mMPhotoList = Gson().fromJson(content, ModelDevices::class.java)
            var data = ArrayList<ModelData<ModelDevices.RowsBean>>()
            for (i in 0 until mMPhotoList.rows.size) {
                if (i % 4 == 0) {
                    val mModelData = ModelData<ModelDevices.RowsBean>()
                    for (j in i until Math.min(mMPhotoList.rows.size, i + 4)) {
                        mMPhotoList.rows[j].modelPicUrl = mModelModels_one.modelPicUrl
                        mModelData.mList.add(mMPhotoList.rows[j])
                    }
                    data.add(mModelData)
                }
            }
            AdaDeviceMainRight(context, data)
        }
//        mAbPullListView.setApiLoadParams(
//            "${baseUrl}device/queryCleanRobotDeviceListBySeries",
//            "POST",
//            this,
//            mModellogin?.token, "deviceSeriesId", item.id.toInt()
//        )
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryAllModelBySeries")) {
            var mModelModels = F.data2Model(data, Array<ModelModels>::class.java)
            mListView.adapter = AdaDeviceMainLeft(context, mModelModels?.toMutableList())
            mModelModels_one = mModelModels!![0]
            if (mModelModels?.isNotEmpty()) {
                mAbPullListView.setApiLoadParams(
                    "${baseUrl}device/queryCleanRobotDeviceListByModel",
                    "POST",
                    this,
                    mModellogin?.token, "deviceModelId", mModelModels_one.id.toInt()
                )
            }
        }
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
    }

    override fun onResume() {
        super.onResume()
        mAbPullListView?.pullLoad()
    }
}