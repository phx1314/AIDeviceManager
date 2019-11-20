//
//  CardDeviceMainRight
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.deepblue.aidevicemanager.item.DeviceMainRight;

public class CardDeviceMainRight extends Card<String>{
	public String item;
	
	public CardDeviceMainRight() {
    	this.type = com.deepblue.aidevicemanager.commons.CardIDS.CARDID_DEVICEMAINRIGHT;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = DeviceMainRight.getView(context, null);;
        }
        DeviceMainRight mDeviceMainRight=(DeviceMainRight) contentView.getTag();
        mDeviceMainRight.set(item);
        return contentView;
    }
    
    

}


