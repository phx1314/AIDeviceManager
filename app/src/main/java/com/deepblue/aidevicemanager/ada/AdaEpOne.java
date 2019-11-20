//
//  AdaEpOne
//
//  Created by 86139 on 2019-11-20 15:47:07
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.deepblue.aidevicemanager.item.EpOne;

public class AdaEpOne extends MAdapter<String>{

   public AdaEpOne(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = EpOne.Companion.getView(getContext(), parent);;
        }
        EpOne mEpOne=(EpOne) convertView.getTag();
        mEpOne.set(item);
        return convertView;
    }
}
