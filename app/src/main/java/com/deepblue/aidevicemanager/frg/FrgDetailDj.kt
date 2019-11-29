//
//  FrgDetailDj
//
//  Created by 86139 on 2019-11-20 19:06:03
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.os.Bundle

import com.deepblue.aidevicemanager.R

import android.widget.TextView
import android.widget.ExpandableListView
import android.widget.Button
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.model.ModelDeviceDetail
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_detail_dj.*


class FrgDetailDj : BaseFrg() {
    
    lateinit var mModelDeviceDetail: ModelDeviceDetail
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_detail_dj)
        mModelDeviceDetail = activity?.intent?.getSerializableExtra("mModelDeviceDetail") as ModelDeviceDetail
    }
    
    override fun initView() {
        
        mButton.setOnClickListener {
            
            Helper.startActivity(context, FrgWorkChoose::class.java, TitleAct::class.java)
        }
    }
    
    override fun loaddata() {
    }
    
    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        mHead.setTitle(mModelDeviceDetail.deviceCode)
    }
}