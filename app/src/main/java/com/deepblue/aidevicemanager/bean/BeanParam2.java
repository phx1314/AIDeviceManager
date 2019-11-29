package com.deepblue.aidevicemanager.bean;

import cn.qqtheme.framework.picker.TimePicker;

/**
 * Created by DELL on 2019/10/16.
 */

public class BeanParam2 {
    public String rpParamId = "";
    public String rpParamValue = "";
    public String rpParamMin;
    public String rpParamMax;

    public BeanParam2(String rpParamId, String rpParamValue, String rpParamMin, String rpParamMax) {
        this.rpParamId = rpParamId;
        this.rpParamValue = rpParamValue;
        this.rpParamMin = rpParamMin;
        this.rpParamMax = rpParamMax;


    }
}
