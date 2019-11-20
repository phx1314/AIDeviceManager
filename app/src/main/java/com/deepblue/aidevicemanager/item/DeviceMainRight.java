//
//  DeviceMainRight
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
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;



public class DeviceMainRight extends BaseItem{
    public LinearLayout mLinearLayout;
    public ImageView mImageView_slw;
    public ImageView mImageView_status;
    public TextView mTextView_num;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_device_main_right,null);
	     convertView.setTag( new DeviceMainRight(convertView));
	     return convertView;
	}

	public DeviceMainRight(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mLinearLayout=(LinearLayout)contentview.findViewById(R.id.mLinearLayout);
        mImageView_slw=(ImageView)contentview.findViewById(R.id.mImageView_slw);
        mImageView_status=(ImageView)contentview.findViewById(R.id.mImageView_status);
        mTextView_num=(TextView)contentview.findViewById(R.id.mTextView_num);


    }

    public void set(String item){

    }
    
    

}