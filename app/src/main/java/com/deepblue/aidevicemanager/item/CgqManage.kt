//
//  CgqManage
//
//  Created by 86139 on 2019-11-21 13:25:58
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaCgqManageSon
import com.deepblue.aidevicemanager.ada.AdaCgqManageSon2
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelGb
import kotlinx.android.synthetic.main.frg_cgq_manage_new.view.*
import org.json.JSONArray


class CgqManage(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.frg_cgq_manage_new, this)
    }

    fun set(item: ModelB) {
        try {
            if (!TextUtils.isEmpty(item.data_lidar_status) && JSONArray(item.data_lidar_status).length() > 0) {
                mGridView1.visibility = View.VISIBLE
                mImageView1.visibility = View.VISIBLE
                mGridView1.adapter = AdaCgqManageSon(context, F.data2Model(item.data_lidar_status, Array<ModelGb>::class.java)?.toMutableList(),"激光雷达")
            } else {
                mGridView1.visibility = View.GONE
                mImageView1.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(item.data_millimeter_wave_status) && JSONArray(item.data_millimeter_wave_status).length() > 0) {
                mMListView1.visibility = View.VISIBLE
                mImageView2.visibility = View.VISIBLE
                mMListView1.adapter = AdaCgqManageSon2(context, F.data2Model(item.data_millimeter_wave_status, Array<ModelGb>::class.java)?.toMutableList(),"毫米波雷达")
            } else {
                mMListView1.visibility = View.GONE
                mImageView2.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(item.data_ultrasonic_wave_status) && JSONArray(item.data_ultrasonic_wave_status).length() > 0) {
                mGridView2.visibility = View.VISIBLE
                mImageView3.visibility = View.VISIBLE
                mGridView2.adapter = AdaCgqManageSon(context, F.data2Model(item.data_ultrasonic_wave_status, Array<ModelGb>::class.java)?.toMutableList(),"超声波")
            } else {
                mGridView2.visibility = View.GONE
                mImageView3.visibility = View.GONE

            }
            if (!TextUtils.isEmpty(item.data_zed_status) && JSONArray(item.data_zed_status).length() > 0) {
                mMListView2.visibility = View.VISIBLE
                mImageView4.visibility = View.VISIBLE
                mMListView2.adapter = AdaCgqManageSon2(context, F.data2Model(item.data_zed_status, Array<ModelGb>::class.java)?.toMutableList(),"ZED相机")
            } else {
                mMListView2.visibility = View.GONE
                mImageView4.visibility = View.GONE
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        mTextView_imu.text = getRightS(item.data_imu_status)
    }


}


fun getRightS(status: String?): String {
    if (TextUtils.isEmpty(status)) {
        return "N/A"
    } else {
        return if (status.equals("0")) "关" else "开"
    }
}