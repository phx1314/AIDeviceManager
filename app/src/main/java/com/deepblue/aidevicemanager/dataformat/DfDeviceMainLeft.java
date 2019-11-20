//
//  DfDeviceMainLeft
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.dataformat;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import android.content.Context;
import com.deepblue.aidevicemanager.ada.AdaDeviceMainLeft;

public class DfDeviceMainLeft implements DataFormat{
    int size = 1;

	@Override
	public boolean hasNext() {
		return size >= Integer.MAX_VALUE;
	}

	@Override
	public MAdapter<?> getAdapter(Context context, Son son, int page) {
		return new AdaDeviceMainLeft(context,null);
	}

	@Override
	public String[][] getPageNext() {
		return null;
	}

	@Override
	public void reload() {

	}
}
