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
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaWorkChoose
import com.deepblue.aidevicemanager.ada.AdaWorkChooseBottom
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelMap
import com.deepblue.aidevicemanager.model.ModelMapInfo
import com.deepblue.aidevicemanager.model.ModelMapLj
import com.google.gson.Gson
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_work_choose.*

var selectID = -1

class FrgWorkChoose : BaseFrg() {
    var page = 1
    var size = Int.MAX_VALUE
    var did = ""
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_work_choose)
        did = activity?.intent?.getStringExtra("did") ?: ""
        selectID = -1
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            0 -> {
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}map/queryMapListByGroup",
                    "POST",
                    this,
                    F.mModellogin?.token, "mapGroupId", obj.toString()
                )
            }
            1 -> {
                load(F.gB().queryMapTaskInfo(obj.toString()), "queryMapTaskInfo")
            }
//            1111 -> { //ws
//                F.mModelStatus?.mModelB = Gson().fromJson(obj.toString(), ModelB::class.java)
//                if (isHeadInit()) mHead?.setStatus()
//            }
        }

    }

    override fun initView() {
        mAbPullListView.setPageSize(6)
        mAbPullListView.setGridCount(3)
        mAbPullListView.setAbOnListViewListener { _, content ->
            var mModelMapLj = Gson().fromJson(content, ModelMapLj::class.java)
            var data = ArrayList<ModelData<ModelMapLj.RowsBean>>()
            for (i in 0 until mModelMapLj.rows.size) {
                if (i % 3 == 0) {
                    val mModelData = ModelData<ModelMapLj.RowsBean>()
                    for (j in i until Math.min(mModelMapLj.rows.size, i + 4)) {
                        mModelData.mList.add(mModelMapLj.rows[j])
                    }
                    data.add(mModelData)
                }
            }
            AdaWorkChoose(context, data)
        }
        mButton.setOnClickListener {
            load(
                F.gB().queryMapGroupList(did, page.toString(), size.toString()),
                "queryMapGroupList"
            )
        }
        mButton_load.setOnClickListener {
            if (selectID == -1) {
                Helper.toast("请先选择作业地图和作业路径")
            } else {
                Helper.startActivity(
                    context,
                    FrgWorkDetail::class.java,
                    TitleAct::class.java,
                    "id",
                    did,
                    "from",
                    "1", "mapId",
                    selectID.toString()
                )
            }
        }
    }

    override fun onDestroy() {
        selectID = -1
        super.onDestroy()
    }

    override fun loaddata() {
        load(F.gB().queryMapGroupList(did, page.toString(), size.toString()), "queryMapGroupList")
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryMapGroupList")) {
            var mModelMap = F.data2Model(data, ModelMap::class.java)
            mHorizontalListView.adapter = AdaWorkChooseBottom(context, mModelMap.rows)
            if (mModelMap.rows.isNotEmpty()) {
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}map/queryMapListByGroup",
                    "POST",
                    this,
                    F.mModellogin?.token, "mapGroupId", mModelMap.rows[0].id.toInt()
                )
            }
        } else if (method.equals("queryMapTaskInfo")) {
            var mModelMapInfo = F.data2Model(data, ModelMapInfo::class.java)
            mTextView_content.setText(
                "作业地点：${mModelMapInfo.mapTaskAddress ?: ""}\n作业任务：${mModelMapInfo.mapTaskName ?: ""}\n作业地图：${mModelMapInfo.mapName ?: ""}\n作业规划里程：${mModelMapInfo.mapTaskPathDistance
                    ?: ""}\n作业规划面积：${mModelMapInfo.mapTaskArea ?: ""}\n预计作业时间：${mModelMapInfo.mapTaskEstimatedTime ?: ""}"
            )

        }
    }

}