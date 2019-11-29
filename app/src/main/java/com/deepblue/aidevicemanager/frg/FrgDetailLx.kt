//
//  FrgDetailLx
//
//  Created by 86139 on 2019-11-20 17:08:52
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.LinearLayout

import com.deepblue.aidevicemanager.R

import android.widget.TextView
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.item.DialogSet
import com.deepblue.aidevicemanager.model.ModelDeviceDetail
import com.deepblue.aidevicemanager.util.DesEncryptDecrypt
import kotlinx.android.synthetic.main.frg_detail_lx.*
import kotlinx.android.synthetic.main.frg_detail_lx.mTextView
import kotlinx.android.synthetic.main.frg_login.*
import kotlinx.android.synthetic.main.item_head.view.*
import timber.log.Timber


class FrgDetailLx : BaseFrg() {
    lateinit var type: String
    lateinit var mModelDeviceDetail: ModelDeviceDetail

    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_detail_lx)

        type = activity?.intent?.getStringExtra("type") ?: ""
        mModelDeviceDetail =
                activity?.intent?.getSerializableExtra("mModelDeviceDetail") as ModelDeviceDetail
    }

    override fun initView() {
        if (type.equals("1")) {
            mButton.visibility = View.VISIBLE
        }
        mTextView.text = mModelDeviceDetail.deviceCode
        mButton.setOnClickListener {
            mButton.visibility = View.GONE
            mProgressBar.visibility = View.VISIBLE

        }
    }


    override fun loaddata() {

    }

    override fun onSuccess(data: String?, method: String) {

    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        if (type.equals("1")) {
            mHead.setShowPop(DialogSet(context))
            mHead.mImageView.setBackgroundResource(R.drawable.u1844)
            mHead.mTextView_d_status.text = getString(R.string.d_connect)
        } else {
            mHead.setShowPop(DialogSet(context))//fix
            mHead.mImageView.setBackgroundResource(R.drawable.lian)
            mHead.mTextView_d_status.text = getString(R.string.d_no_connect)
        }
        mHead.mLinearLayout_status.visibility = View.VISIBLE
    }
}