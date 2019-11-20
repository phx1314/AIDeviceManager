//
//  FrgDetailLx
//
//  Created by 86139 on 2019-11-20 17:08:52
//  Copyright (c) 86139 All rights reserved.


/**
   
*/

package com.deepblue.aidevicemanager.frg;
import android.os.Bundle;

import com.deepblue.aidevicemanager.R;

import android.widget.TextView;



public class FrgDetailLx extends BaseFrg{

    public TextView mTextView;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_detail_lx);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mTextView=(TextView)findViewById(R.id.mTextView);


    }
    
    public void loaddata(){

    }
    
   
 
}