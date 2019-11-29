//
//  FrgWorkChoose
//
//  Created by 86139 on 2019-11-21 09:29:17
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaDeviceMainRight
import com.deepblue.aidevicemanager.ada.AdaWorkChoose
import com.deepblue.aidevicemanager.ada.AdaWorkChooseBottom
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDevices
import com.deepblue.aidevicemanager.model.ModelMap
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frg_device_main.*
import kotlinx.android.synthetic.main.frg_work_choose.*
import kotlinx.android.synthetic.main.frg_work_choose.mAbPullListView


class FrgWorkChoose : BaseFrg() {
    var page = 1
    var size = Int.MAX_VALUE
    var did = ""
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_work_choose)
        did = activity?.intent?.getStringExtra("did") ?: ""
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        when (type) {
            0 -> {

            }
        }

    }

    override fun initView() {
        mAbPullListView.setAbOnListViewListener { _, content ->
            val mMPhotoList = Gson().fromJson(content, ModelDevices::class.java)
            var data = ArrayList<ModelData<ModelDevices.RowsBean>>()
            for (i in 0 until mMPhotoList.rows.size) {
                if (i % 3 == 0) {
                    val mModelData = ModelData<ModelDevices.RowsBean>()
                    for (j in i until Math.min(mMPhotoList.rows.size, i + 4)) {
                        mModelData.mList.add(mMPhotoList.rows[j])
                    }
                    data.add(mModelData)
                }
            }
            AdaWorkChoose(context, data)
        }
    }

    override fun loaddata() {
        load(
            F.gB().queryMapListByDevice(did, page.toString(), size.toString()),
            "queryMapListByDevice"
        )
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryMapListByDevice")) {
            var mModelMap = F.data2Model(data, ModelMap::class.java)
            mHorizontalListView.adapter = AdaWorkChooseBottom(context, mModelMap.rows)
            if (mModelMap.rows.isNotEmpty()) {
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}map/queryMapAreaPathListByDevice",
                    "POST",
                    this,
                    F.mModellogin?.token, "deviceId", mModelMap.rows[0].deviceId.toInt()
                )
            }
        }
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
    }
}