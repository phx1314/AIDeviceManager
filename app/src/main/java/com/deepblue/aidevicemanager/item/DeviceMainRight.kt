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
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.frg.FrgDetailDj
import com.deepblue.aidevicemanager.frg.FrgWorkDetail
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelDevices
import com.deepblue.aidevicemanager.util.GlideLoader
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.item_device_main_right.view.*


class DeviceMainRight(context: Context?) : BaseItem(context) {
    lateinit var item: ModelData<ModelDevices.RowsBean>
    lateinit var item_son: ModelDevices.RowsBean

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_device_main_right, this)

        mLinearLayout_1.setOnClickListener {
            item_son = item.mList[0]
            load(F.gB().queryDeviceLiveData(item.mList[0].id.toString()), "queryDeviceLiveData")
        }
        mLinearLayout_2.setOnClickListener {
            item_son = item.mList[1]
            load(F.gB().queryDeviceLiveData(item.mList[1].id.toString()), "queryDeviceLiveData")
        }
        mLinearLayout_3.setOnClickListener {
            item_son = item.mList[2]
            load(F.gB().queryDeviceLiveData(item.mList[2].id.toString()), "queryDeviceLiveData")
        }
        mLinearLayout_4.setOnClickListener {
            item_son = item.mList[3]
            load(F.gB().queryDeviceLiveData(item.mList[3].id.toString()), "queryDeviceLiveData")
        }
    }

    override fun onSuccess(data: String?, method: String) {
        var mModelB = F.data2Model(data, ModelB::class.java)
        F.mModelStatus?.mModelB = mModelB
        if (mModelB.data_system_status.equals("1")) {
            Helper.startActivity(
                context,
                FrgDetailDj::class.java,
                TitleAct::class.java,
                "data",
                item_son
            )
            Helper.startActivity(
                context,
                FrgWorkDetail::class.java,
                TitleAct::class.java,
                "id",
                item_son.id.toString(),
                "from",
                "0",
                "mapId",
                item_son.mapId
            )
        } else if (mModelB.data_system_status.equals("3")) {
            Helper.toast(resources.getString(R.string.d_broken))
        } else {
            Helper.startActivity(
                context,
                FrgDetailDj::class.java,
                TitleAct::class.java,
                "data",
                item_son
            )
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
            "3" -> {
                mImageView.setBackgroundResource(R.drawable.shape_yuank)
            }
            else -> {
                mImageView.setBackgroundResource(R.drawable.shape_yuank)
            }
        }
    }

    fun setShow(count: Int) {
        mLinearLayout_1.visibility = if (count > 0) View.VISIBLE else View.INVISIBLE
        mLinearLayout_2.visibility = if (count > 1) View.VISIBLE else View.INVISIBLE
        mLinearLayout_3.visibility = if (count > 2) View.VISIBLE else View.INVISIBLE
        mLinearLayout_4.visibility = if (count > 3) View.VISIBLE else View.INVISIBLE
    }

}