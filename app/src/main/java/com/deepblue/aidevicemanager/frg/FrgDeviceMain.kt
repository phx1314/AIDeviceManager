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
import com.deepblue.aidevicemanager.R
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_device_main.*
import com.deepblue.aidevicemanager.ada.AdaMain
import android.R.attr.data
import java.nio.file.Files.size
import com.deepblue.aidevicemanager.model.ModelMain.DeviceListBean
import com.mdx.framework.adapter.MAdapter
import com.mdx.framework.view.listener.AbOnListViewListener
import android.R.attr.data
import android.R.attr.data
import com.deepblue.aidevicemanager.F.baseUrl
import com.deepblue.aidevicemanager.F.mModellogin
import com.deepblue.aidevicemanager.ada.AdaDeviceMainLeft
import com.deepblue.aidevicemanager.ada.AdaDeviceMainRight
import com.deepblue.aidevicemanager.model.ModelMain
import com.deepblue.aidevicemanager.model.ModelModels
import timber.log.Timber
import java.nio.file.Files.size
import java.nio.file.Files.size
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDevices
import com.google.gson.Gson
import java.nio.file.Files.size
import android.opengl.ETC1.getHeight
import android.support.v4.os.HandlerCompat.postDelayed
import kotlinx.android.synthetic.main.frg_device_main.mTextView_gs
import kotlinx.android.synthetic.main.frg_device_main.mTextView_name
import kotlinx.android.synthetic.main.frg_main.*


class FrgDeviceMain : BaseFrg() {
    lateinit var item: ModelMain
    var position = 1
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_device_main)
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
        mTextView_gs.text = F.mModellogin?.merchant?.contactName + " >"
        mTextView_name.text = F.mModellogin?.merchant?.name
        mAbPullListView.setAbOnListViewListener(object : AbOnListViewListener {
            override fun onSuccess(methodName: String, content: String): MAdapter<*> {
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

                return AdaDeviceMainRight(context, data)
            }
        })

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