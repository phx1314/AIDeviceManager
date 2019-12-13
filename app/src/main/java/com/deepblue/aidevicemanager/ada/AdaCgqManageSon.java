//
//  AdaCgqManageSon
//
//  Created by 86139 on 2019-11-21 13:27:24
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.deepblue.aidevicemanager.item.CgqManageSon;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaCgqManageSon extends MAdapter<Character> {
    public String start = "";

    public AdaCgqManageSon(Context context, List<Character> list, String start) {
        super(context, list);
        this.start = start;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        Character item = get(position);
        if (convertView == null) {
            convertView = new CgqManageSon(getContext());
        }
        CgqManageSon mCgqManageSon = (CgqManageSon) convertView;
        mCgqManageSon.set(item, start,position);
        return convertView;
    }
}
