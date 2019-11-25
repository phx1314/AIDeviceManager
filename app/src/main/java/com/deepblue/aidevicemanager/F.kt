package com.deepblue.aidevicemanager

import android.preference.PreferenceManager
import com.deepblue.aidevicemanager.model.ModelLogin
import com.deepblue.aidevicemanager.service.ApiService
import com.google.gson.Gson
import com.mdx.framework.Frame

object F {
    var mModellogin: ModelLogin? = null
    val baseUrl = "http://192.168.113.47:8081/robotos/cleanApp/"
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

}
