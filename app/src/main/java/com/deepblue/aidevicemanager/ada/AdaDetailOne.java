//
//  AdaDetailOne
//
//  Created by 86139 on 2019-11-20 19:06:06
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

import com.deepblue.aidevicemanager.item.DetailOne;

public class AdaDetailOne extends MAdapter<String> {

    public AdaDetailOne(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new DetailOne(getContext());
        }
        DetailOne mDetailOne = (DetailOne) convertView;
        mDetailOne.set(item);
        return convertView;
    }
}
