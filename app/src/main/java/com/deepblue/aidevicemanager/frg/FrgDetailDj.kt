//
//  FrgDetailDj
//
//  Created by 86139 on 2019-11-20 19:06:03
//  Copyright (c) 86139 All rights reserved.

/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaDetailTwo
import com.deepblue.aidevicemanager.item.DialogSet
import com.deepblue.aidevicemanager.item.getRightS
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelDevices
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_detail_dj.*

class FrgDetailDj : BaseFrg() {
    lateinit var mModelB: ModelB
    lateinit var data: ModelDevices.RowsBean
    lateinit var mDialogSet: DialogSet
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_detail_dj)
        data = activity?.intent?.getSerializableExtra("data") as ModelDevices.RowsBean
    }

    override fun initView() {
        mButton.setOnClickListener {
            if (mButton.text.equals("启动")) {
                load(F.gB().createOrder("12", data.id.toString()), "createOrder")
            } else {
                if (mModelB.data_system_status.equals("2")) {
                    AlertDialog.Builder(context).setTitle("提示")
                            .setMessage("车辆目前处于有人驾驶状态中，是否确认切换到无人作业？")
                            .setPositiveButton(
                                    "切换"
                            ) { dialogInterface: DialogInterface, i: Int ->
                                run {
                                    Helper.startActivity(
                                            context,
                                            FrgWorkChoose::class.java,
                                            TitleAct::class.java
                                    )
                                }
                            }
                            .setNegativeButton("取消", null)
                            .show()
                }
                Helper.startActivity(context, FrgWorkChoose::class.java, TitleAct::class.java)
            }
        }
    }

    override fun loaddata() {
        load(F.gB().queryDeviceLiveData(data.id.toString()), "queryDeviceLiveData")
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryDeviceLiveData")) {
            mModelB = F.data2Model(data, ModelB::class.java)
            mDialogSet.set(mModelB)

            mTextView_lng.text = "Lng: ${mModelB.data_longitude}"
            mTextView_lat.text = "Lng: ${mModelB.data_latitude}"

            mTextView_gl.text = mModelB.data_velocity + "m/s"
            when (mModelB.data_system_status) {
                "0" -> {
                    mTextView_status.text = "待机"
                    mButton.text = "清扫作业任务选择"
                }
                "1" -> {
                    mTextView_status.text = "自动作业中"
                    mButton.text = "清扫作业任务选择"
                }
                "2" -> {
                    mTextView_status.text = "手动驾驶中"
                    mButton.text = "清扫作业任务选择"
                }
                "3" -> {
                    mTextView_status.text = "故障"
                }
                else -> {
                    mTextView_status.text = "未知"
                }
            }

            mTextView_dl.text = (mModelB.data_battery_remaining_capacity ?: "0") + "%"
            mTextView_js.text = (mModelB.data_water_level ?: "0") + "%"
            mTextView_fx.text = mModelB.data_gear
            if (TextUtils.isEmpty(mModelB.data_water_level)) {
                mTextView_sf.text = "N/A"
            } else {
                mTextView_sf.text = if (mModelB.data_water_level.equals("0")) "ERROR" else "OK"
            }
            var data = ArrayList<String>()
            data.add("油门值:" + mModelB.data_throttle_value)
            data.add("刹车状态:" + getRightS(mModelB.data_brake_value))
            data.add("手刹状态:" + getRightS(mModelB.data_manual_brake))
            data.add("扫刷状态:" + getRightS(mModelB.data_brush_status))
            data.add("扫刷位置:" + getRightS(mModelB.data_brush_position))
            data.add("垃圾箱位置:" + getRightS(mModelB.data_trash_level))
            data.add("吸风状态:" + getRightS(mModelB.data_suction_status))
            data.add("吸风口位置:" + getRightS(mModelB.data_suction_inlet_position))
            data.add("喷水状态:" + getRightS(mModelB.data_spout_water))
            mMGridView.adapter = AdaDetailTwo(context, data)
        } else if (method.equals("createOrder")) {
            mProgressBar.visibility = View.VISIBLE
        }
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        mHead.setTitle(data.deviceCode)

        mDialogSet = DialogSet(context, data)
        mHead.setShowPop(mDialogSet)
//        mHead.mLinearLayout_status.visibility = View.VISIBLE
//        mHead.mImageView.setBackgroundResource(R.drawable.u1844)
//        mHead.mTextView_d_status.text = getString(R.string.d_connect)

    }

    override fun onDestroy() {
        F.mModelStatus?.mModelB = null
        F.stopConnectWSocket()
        super.onDestroy()
    }
}