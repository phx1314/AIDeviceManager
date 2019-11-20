//
//  AdaDeviceMainRight
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

import com.deepblue.aidevicemanager.item.DeviceMainRight;

public class AdaDeviceMainRight extends MAdapter<String>{

   public AdaDeviceMainRight(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = DeviceMainRight.getView(getContext(), parent);;
        }
        DeviceMainRight mDeviceMainRight=(DeviceMainRight) convertView.getTag();
        mDeviceMainRight.set(item);
        return convertView;
    }
}
