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
import android.view.View

import com.deepblue.aidevicemanager.R

import android.widget.TextView
import android.widget.ExpandableListView
import android.widget.Button
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.item.DialogSet
import com.deepblue.aidevicemanager.model.ModelDeviceDetail
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_detail_dj.*
import kotlinx.android.synthetic.main.item_head.view.*


class FrgDetailDj : BaseFrg() {

    lateinit var mModelDeviceDetail: ModelDeviceDetail
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_detail_dj)
        mModelDeviceDetail =
            activity?.intent?.getSerializableExtra("mModelDeviceDetail") as ModelDeviceDetail
    }

    override fun initView() {
        mButton.setOnClickListener {
            if (mButton.text.equals("启动")) {

            } else {
                Helper.startActivity(context, FrgWorkChoose::class.java, TitleAct::class.java)
            }
        }
    }

    override fun loaddata() {
        if (mModelDeviceDetail.deviceStatus.equals("0")) {//离线
            mTextView_status.text = "离线"
            mButton.visibility = View.GONE
            mHead.setShowPop(DialogSet(context,mModelDeviceDetail))//fix
            mHead.mImageView.setBackgroundResource(R.drawable.lian)
            mHead.mTextView_d_status.text = getString(R.string.d_no_connect)
        } else {
            if (mModelDeviceDetail.deviceOnlineStatus.equals("0")) {//离线待机
                mTextView_status.text = "离线待机"
                mButton.text = "启动"
                mHead.setShowPop(DialogSet(context,mModelDeviceDetail))
                mHead.mImageView.setBackgroundResource(R.drawable.u1844)
                mHead.mTextView_d_status.text = getString(R.string.d_connect)
            } else if (mModelDeviceDetail.deviceOnlineStatus.equals("3")) {//在线待机
                mTextView_status.text = "待机"
                mButton.text = "清扫作业任务选择"
            } else if (mModelDeviceDetail.deviceOnlineStatus.equals("4")) {
                if (mModelDeviceDetail.deviceTaskStatus.equals("2")) {//正在施行
                }
            }
        }
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        mHead.setTitle(mModelDeviceDetail.deviceCode)
        mHead.mLinearLayout_status.visibility = View.VISIBLE
    }
}