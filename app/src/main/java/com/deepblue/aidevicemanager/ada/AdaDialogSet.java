//
//  AdaDialogSet
//
//  Created by 86139 on 2019-11-21 10:03:46
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.deepblue.aidevicemanager.item.DialogSet;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaDialogSet extends MAdapter<String>{

   public AdaDialogSet(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
//            convertView =new DialogSet (getContext() );
        }
        DialogSet mDialogSet=(DialogSet) convertView ;
//        mDialogSet.set(item);
        return convertView;
    }
}
