//
//  AdaCgqManage
//
//  Created by 86139 on 2019-11-21 13:25:58
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.deepblue.aidevicemanager.item.CgqManage;
import com.deepblue.aidevicemanager.item.Xx;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaXx extends MAdapter<String> {

    public AdaXx(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new Xx(getContext());
        }
        Xx mCgqManage = (Xx) convertView;
        mCgqManage.set(item);
        return convertView;
    }
}
