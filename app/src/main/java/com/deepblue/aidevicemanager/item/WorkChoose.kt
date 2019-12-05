//
//  WorkChooseBottom
//
//  Created by 86139 on 2019-11-21 09:34:19
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaWorkChoose
import com.deepblue.aidevicemanager.frg.selectID
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelMapLj
import com.deepblue.aidevicemanager.util.GlideLoader
import com.mdx.framework.Frame
import kotlinx.android.synthetic.main.item_work_choose.view.*


class WorkChoose(context: Context?) : LinearLayout(context) {
    lateinit var item: ModelData<ModelMapLj.RowsBean>
    lateinit var mAdaWorkChoose: AdaWorkChoose

    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_work_choose, this)

        mImageView1.setOnClickListener {
            selectID = item.mList[0].id
            Frame.HANDLES.sentAll("FrgWorkChoose", 1, item.mList[0])
            mAdaWorkChoose.notifyDataSetChanged()
        }
        mImageView2.setOnClickListener {
            selectID = item.mList[1].id
            Frame.HANDLES.sentAll("FrgWorkChoose", 1, item.mList[1])
            mAdaWorkChoose.notifyDataSetChanged()
        }
        mImageView3.setOnClickListener {
            selectID = item.mList[2].id
            Frame.HANDLES.sentAll("FrgWorkChoose", 1, item.mList[2])
            mAdaWorkChoose.notifyDataSetChanged()
        }
    }

    fun set(item: ModelData<ModelMapLj.RowsBean>, mAdaWorkChoose: AdaWorkChoose) {
        this.item = item
        this.mAdaWorkChoose = mAdaWorkChoose
        setShow(item.mList.size)
        item.mList.forEachIndexed { i, it ->
            if (i == 0) {
                setShowChoose(it.id, mImageView1)
                GlideLoader.loadImage(it.pathPicUrl, mImageView1, R.drawable.u2303_mouse)
            } else if (i == 1) {
                setShowChoose(it.id, mImageView2)
                GlideLoader.loadImage(it.pathPicUrl, mImageView2, R.drawable.u2303_mouse)
            } else if (i == 2) {
                setShowChoose(it.id, mImageView3)
                GlideLoader.loadImage(it.pathPicUrl, mImageView3, R.drawable.u2303_mouse)
            }
        }
    }

    fun setShow(count: Int) {
        mImageView1.visibility = if (count > 0) View.VISIBLE else View.INVISIBLE
        mImageView2.visibility = if (count > 1) View.VISIBLE else View.INVISIBLE
        mImageView3.visibility = if (count > 2) View.VISIBLE else View.INVISIBLE
    }

    fun setShowChoose(id: Int, mImageView: ImageView) {
        if (selectID == id) {
            mImageView.setBackgroundResource(R.drawable.shape_k)
        } else {
            mImageView.setBackgroundResource(0)
        }
    }
}