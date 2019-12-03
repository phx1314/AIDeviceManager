//
//  FrgLogin
//
//  Created by DELL on 2019-10-15 13:14:05
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.view.View
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaXx
import com.deepblue.aidevicemanager.model.ModelBrokenXx
import com.deepblue.aidevicemanager.model.ModelTaskXx
import com.mdx.framework.Frame
import kotlinx.android.synthetic.main.frg_xx.*


class FrgXx : BaseFrg() {
    var type: Int = 0
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_xx)
    }

    override fun initView() {
        type = arguments?.getInt("type") ?: 0
        chageType(type)

        mLinearLayout_1.setOnClickListener { chageType(0) }
        mLinearLayout_2.setOnClickListener { chageType(1) }
        mLinearLayout_3.setOnClickListener { chageType(2) }
        mTextView_1.text = "${getString(R.string.d_task_xx) + "(" + arguments?.getInt("count1")})"
        mTextView_2.text = "${getString(R.string.d_waring_xx) + "(" + arguments?.getInt("count2")})"
        mTextView_3.text = "${getString(R.string.d_broken_xx) + "(" + arguments?.getInt("count3")})"

    }

    override fun disposeMsg(type: Int, obj: Any?) {
        when (type) {
            3 -> {
                load(F.gB().updateTaskIsRead(obj.toString(), "1"), "updateTaskIsRead")
            }
            4 -> {
                load(F.gB().updateBreakdownIsRead(obj.toString(), "1"), "updateTaskIsRead")
            }
        }


    }

    fun chageType(type: Int) {
        this.type = type
        when (type) {
            0 -> {
                mImageView_1.visibility = View.VISIBLE
                mImageView_2.visibility = View.INVISIBLE
                mImageView_3.visibility = View.INVISIBLE
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}task/queryTaskListWithPage",
                    "POST",
                    this,
                    F.mModellogin?.token
                )
            }
            1 -> {
                mImageView_1.visibility = View.INVISIBLE
                mImageView_2.visibility = View.VISIBLE
                mImageView_3.visibility = View.INVISIBLE
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}task/queryAlarmBreakdowns",
                    "POST",
                    this,
                    F.mModellogin?.token
                )
            }
            2 -> {
                mImageView_1.visibility = View.INVISIBLE
                mImageView_2.visibility = View.INVISIBLE
                mImageView_3.visibility = View.VISIBLE
                mAbPullListView.setApiLoadParams(
                    "${F.baseUrl}task/queryBreakdowns",
                    "POST",
                    this,
                    F.mModellogin?.token
                )
            }

        }
    }

    override fun loaddata() {
        mAbPullListView.setAbOnListViewListener { _, content ->
            when (type) {
                0 -> {
                    var mModelTaskXx = F.data2Model(content, ModelTaskXx::class.java)
                    mTextView_1.text =
                        "${getString(R.string.d_task_xx) + "(" + mModelTaskXx.noReadCount.toInt()})"
                    Frame.HANDLES.sentAll("FrgMain", 2, mModelTaskXx.noReadCount.toInt())
                    AdaXx(context, mModelTaskXx.rows)
                }
                1 -> {
                    var mModelWaringXx = F.data2Model(content, ModelBrokenXx::class.java)
                    mTextView_2.text =
                        "${getString(R.string.d_waring_xx) + "(" + mModelWaringXx.noReadCount.toInt()})"
                    Frame.HANDLES.sentAll("FrgMain", 3, mModelWaringXx.noReadCount.toInt())
                    AdaXx(context, mModelWaringXx.pageInfo.rows)
                }
                2 -> {
                    var mModelBrokenXx = F.data2Model(content, ModelBrokenXx::class.java)
                    mTextView_3.text =
                        "${getString(R.string.d_broken_xx) + "(" + mModelBrokenXx.noReadCount.toInt()})"
                    Frame.HANDLES.sentAll("FrgMain", 4, mModelBrokenXx.noReadCount.toInt())
                    AdaXx(context, mModelBrokenXx.pageInfo.rows)
                }
                else -> {
                    AdaXx(context, arrayListOf())
                }
            }
        }
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("updateTaskIsRead")) {

        }
    }
}