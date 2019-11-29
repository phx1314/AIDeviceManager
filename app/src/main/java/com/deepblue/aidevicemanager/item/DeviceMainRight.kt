//
//  DeviceMainRight
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import com.deepblue.aidevicemanager.R

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.widget.ImageView
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.frg.FrgDetailDj
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDeviceDetail
import com.deepblue.aidevicemanager.model.ModelDevices
import com.deepblue.aidevicemanager.util.GlideLoader
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.item_device_main_right.view.*


class DeviceMainRight(context: Context?) : BaseItem(context) {
    lateinit var item: ModelData<ModelDevices.RowsBean>

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_device_main_right, this)

        mLinearLayout_1.setOnClickListener {
            load(F.gB().queryDeviceDetail(item.mList[0].id.toString()), "queryDeviceDetail")
        }
        mLinearLayout_2.setOnClickListener {
            load(F.gB().queryDeviceDetail(item.mList[1].id.toString()), "queryDeviceDetail")
        }
        mLinearLayout_3.setOnClickListener {
            load(F.gB().queryDeviceDetail(item.mList[2].id.toString()), "queryDeviceDetail")
        }
        mLinearLayout_4.setOnClickListener {
            load(F.gB().queryDeviceDetail(item.mList[3].id.toString()), "queryDeviceDetail")
        }
    }

    override fun onSuccess(data: String?, method: String) {
        var mModelDeviceDetail = F.data2Model(data, ModelDeviceDetail::class.java)
        if (mModelDeviceDetail.deviceMaintainStatus.equals("1")) {
            Helper.startActivity(
                context,
                FrgDetailDj::class.java,
                TitleAct::class.java,
                "mModelDeviceDetail",
                mModelDeviceDetail
            )
            if (mModelDeviceDetail.deviceStatus.equals("0")) {
            } else {
                if (mModelDeviceDetail.deviceOnlineStatus.equals("0")) {
                } else if (mModelDeviceDetail.deviceOnlineStatus.equals("3")) {
                } else if (mModelDeviceDetail.deviceOnlineStatus.equals("4")) {
                    if (mModelDeviceDetail.deviceTaskStatus.equals("2")) {//正在施行
                    }
                }
            }
        } else {
            Helper.toast(resources.getString(R.string.d_broken))
        }

    }

    fun set(item: ModelData<ModelDevices.RowsBean>) {
        this.item = item
        setShow(item.mList.size)
        item.mList.forEachIndexed { i, it ->
            if (i == 0) {
                mTextView_num_1.text = it.deviceName
                setStatusType(mImageView_status_1, it)
                GlideLoader.loadImage(it.modelPicUrl, mImageView_slw_1, R.drawable.test_clean)
            } else if (i == 1) {
                mTextView_num_2.text = it.deviceName
                setStatusType(mImageView_status_2, it)
                GlideLoader.loadImage(it.modelPicUrl, mImageView_slw_2, R.drawable.test_clean)
            } else if (i == 2) {
                mTextView_num_3.text = it.deviceName
                setStatusType(mImageView_status_3, it)
                GlideLoader.loadImage(it.modelPicUrl, mImageView_slw_3, R.drawable.test_clean)
            } else if (i == 3) {
                mTextView_num_4.text = it.deviceName
                setStatusType(mImageView_status_4, it)
                GlideLoader.loadImage(it.modelPicUrl, mImageView_slw_4, R.drawable.test_clean)
            }
        }

    }

    fun setStatusType(mImageView: ImageView, it: ModelDevices.RowsBean) {
        if (it.deviceMaintainStatus.equals("1")) {//正常
            if (it.deviceStatus.equals("0")) {//未激活
                mImageView.setBackgroundResource(R.drawable.shape_grayk)
            } else {//激活
                if (it.deviceOnlineStatus.equals("0")) {//离线
                    mImageView.setBackgroundResource(R.drawable.shape_yellowk)
                } else if (it.deviceOnlineStatus.equals("3")) {//待命
                    mImageView.setBackgroundResource(R.drawable.shape_bluek)
                } else if (it.deviceOnlineStatus.equals("4")) {//执行任务
                    if (it.deviceTaskStatus.equals("2")) {//正在执行
                        mImageView.setBackgroundResource(R.drawable.shape_greenk)
                    }
                }
            }
        } else {//故障
            mImageView.setBackgroundResource(R.drawable.shape_yuank)
        }
    }

    fun setShow(count: Int) {
        mLinearLayout_1.visibility = if (count > 0) View.VISIBLE else View.INVISIBLE
        mLinearLayout_2.visibility = if (count > 1) View.VISIBLE else View.INVISIBLE
        mLinearLayout_3.visibility = if (count > 2) View.VISIBLE else View.INVISIBLE
        mLinearLayout_4.visibility = if (count > 3) View.VISIBLE else View.INVISIBLE
    }

}