//
//  AdaWorkChooseBottom
//
//  Created by 86139 on 2019-11-21 09:34:19
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.deepblue.aidevicemanager.item.WorkChoose
import com.deepblue.aidevicemanager.model.ModelData
import com.deepblue.aidevicemanager.model.ModelMapLj
import com.mdx.framework.adapter.MAdapter

class AdaWorkChoose(context: Context?, list: List<ModelData<ModelMapLj.RowsBean>>) :
        MAdapter<ModelData<ModelMapLj.RowsBean>>(context, list) {

    override fun getview(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val item = get(position)
        if (convertView == null) {
            convertView = WorkChoose(context)
        }
        val mWorkChooseBottom = convertView as WorkChoose?
        mWorkChooseBottom!!.set(item,this)
        return convertView
    }
}
