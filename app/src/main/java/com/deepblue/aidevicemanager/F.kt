package com.deepblue.aidevicemanager

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import com.deepblue.aidevicemanager.frg.FrgLogin
import com.deepblue.aidevicemanager.model.ModelLogin
import com.deepblue.aidevicemanager.service.ApiService
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.mdx.framework.Frame
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.util.Helper

object F {
    var mModellogin: ModelLogin? = null
    val baseUrl = "http://192.168.110.204:8081/robotos/cleanApp/"
    fun gB() =
            com.mdx.framework.service.gB(
                    ApiService::class.java,
                    baseUrl,
                    mModellogin?.token
            )


    fun init() {
        mModellogin = Gson().fromJson(getJson("mModellogin"), ModelLogin::class.java)

    }

    fun <T> data2Model(data: String?, mclass: Class<T>): T {
        return Gson().fromJson(data, mclass)
    }


    fun getJson(key: String): String? {
        val sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT)
        return sp.getString(key, "")
    }

    fun saveJson(key: String, json: String?) {
        val sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT)
        sp.edit().putString(key, json).apply()

    }

    fun logOut(context: Context?, isShow: Boolean = true) {
        if (isShow) Helper.toast(context?.resources?.getString(R.string.i_login))
        saveJson("mModellogin", "")
        mModellogin = null
        Helper.startActivity(context, Intent.FLAG_ACTIVITY_CLEAR_TOP, FrgLogin::class.java, IndexAct::class.java)
    }

}
