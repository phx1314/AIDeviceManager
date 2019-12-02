//
//  AdaDeviceMainRight
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.deepblue.aidevicemanager.item.DeviceMainRight;
import com.deepblue.aidevicemanager.model.ModelData;
import com.deepblue.aidevicemanager.model.ModelDevices;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaDeviceMainRight extends MAdapter<ModelData<ModelDevices.RowsBean>> {

    public AdaDeviceMainRight(Context context, List<ModelData<ModelDevices.RowsBean>> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelData<ModelDevices.RowsBean> item = get(position);
        if (convertView == null) {
            convertView = new DeviceMainRight(getContext());
        }
        DeviceMainRight mDeviceMainRight = (DeviceMainRight) convertView;
        mDeviceMainRight.set(item);
        return convertView;
    }
}
