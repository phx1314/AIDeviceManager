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
import com.deepblue.aidevicemanager.ada.AdaWorkChoose
import com.deepblue.aidevicemanager.ada.AdaWorkChooseBottom
import com.deepblue.aidevicemanager.model.*
import com.google.gson.Gson
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_work_choose.*

var selectID = -1

class FrgWorkChoose : BaseFrg() {
    var page = 1
    var size = Int.MAX_VALUE
    lateinit var mModelMapLj: ModelMapLj.RowsBean
    var mModelMapInfo: ModelMapInfo? = null
    var mModelDeviceDetail: ModelDeviceDetail? = null
    lateinit var data: ModelDevices.RowsBean
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_work_choose)
        mModelDeviceDetail = activity?.intent?.getSerializableExtra("mModelDeviceDetail") as ModelDeviceDetail
        data = activity?.intent?.getSerializableExtra("data") as ModelDevices.RowsBean
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
                mModelMapLj = obj as ModelMapLj.RowsBean
                load(F.gB().queryMapTaskInfo(mModelMapLj.id.toString()), "queryMapTaskInfo")
            }
            1111 -> { //ws
                try {
                    F.sendDb2a(F.mModelStatus?.mModelB, F.data2Model(obj.toString(), ModelA::class.java)?.cleanKingLiveStatus)
                    F.sendDb2a(mModelDeviceDetail?.cleanKingLiveStatus, F.data2Model(obj.toString(), ModelA::class.java)?.cleanKingLiveStatus)
                    if (isHeadInit()) mHead?.setStatus(this.javaClass.simpleName)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
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
                F.gB().queryMapGroupList(mModelDeviceDetail?.id.toString(), page.toString(), size.toString()),
                "queryMapGroupList"
            )
        }
        mButton_load.setOnClickListener {
            if (selectID == -1) {
                Helper.toast(getString(R.string.d_ts3))
            } else {
                Helper.startActivity(
                    context,
                    FrgWorkDetail::class.java,
                    TitleAct::class.java,
                    "mModelDeviceDetail",
                    mModelDeviceDetail,
                    "from",
                    "1", "mapId",
                    selectID.toString(), "mapTaskName",
                    mModelMapInfo?.mapTaskName, "data", data
                )
            }
        }
    }

    override fun onDestroy() {
        selectID = -1
        super.onDestroy()
    }

    override fun loaddata() {
        load(F.gB().queryMapGroupList(mModelDeviceDetail?.id.toString(), page.toString(), size.toString()), "queryMapGroupList")
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryMapGroupList")) {
            var mModelMap = F.data2Model(data, ModelMap::class.java)
            mHorizontalListView.adapter = AdaWorkChooseBottom(context, mModelMap?.rows)
            if (mModelMap?.rows!!.isNotEmpty()) {
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}map/queryMapListByGroup",
                    "POST",
                    this,
                    F.mModellogin?.token, "mapGroupId", mModelMap!!.rows[0].id.toInt()
                )
            }
        } else if (method.equals("queryMapTaskInfo")) {
            mModelMapInfo = F.data2Model(data, ModelMapInfo::class.java)
            mTextView_content.setText(
                getString(R.string.d_zydd) + "${mModelMapInfo?.mapTaskAddress ?: ""}\n" + getString(R.string.d_zyrw) + "${mModelMapInfo?.mapTaskName
                    ?: ""}\n" + getString(R.string.d_zydt) + "${mModelMapInfo?.mapName
                    ?: ""}\n" + getString(R.string.d_zyghlc) + "${com.mdx.framework.F.go2Wei(mModelMapInfo?.mapTaskPathDistance?.toDouble())
                    ?: ""}公里\n" + getString(R.string.d_zyghmj) + "${com.mdx.framework.F.go2Wei(mModelMapInfo?.mapTaskArea?.toDouble())
                    ?: ""}㎡\n" + getString(R.string.d_yjzysj) + "${com.mdx.framework.F.go2Wei(mModelMapInfo?.mapTaskEstimatedTime?.toDouble()) ?: ""}H"
            )

        }
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        mHead.setShowPop(mDialogSet)
        mHead.setTitle(data.deviceName)
    }
}