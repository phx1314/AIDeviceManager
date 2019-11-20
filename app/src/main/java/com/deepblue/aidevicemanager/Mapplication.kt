package com.deepblue.aidevicemanager

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.mdx.framework.util.CrashReportingTree

import com.mdx.framework.util.Frame
import timber.log.Timber

class Mapplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        Frame.init(applicationContext)
        initBaiduSDK()
    }

    private fun initBaiduSDK() {
        SDKInitializer.initialize(this)
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
