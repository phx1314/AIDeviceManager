//
//  AdaDeviceMainLeft
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.deepblue.aidevicemanager.item.DeviceMainLeft;

public class AdaDeviceMainLeft extends MAdapter<String>{

   public AdaDeviceMainLeft(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = DeviceMainLeft.getView(getContext(), parent);;
        }
        DeviceMainLeft mDeviceMainLeft=(DeviceMainLeft) convertView.getTag();
        mDeviceMainLeft.set(item);
        return convertView;
    }
}
