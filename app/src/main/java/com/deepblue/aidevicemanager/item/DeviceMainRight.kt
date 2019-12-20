//
//  DeviceMainRight
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.frg.FrgDetailDj
import com.deepblue.aidevicemanager.frg.FrgWorkDetail
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDeviceDetail
import com.deepblue.aidevicemanager.model.ModelDevices
import com.deepblue.aidevicemanager.util.GlideLoader
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.utility.Helper
import kotlinx.android.synthetic.main.item_device_main_right.view.*


class DeviceMainRight(context: Context?) : BaseItem(context) {
    lateinit var item: ModelData<ModelDevices.RowsBean>
    lateinit var item_son: ModelDevices.RowsBean

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_device_main_right, this)
        mLinearLayout_1.setOnClickListener {
            item_son = item.mList[0]
//            load(F.gB().queryDeviceLiveData(item.mList[0].id.toString()), "queryDeviceLiveData")
            load(F.gB().queryDeviceDetail(item.mList[0].id.toString()), "queryDeviceDetail")
        }
        mLinearLayout_2.setOnClickListener {
            item_son = item.mList[1]
            load(F.gB().queryDeviceDetail(item.mList[1].id.toString()), "queryDeviceDetail")
        }
        mLinearLayout_3.setOnClickListener {
            item_son = item.mList[2]
            load(F.gB().queryDeviceDetail(item.mList[2].id.toString()), "queryDeviceDetail")
        }
        mLinearLayout_4.setOnClickListener {
            item_son = item.mList[3]
            load(F.gB().queryDeviceDetail(item.mList[3].id.toString()), "queryDeviceDetail")
        }
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryDeviceDetail")) {
            var mModelDeviceDetail = F.data2Model(data?.replace("\\n", ""), ModelDeviceDetail::class.java)
            F.mModelStatus?.mModelB = mModelDeviceDetail?.cleanKingLiveStatus ?: ModelB()

            if (mModelDeviceDetail?.deviceStatus?.equals("1") == true) {
                goDj(mModelDeviceDetail)
            } else if (mModelDeviceDetail?.deviceStatus?.equals("2") == true) {
                if (mModelDeviceDetail?.breakdown?.equals("1") == true) {//故障
                    goDj(mModelDeviceDetail)
                } else if (mModelDeviceDetail?.breakdown?.equals("0") == true) {
                    when (mModelDeviceDetail?.cleanKingLiveStatus?.data_sweeper_state) {
//                        "0", "2" -> {
//                            goDj(mModelDeviceDetail)
//                        }
                        "1" -> {
                            goDetail(mModelDeviceDetail)
                        }
                        else -> {
                            goDj(mModelDeviceDetail)
                        }

                    }
                } else {
                    Helper.toast("未知状态")
                }

            } else if (mModelDeviceDetail?.deviceStatus?.equals("3") == true) {//离线待机
                goDj(mModelDeviceDetail)
            } else if (mModelDeviceDetail?.deviceStatus?.equals("0") == true) {//未激活
                Helper.toast("未激活")
            }

        }
    }

    fun goDetail(mModelDeviceDetail: ModelDeviceDetail) {
        if (!TextUtils.isEmpty(mModelDeviceDetail?.mapId)) {
            F.connectWSocket(context, "${mModelDeviceDetail?.id}/${F.mModellogin?.token}")
            Helper.startActivity(
                context,
                FrgWorkDetail::class.java,
                TitleAct::class.java,
                "mModelDeviceDetail",
                mModelDeviceDetail,
                "from",
                "0",
                "mapId",
                mModelDeviceDetail?.mapId, "data",
                item_son
            )
        } else {
            Helper.toast(context.getString(R.string.d_id_null))
        }

    }

    fun goDj(mModelDeviceDetail: ModelDeviceDetail) {
        F.connectWSocket(context, "${mModelDeviceDetail?.id}/${F.mModellogin?.token}")
        Helper.startActivity(
            context,
            FrgDetailDj::class.java,
            TitleAct::class.java,
            "data",
            item_son
        )
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

        if (it.deviceStatus?.equals("1") == true) {//离线
            mImageView.setBackgroundResource(R.drawable.shape_grayk)
        } else if (it.deviceStatus?.equals("2") == true) {
            if (it.breakdown?.equals("1") == true) {//故障
                mImageView.setBackgroundResource(R.drawable.shape_yuank)
            } else if (it.breakdown?.equals("0") == true) {
                when (it.data_system_status) {
                    "0" -> {
                        mImageView.setBackgroundResource(R.drawable.shape_bluek)
                    }
                    "1" -> {
                        mImageView.setBackgroundResource(R.drawable.shape_greenk)
                    }
                    "2" -> {
                        mImageView.setBackgroundResource(R.drawable.shape_orangek)
                    }
                    else -> {
                        mImageView.setBackgroundResource(R.drawable.shape_bluek)
                    }

                }
            } else {
                mImageView.setBackgroundResource(R.drawable.shape_bluek)
            }
        } else if (it?.deviceStatus?.equals("3") == true) {//离线待机
            mImageView.setBackgroundResource(R.drawable.shape_yellowk)
        } else if (it?.deviceStatus?.equals("0") == true) {//未激活
            mImageView.setBackgroundResource(R.drawable.shape_grayk)
        }


    }

    fun setShow(count: Int) {
        mLinearLayout_1.visibility = if (count > 0) View.VISIBLE else View.INVISIBLE
        mLinearLayout_2.visibility = if (count > 1) View.VISIBLE else View.INVISIBLE
        mLinearLayout_3.visibility = if (count > 2) View.VISIBLE else View.INVISIBLE
        mLinearLayout_4.visibility = if (count > 3) View.VISIBLE else View.INVISIBLE
    }

}