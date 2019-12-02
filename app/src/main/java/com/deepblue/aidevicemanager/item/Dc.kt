//
//  Dian
//
//  Created by DELL on 2019-10-15 13:05:56
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.bean.BeanParam2
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelCarSet
import com.google.gson.Gson
import com.mdx.framework.Frame
import kotlinx.android.synthetic.main.frg_dc.view.*


class Dc(context: Context?) : LinearLayout(context) {
    var fromUser_ddlbj: Boolean = false
    var fromUser_yzddlbj: Boolean = false
    lateinit var it_ddlbj: ModelCarSet
    lateinit var it_yzddlbj: ModelCarSet

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.frg_dc, this)

        mSeekBar_ddlbj.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                fromUser_ddlbj = fromUser
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (fromUser_ddlbj) {
                    var sum = (seekBar?.progress ?: 0) + it_ddlbj.rpParamMin.toInt()
                    it_ddlbj.rpParamValue = sum.toString()
                    var data = ArrayList<Any>()
                    data.add(BeanParam2(it_ddlbj.rpParamId.toString(),
                            it_ddlbj.rpParamValue,
                            it_ddlbj.rpParamMin,
                            it_ddlbj.rpParamMax))
                    Frame.HANDLES.sentAll("DialogSet", 2, Gson().toJson(data))
                }
            }
        })
        mSeekBar_yzddlbj.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                fromUser_yzddlbj = fromUser
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (fromUser_yzddlbj) {
                    it_yzddlbj.rpParamValue =
                            ((seekBar?.progress ?: 0) + it_yzddlbj.rpParamMin.toInt()).toString()
                    var data = ArrayList<Any>()
                    data.add(BeanParam2(it_yzddlbj.rpParamId.toString(),
                            it_yzddlbj.rpParamValue,
                            it_yzddlbj.rpParamMin,
                            it_yzddlbj.rpParamMax))
                    Frame.HANDLES.sentAll("DialogSet", 2, Gson().toJson(data))
                }
            }
        })
    }


    fun set(item: ModelB) {
        mTextView_dc.text = item.data_battery_remaining_capacity ?: "N/A"
        mTextView_dy.text = item.data_battery_voltage  ?: "N/A"
//        mTextView_dl.text = item.data_battery_remaining_capacity ?: "N/A"
        mTextView_wd.text = item.data_battery_temperature  ?: "N/A"
        mTextView_dliang.text = item.data_battery_remaining_capacity ?: "N/A"
        mTextView_dcrl.text = item.data_battery_capacity ?: "N/A"
//        mTextView_xhcsh.text = item.batteryCycle ?: "N/A"
//        mTextView_dccode.text = item.batterySeriesNumber ?: "N/A"
    }

    fun set(it: ModelCarSet) {
        if (it.paramShowName.equals(resources.getString(R.string.d_yzddlbj))) {
            mLinearLayout_yzddlbj.visibility = View.VISIBLE
            pzSeek(it, mSeekBar_yzddlbj)
            this.it_yzddlbj = it
        } else {
            mLinearLayout_ddlbj.visibility = View.VISIBLE
            pzSeek(it, mSeekBar_ddlbj)
            this.it_ddlbj = it
        }
    }

    fun pzSeek(it: ModelCarSet, mSeekBar: SeekBar) {
        var progress = it.rpParamMax.toInt() - it.rpParamMin.toInt()
        var progress_son = it.rpParamValue.toInt() - it.rpParamMin.toInt()
        mSeekBar.max = progress
        mSeekBar.setProgress(progress_son)
    }

}