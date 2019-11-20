//
//  CardDeviceMainLeft
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.deepblue.aidevicemanager.item.DeviceMainLeft;

public class CardDeviceMainLeft extends Card<String>{
	public String item;
	
	public CardDeviceMainLeft() {
    	this.type = com.deepblue.aidevicemanager.commons.CardIDS.CARDID_DEVICEMAINLEFT;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = DeviceMainLeft.getView(context, null);;
        }
        DeviceMainLeft mDeviceMainLeft=(DeviceMainLeft) contentView.getTag();
        mDeviceMainLeft.set(item);
        return contentView;
    }
    
    

}


