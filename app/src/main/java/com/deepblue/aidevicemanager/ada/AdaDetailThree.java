//
//  AdaDetailTwo
//
//  Created by 86139 on 2019-11-20 19:06:06
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.deepblue.aidevicemanager.item.DetailThree;
import com.deepblue.aidevicemanager.item.DetailTwo;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaDetailThree extends MAdapter<String> {

    public AdaDetailThree(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new DetailTwo(getContext());
        }
        DetailThree mDetailThree = (DetailThree) convertView;
        mDetailThree.set(item);
        return convertView;
    }
}
