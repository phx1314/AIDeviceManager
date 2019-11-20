//
//  CardEpOne
//
//  Created by 86139 on 2019-11-20 15:47:07
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.card;

import android.content.Context;
import com.mdx.framework.adapter.Card;
import android.view.View;

import com.deepblue.aidevicemanager.item.EpOne;

public class CardEpOne extends Card<String>{
	public String item;
	
	public CardEpOne() {
    	this.type = com.deepblue.aidevicemanager.commons.CardIDS.CARDID_EPONE;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = EpOne.Companion.getView(context, null);;
        }
        EpOne mEpOne=(EpOne) contentView.getTag();
        mEpOne.set(item);
        return contentView;
    }
    
    

}


