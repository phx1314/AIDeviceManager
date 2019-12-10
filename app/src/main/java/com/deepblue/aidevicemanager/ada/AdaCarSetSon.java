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

import com.deepblue.aidevicemanager.item.CarSetSon;
import com.deepblue.aidevicemanager.model.ModelCarSet;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaCarSetSon extends MAdapter<ModelCarSet> {

    public AdaCarSetSon(Context context, List<ModelCarSet> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelCarSet item = get(position);
        if (convertView == null) {
            convertView = new CarSetSon(getContext());
        }
        CarSetSon mDetailTwo = (CarSetSon) convertView;
        mDetailTwo.set(item);

        return convertView;
    }
}
