package com.deepblue.aidevicemanager.bean;

/**
 * Created by DELL on 2019/10/16.
 */

public class BeanLogin {
    public String userName = "";
    public String messageCode = "";
    public String password = "";

    public BeanLogin(String userName, String messageCode, String password) {
        this.userName = userName;
        this.messageCode = messageCode;
        this.password = password;
    }
}
