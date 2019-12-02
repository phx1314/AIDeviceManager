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
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelB
import kotlinx.android.synthetic.main.frg_cgq_manage.view.*


class CgqManage(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.frg_cgq_manage, this)
    }

    fun set(item: ModelB) {
        mTextView1.text = getRightS(item.data_lidar1_status)
        mTextView2.text = getRightS(item.data_lidar2_status)
        mTextView3.text = getRightS(item.data_lidar3_status)
        mTextView4.text = getRightS(item.data_lidar4_status)
        mTextView5.text = getRightS(item.data_lidar5_status)
        mTextView6.text = getRightS(item.data_lidar6_status)
        mTextView7.text = getRightS(item.data_lidar7_status)
        mTextView8.text = getRightS(item.data_lidar8_status)
        mTextViewl1.text = getRightS(item.data_millimeter_wave1_status)
        mTextViewl2.text = getRightS(item.data_millimeter_wave2_status)
        mTextViewl3.text = getRightS(item.data_millimeter_wave3_status)
        mTextViewl4.text = getRightS(item.data_millimeter_wave4_status)
        mTextViewl5.text = getRightS(item.data_millimeter_wave5_status)
        mTextViewl6.text = getRightS(item.data_millimeter_wave6_status)
        mTextViewl7.text = getRightS(item.data_millimeter_wave7_status)
        mTextViewl8.text = getRightS(item.data_millimeter_wave8_status)
        mTextViewc1.text = getRightS(item.data_ultrasonic_wave1_status)
        mTextViewc2.text = getRightS(item.data_ultrasonic_wave2_status)
        mTextViewc3.text = getRightS(item.data_ultrasonic_wave3_status)
        mTextViewc4.text = getRightS(item.data_ultrasonic_wave4_status)
        mTextViewc5.text = getRightS(item.data_ultrasonic_wave5_status)
        mTextViewc6.text = getRightS(item.data_ultrasonic_wave6_status)
        mTextViewc7.text = getRightS(item.data_ultrasonic_wave7_status)
        mTextViewc8.text = getRightS(item.data_ultrasonic_wave8_status)
        mTextView_zed.text = item.data_zed_status ?: "N/A"
//        mTextView_imu.text = item.data_imustatus ?: "N/A"
    }


}


fun getRightS(status: String): String {
    if (TextUtils.isEmpty(status)) {
        return "N/A"
    } else {
        return if (status.equals("0")) "关" else "开"
    }
}