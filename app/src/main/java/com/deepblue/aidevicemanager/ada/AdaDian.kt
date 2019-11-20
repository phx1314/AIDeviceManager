//
//  AdaDian
//
//  Created by DELL on 2019-10-15 13:05:56
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.deepblue.aidevicemanager.item.Dian

import com.mdx.framework.adapter.MAdapter

class AdaDian(context: Context, list: List<String>) : MAdapter<String>(context, list) {


    override fun getview(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val item = get(position)
        if (convertView == null) {
            convertView = Dian(context)
        }
        (convertView as Dian).set(item)
        return convertView
    }
}
