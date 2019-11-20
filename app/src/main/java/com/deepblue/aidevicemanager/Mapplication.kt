package com.deepblue.aidevicemanager

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.mdx.framework.util.CrashReportingTree

import com.mdx.framework.util.Frame
import timber.log.Timber


/**
 * Created by bob on 2015/1/30.
 */
class Mapplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        Frame.init(applicationContext)
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
