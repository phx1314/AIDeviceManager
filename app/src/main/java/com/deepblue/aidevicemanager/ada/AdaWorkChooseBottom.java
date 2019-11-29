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

import com.deepblue.aidevicemanager.model.ModelMap;
import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.deepblue.aidevicemanager.item.WorkChooseBottom;

public class AdaWorkChooseBottom extends MAdapter<ModelMap.RowsBean> {

    public AdaWorkChooseBottom(Context context, List<ModelMap.RowsBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelMap.RowsBean item = get(position);
        if (convertView == null) {
            convertView = new WorkChooseBottom(getContext());
        }
        WorkChooseBottom mWorkChooseBottom = (WorkChooseBottom) convertView;
        mWorkChooseBottom.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frame.HANDLES.sentAll("FrgWorkChoose", 0, (int) item.id);
            }
        });
        return convertView;
    }
}
