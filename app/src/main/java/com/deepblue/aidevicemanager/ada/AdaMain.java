//
//  AdaMain
//
//  Created by 86139 on 2019-11-22 15:09:45
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.ada;

import java.util.List;

import com.deepblue.aidevicemanager.frg.FrgDeviceMain;
import com.deepblue.aidevicemanager.model.ModelMain;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.deepblue.aidevicemanager.item.Main;
import com.mdx.framework.util.Helper;

public class AdaMain extends MAdapter<ModelMain> {

    public AdaMain(Context context, List<ModelMain> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelMain item = get(position);
        if (convertView == null) {
            convertView = new Main(getContext());
        }
        Main mMain = (Main) convertView;
        mMain.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgDeviceMain.class, TitleAct.class, "item", item);
            }
        });
        return convertView;
    }
}
