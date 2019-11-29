//
//  AdaDeviceMainLeft
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada;

import java.util.List;

import com.deepblue.aidevicemanager.model.ModelModels;
import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.deepblue.aidevicemanager.item.DeviceMainLeft;

public class AdaDeviceMainLeft extends MAdapter<ModelModels> {

    public AdaDeviceMainLeft(Context context, List<ModelModels> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelModels item = get(position);
        if (convertView == null) {
            convertView = new DeviceMainLeft(getContext());
        }
        DeviceMainLeft mDeviceMainLeft = (DeviceMainLeft) convertView;
        mDeviceMainLeft.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frame.HANDLES.sentAll("FrgDeviceMain", 0, item);
            }
        });
        return convertView;
    }
}
