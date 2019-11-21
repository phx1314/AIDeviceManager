//
//  AdaCgqManageSon
//
//  Created by 86139 on 2019-11-21 13:27:24
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

import com.deepblue.aidevicemanager.item.CgqManageSon;

public class AdaCgqManageSon extends MAdapter<String> {

    public AdaCgqManageSon(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new CgqManageSon(getContext());
        }
        CgqManageSon mCgqManageSon = (CgqManageSon) convertView;
        mCgqManageSon.set(item);
        return convertView;
    }
}
