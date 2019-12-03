//
//  AdaCgqManage
//
//  Created by 86139 on 2019-11-21 13:25:58
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.deepblue.aidevicemanager.item.Xx
import com.deepblue.aidevicemanager.model.ModelBrokenXx
import com.deepblue.aidevicemanager.model.ModelTaskXx
import com.mdx.framework.Frame
import com.mdx.framework.adapter.MAdapter

class AdaXx(context: Context?, list: List<Any>) : MAdapter<Any>(context, list) {


    override fun getview(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val item = get(position)
        if (convertView == null) {
            convertView = Xx(context)
        }
        val mCgqManage = convertView as Xx?
        mCgqManage!!.set(item)
        convertView.setOnClickListener {
            if (item is ModelTaskXx.PageInfoBean.RowsBean) {
                if (item.isRead.equals("0"))
                    Frame.HANDLES.sentAll("FrgXx", 3, item.id.toString())
            } else if (item is ModelBrokenXx.PageInfoBean.RowsBean) {
                if (item.isRead.equals("0"))
                    Frame.HANDLES.sentAll("FrgXx", 4, item.breakdownId.toString())
            }
//            this@AdaXx.notifyDataSetChanged()
        }
        return convertView
    }
}
