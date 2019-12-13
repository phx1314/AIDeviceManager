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

import com.deepblue.aidevicemanager.item.CgqManageSon2;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaCgqManageSon2 extends MAdapter<String> {

    public AdaCgqManageSon2(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new CgqManageSon2(getContext());
        }
        CgqManageSon2 mCgqManageSon = (CgqManageSon2) convertView;
        mCgqManageSon.set(item);
        return convertView;
    }
}
