//
//  AdaDetailTwo
//
//  Created by 86139 on 2019-11-20 19:06:06
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.deepblue.aidevicemanager.item.DetailTwo;

public class AdaDetailTwo extends MAdapter<String>{

   public AdaDetailTwo(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
//        if (convertView == null) {
//            convertView = DetailTwo.Companion.getView(getContext(), parent);;
//        }
//        DetailTwo mDetailTwo=(DetailTwo) convertView.getTag();
//        mDetailTwo.set(item);
        return convertView;
    }
}
