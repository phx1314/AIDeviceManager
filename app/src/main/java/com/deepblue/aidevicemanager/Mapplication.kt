package com.deepblue.aidevicemanager

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import cn.jpush.android.api.JPushInterface
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.mdx.framework.Frame
import com.tencent.bugly.crashreport.CrashReport


class Mapplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Frame.init(applicationContext)
        initBaiduSDK()
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        CrashReport.initCrashReport(applicationContext, "e50df62564", false);
    }

    private fun initBaiduSDK() {
        SDKInitializer.initialize(this)
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
