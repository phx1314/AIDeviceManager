//
//  AdaCgqManage
//
//  Created by 86139 on 2019-11-21 13:25:58
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.deepblue.aidevicemanager.item.CgqManage;

public class AdaCgqManage extends MAdapter<String> {

    public AdaCgqManage(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new CgqManage(getContext());
        }
        CgqManage mCgqManage = (CgqManage) convertView;
//        mCgqManage.set(item);
        return convertView;
    }
}
