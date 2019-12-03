package com.deepblue.aidevicemanager

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.preference.PreferenceManager
import android.telephony.TelephonyManager
import cn.jpush.android.api.JPushInterface
import com.deepblue.aidevicemanager.frg.FrgLogin
import com.deepblue.aidevicemanager.model.ModelLogin
import com.deepblue.aidevicemanager.model.ModelStatus
import com.deepblue.aidevicemanager.service.ApiService
import com.deepblue.aidevicemanager.ws.WsManager
import com.google.gson.Gson
import com.mdx.framework.Frame
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.util.Helper
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object F {
    var wsManager: WsManager? = null
    var mModellogin: ModelLogin? = null
    var mModelStatus: ModelStatus? = null
    val wsBaseUrl = ""
    //    val baseUrl = "http://192.168.123.209:8081/robotos/cleanApp/"
//    val baseUrl = "http://10.1.1.160:8081/robotos/cleanApp/" //测试
    val baseUrl = "http://192.168.16.91:8081/robotos/cleanApp/"//开发

    fun gB(TIME: Long = 30) =
        com.mdx.framework.service.gB(ApiService::class.java, baseUrl, mModellogin?.token, TIME)

    fun init() {
        mModellogin = Gson().fromJson(getJson("mModellogin"), ModelLogin::class.java)
    }

    fun <T> data2Model(data: String?, mclass: Class<T>): T {
        return Gson().fromJson(data, mclass)
    }

    fun getJson(key: String): String? {
        val sp = PreferenceManager.getDefaultSharedPreferences(Frame.CONTEXT)
        return sp.getString(key, "")
    }

    fun saveJson(key: String, json: String?) {
        val sp = PreferenceManager.getDefaultSharedPreferences(Frame.CONTEXT)
        sp.edit().putString(key, json).apply()

    }

    fun logOut(context: Context?, isShow: Boolean = true) {
        if (isShow) Helper.toast(context?.resources?.getString(R.string.i_login))
        JPushInterface.stopPush(context);
        saveJson("mModellogin", "")
        mModellogin = null
        Helper.startActivity(
            context,
            Intent.FLAG_ACTIVITY_CLEAR_TOP,
            FrgLogin::class.java,
            IndexAct::class.java
        )
    }

    fun isWifiConnect(context: Context): Boolean {
        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mWifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return mWifiInfo.isConnected
    }

    fun checkWifiState(context: Context): Int {
        var wifiLevel = -1
        if (isWifiConnect(context)) {
            val mWifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val mWifiInfo = mWifiManager.connectionInfo
            val wifi = mWifiInfo.rssi//获取wifi信号强度
            if (wifi > -50 && wifi < 0) {//最强
                wifiLevel = 4
            } else if (wifi > -70 && wifi < -50) {//较强
                wifiLevel = 3
            } else if (wifi > -80 && wifi < -70) {//较弱
                wifiLevel = 2
            } else if (wifi > -100 && wifi < -80) {//微弱
                wifiLevel = 1
            } else {
                wifiLevel = 0
            }
            return wifiLevel
        } else {
            wifiLevel = -1
            return wifiLevel
            //无连接
        }
    }

    /**
     * 得到当前的手机蜂窝网络信号强度
     * 获取LTE网络和3G/2G网络的信号强度的方式有一点不同，
     * LTE网络强度是通过解析字符串获取的，
     * 3G/2G网络信号强度是通过API接口函数完成的。
     * asu 与 dbm 之间的换算关系是 dbm=-113 + 2*asu
     */
    fun getCurrentNetDBM(context: Context) {

    }

    /**
     * 判断是否包含SIM卡
     *
     * @return 状态
     */
    fun ishasSimCard(context: Context): Boolean {
        val telMgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val simState = telMgr.simState
        var result = true
        when (simState) {
            TelephonyManager.SIM_STATE_ABSENT -> result = false // 没有SIM卡
            TelephonyManager.SIM_STATE_UNKNOWN -> result = false
        }
        return result
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    fun isOPenGPS(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        val network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        return if (gps || network) {
            true
        } else false
    }


    fun stopConnectWSocket() {
        if (wsManager != null && wsManager!!.isWSConnected())
            wsManager!!.stopConnect()
    }

    fun connectWSocket(context: Context, url: String) {
        if (wsManager == null) {
            wsManager = WsManager.Builder()
                .context(context)
                .wsUrl(url)
                .client(
                    OkHttpClient().newBuilder()
                        .pingInterval(10, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(15, TimeUnit.SECONDS)//设置写的超时时间
                        .connectTimeout(15, TimeUnit.SECONDS)//设置连接超时时间
                        .retryOnConnectionFailure(true)
                        .build()
                )
                .needReconnect(true)
                .build()
        } else {
            wsManager?.wsUrl = url
            stopConnectWSocket()
        }
        wsManager?.startConnect()
    }
}
