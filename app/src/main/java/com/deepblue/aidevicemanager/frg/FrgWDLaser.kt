package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelWSRealTimeData
import com.deepblue.aidevicemanager.util.GlideLoader
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frg_wd_laser.*
import java.lang.Exception

class FrgWDLaser : BaseFrg() {
    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_wd_laser)
    }

    override fun initView() {
    }

    override fun loaddata() {
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            1111 -> {
//                Helper.toast("实时数据：${obj.toString()}")
                try {
                    val a = Gson().fromJson(obj.toString(), ModelWSRealTimeData::class.java)
                    GlideLoader.loadImage_error(a.pointYunPic, iv_wd_laser)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}