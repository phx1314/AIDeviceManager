//
//  BaseItem
//
//  Created by 86139 on 2019-11-20 15:47:07
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.item;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseItem implements OnClickListener {
	protected Context context;
	protected View contentview;

	@Override
	public void onClick(View v) {

	}

	public View findViewById(int id) {
         return this.contentview.findViewById(id);
    }

}

