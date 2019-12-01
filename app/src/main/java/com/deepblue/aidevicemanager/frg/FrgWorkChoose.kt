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
import android.util.Log
import android.widget.AbsListView
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaWorkChoose
import com.deepblue.aidevicemanager.ada.AdaWorkChooseBottom
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDevices
import com.deepblue.aidevicemanager.model.ModelMap
import com.google.gson.Gson
import com.mdx.framework.view.HorizontalListView
import kotlinx.android.synthetic.main.frg_work_choose.*
import timber.log.Timber

class FrgWorkChoose : BaseFrg() {
    var page = 1
    var size = Int.MAX_VALUE
    var did = ""
    var mTotalItemCount: Int = 0
    var mIsLoading: Boolean = false
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_work_choose)
        did = activity?.intent?.getStringExtra("did") ?: ""
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
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
        //load(F.gB().queryMapListByDevice(did, page.toString(), size.toString()), "queryMapListByDevice")

        mHorizontalListView.adapter = AdaWorkChooseBottom(context, ArrayList<ModelMap.RowsBean>().apply { this.add(ModelMap.RowsBean());this.add(ModelMap.RowsBean());this.add(ModelMap.RowsBean());this.add(ModelMap.RowsBean());this.add(ModelMap.RowsBean());this.add(ModelMap.RowsBean());this.add(ModelMap.RowsBean()) })
        mHorizontalListView.setOnScrollStateChangedListener { scrollState ->
            val lastVisibleIndex = mHorizontalListView.lastVisiblePosition
            if (!mIsLoading && scrollState === HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING//停止滚动
                    && lastVisibleIndex == 6) {//滑动到最后一项
                Timber.d("加载更多")
            }
        }
        mHorizontalListView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (firstVisibleItem >  mHorizontalListView.lastVisiblePosition) {
                    Log.d("scroll", "上滑");
                }
                if (firstVisibleItem <  mHorizontalListView.lastVisiblePosition) {
                    Log.d("scroll", "下滑");
                }
                if (firstVisibleItem ==  mHorizontalListView.lastVisiblePosition) {
                    return;
                }
                //mHorizontalListView.lastVisiblePosition = firstVisibleItem;

            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }
        })
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