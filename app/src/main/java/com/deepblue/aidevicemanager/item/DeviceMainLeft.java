//
//  DeviceMainLeft
//
//  Created by 86139 on 2019-11-20 17:00:09
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.item;

import com.deepblue.aidevicemanager.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.view.View;
import android.widget.TextView;



public class DeviceMainLeft extends BaseItem{
    public TextView mTextView;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_device_main_left,null);
	     convertView.setTag( new DeviceMainLeft(convertView));
	     return convertView;
	}

	public DeviceMainLeft(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mTextView=(TextView)contentview.findViewById(R.id.mTextView);


    }

    public void set(String item){

    }
    
    

}