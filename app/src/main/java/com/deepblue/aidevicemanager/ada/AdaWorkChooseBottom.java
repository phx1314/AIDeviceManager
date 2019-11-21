//
//  AdaWorkChooseBottom
//
//  Created by 86139 on 2019-11-21 09:34:19
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

import com.deepblue.aidevicemanager.item.WorkChooseBottom;

public class AdaWorkChooseBottom extends MAdapter<String> {

    public AdaWorkChooseBottom(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new WorkChooseBottom(getContext());
        }
        WorkChooseBottom mWorkChooseBottom = (WorkChooseBottom) convertView;
        mWorkChooseBottom.set(item);
        return convertView;
    }
}
