package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.util.GlideLoader
import com.deepblue.aidevicemanager.ws.WsStatus
import com.google.gson.Gson
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_wd_laser.*
import kotlinx.android.synthetic.main.frg_workdetail.*

class FrgWDLaser : BaseFrg() {
    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_wd_laser)
    }

    override fun initView() {
        iv_wd_laser.setImageResource(R.mipmap.picloading)
    }

    override fun loaddata() {
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            1111 -> {
//                Helper.toast("实时数据：${obj.toString()}")
                try {
                    F.mModelStatus?.mModelB = Gson().fromJson(obj.toString(), ModelB::class.java)
                    GlideLoader.loadImage_error(F.mModelStatus?.mModelB?.pointYunPic, iv_wd_laser, R.mipmap.picloading, R.mipmap.picerror)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}