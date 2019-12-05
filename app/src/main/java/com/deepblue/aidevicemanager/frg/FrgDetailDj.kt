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
import com.deepblue.aidevicemanager.model.ModelDeviceDetail
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

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
//            1111 -> { //ws
//                F.mModelStatus?.mModelB = Gson().fromJson(obj.toString(), ModelB::class.java)
//                mModelB = Gson().fromJson(obj.toString(), ModelB::class.java)
//                setData(mModelB)
//                if (isHeadInit()) mHead?.setStatus()
//            }
        }
    }

    override fun initView() {
        mButton.setOnClickListener {
            if (mButton.text.equals(getString(R.string.d_qd))) {
                load(F.gB().createOrder("12", data.id.toString()), "createOrder")
            } else {
                if (mModelB.data_system_status.equals("2")) {
                    AlertDialog.Builder(context).setTitle(getString(R.string.d_ts))
                        .setMessage(getString(R.string.d_ts1))
                        .setPositiveButton(
                            getString(R.string.d_qh)
                        ) { dialogInterface: DialogInterface, i: Int ->
                            run {
                                Helper.startActivity(
                                    context,
                                    FrgWorkChoose::class.java,
                                    TitleAct::class.java
                                )
                            }
                        }
                        .setNegativeButton(getString(R.string.d_cancel), null)
                        .show()
                }
                Helper.startActivity(
                    context,
                    FrgWorkChoose::class.java,
                    TitleAct::class.java,
                    "did",
                    data.id.toString()
                )
            }
        }
    }

    override fun loaddata() {
//        load(F.gB().queryDeviceLiveData(data.id.toString()), "queryDeviceLiveData")
        load(F.gB().queryDeviceDetail(data.id.toString()), "queryDeviceDetail")
    }

    fun setData(mModelB: ModelB) {
        mDialogSet.set(mModelB)
        mTextView_lng.text = "Lng: ${mModelB.data_longitude}"
        mTextView_lat.text = "Lng: ${mModelB.data_latitude}"
        mTextView_gl.text = mModelB.data_velocity + "m/s"
        when (mModelB.data_system_status) {
            "0" -> {
                mTextView_status.text = getString(R.string.d_dj)
                mButton.text = getString(R.string.d_qszyrwxz)
            }
            "1" -> {
                mTextView_status.text = getString(R.string.d_zdzyz)
                mButton.text = getString(R.string.d_qszyrwxz)
            }
            "2" -> {
                mTextView_status.text = getString(R.string.d_sdjsz)
                mButton.text = getString(R.string.d_qszyrwxz)
            }
            "3" -> {
                mTextView_status.text = getString(R.string.d_gz)
            }
            else -> {
                mTextView_status.text = getString(R.string.d_wz)
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
        data.add(getString(R.string.d_ymz) + mModelB.data_throttle_value)
        data.add(getString(R.string.d_sczt) + getRightS(mModelB.data_brake_value))
        data.add(getString(R.string.d_sczt1) + getRightS(mModelB.data_manual_brake))
        data.add(getString(R.string.d_sszt) + getRightS(mModelB.data_brush_status))
        data.add(getString(R.string.d_sswz) + getRightS(mModelB.data_brush_position))
        data.add(getString(R.string.d_ljxwz) + getRightS(mModelB.data_trash_level))
        data.add(getString(R.string.d_xfzt) + getRightS(mModelB.data_suction_status))
        data.add(getString(R.string.d_xfkwz) + getRightS(mModelB.data_suction_inlet_position))
        data.add(getString(R.string.d_pszt) + getRightS(mModelB.data_spout_water))
        mMGridView.adapter = AdaDetailTwo(context, data)


        if (mModelB?.data_high_beam_light?.equals("1") == true) {
            mImageView_ygd.setImageResource(R.drawable.high_beam_light_light)
        } else {
            mImageView_ygd.setImageResource(R.drawable.high_beam_light_dark)
        }
        if (mModelB?.data_width_light?.equals("1") == true) {
            mImageView_sgd.setImageResource(R.drawable.width_light_light)
        } else {
            mImageView_sgd.setImageResource(R.drawable.width_light_dark)
        }
        if (mModelB?.data_left_light?.equals("1") == true) {
            mImageView_l.setImageResource(R.drawable.left_light_light)
        } else {
            mImageView_l.setImageResource(R.drawable.left_light_dark)
        }
        if (mModelB?.data_right_light?.equals("1") == true) {
            mImageView_r.setImageResource(R.drawable.right_light_light)
        } else {
            mImageView_r.setImageResource(R.drawable.right_light_dark)
        }
        if (mModelB?.data_low_beam_light?.equals("1") == true) {
            mImageView_j.setImageResource(R.drawable.j_s)
        } else {
            mImageView_j.setImageResource(R.drawable.j)
        }
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryDeviceDetail")) {
            var mModelDeviceDetail = F.data2Model(data, ModelDeviceDetail::class.java)
            mModelB = mModelDeviceDetail.cleanKingLiveStatus
            setData(mModelB)
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
        super.onDestroy()
    }
}