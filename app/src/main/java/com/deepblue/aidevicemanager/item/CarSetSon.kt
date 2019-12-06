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
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import cn.qqtheme.framework.picker.TimePicker
import cn.qqtheme.framework.util.ConvertUtils
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelCarSet
import com.deepblue.aidevicemanager.model.ModelTime
import com.google.gson.Gson
import com.mdx.framework.activity.BaseActivity
import kotlinx.android.synthetic.main.item_car_set_son.view.*
import java.util.*


class CarSetSon(context: Context?) : LinearLayout(context), CompoundButton.OnCheckedChangeListener {
    var mModelTime: ModelTime? = null
    lateinit var item: ModelCarSet

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_car_set_son, this)
        mCheckBox.setOnCheckedChangeListener(this)
        mTextView_3.setOnClickListener { onTimePicker() }

        mEditText_1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                item.rpParamValue = mEditText_1.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }


    fun set(item: ModelCarSet) {
        this.item = item
        if (item.paramShowName.equals(resources.getString(R.string.d_dsqd))) {
            mLinearLayout_1.visibility = View.GONE
            mLinearLayout_2.visibility = View.VISIBLE

            mModelTime = F.data2Model(item.rpParamValue, ModelTime::class.java)
            mTextView_3.text = mModelTime?.time
            mCheckBox.setOnCheckedChangeListener(null)
            mCheckBox.isChecked = mModelTime?.type == 1
            mCheckBox.setOnCheckedChangeListener(this)
        } else {
            mLinearLayout_1.visibility = View.VISIBLE
            mLinearLayout_2.visibility = View.GONE
            mTextView_name.text = item.paramShowName
            mTextView_dw.text = item.paramUnit
            mEditText_1.setText(item.rpParamValue)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) mModelTime?.type = 1 else mModelTime?.type = 0
        item.rpParamValue = Gson().toJson(mModelTime)
    }

    private fun onTimePicker() {
        val picker = TimePicker(context as BaseActivity, TimePicker.HOUR_24)
        picker.setUseWeight(false)
        picker.setCycleDisable(false)
        picker.setRangeStart(0, 0)//00:00
        picker.setRangeEnd(23, 59)//23:59
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
        picker.setSelectedItem(currentHour, currentMinute)
        picker.setTopLineVisible(false)
        picker.setTextPadding(ConvertUtils.toPx(context, 15f))
        picker.setOnTimePickListener { hour, minute ->
            run {
                mModelTime?.time = "$hour:$minute"
                mTextView_3.text = mModelTime?.time
                item.rpParamValue = Gson().toJson(mModelTime)
            }
        }
        picker.show()
    }

}