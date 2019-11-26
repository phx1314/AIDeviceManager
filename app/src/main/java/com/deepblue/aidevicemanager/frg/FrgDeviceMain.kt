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


class FrgDeviceMain : BaseFrg() {
    lateinit var item: ModelMain
    var position = 1
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_device_main)
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        when (type) {
            0 -> {
                mAbPullListView.setApiLoadParams(
                        "${baseUrl}device/queryCleanRobotDeviceListByModel",
                        "POST",
                        this,
                        mModellogin?.token, "deviceModelId", obj.toString()
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
            var data = ArrayList<ModelData<ModelDevices.DataBean.RowsBean>>()
            //                mMPhotoList.data.rows.addAll(mMPhotoList.data.rows)
            //                mMPhotoList.data.rows.addAll(mMPhotoList.data.rows)
            //                mMPhotoList.data.rows.addAll(mMPhotoList.data.rows)
            for (i in 0 until mMPhotoList.data.rows.size) {
                if (i % 4 == 0) {
                    val mModelData = ModelData<ModelDevices.DataBean.RowsBean>()
                    for (j in i until Math.min(mMPhotoList.data.rows.size, i + 4)) {
                        mModelData.mList.add(mMPhotoList.data.rows[j])
                    }
                    data.add(mModelData)
                }
            }

            AdaDeviceMainRight(context, data)
        }

    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryAllModelBySeries")) {
            var mModelModels = F.data2Model(data, Array<ModelModels>::class.java)
            mListView.adapter = AdaDeviceMainLeft(context, mModelModels.toMutableList())
            if (mModelModels.isNotEmpty()) {
                mAbPullListView.setApiLoadParams(
                        "${baseUrl}device/queryCleanRobotDeviceListByModel",
                        "POST",
                        this,
                        mModellogin?.token, "deviceModelId", mModelModels[0].id.toInt()
                )
            }
        }
    }
}