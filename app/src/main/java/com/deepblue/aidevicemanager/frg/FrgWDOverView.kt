package com.deepblue.aidevicemanager.frg

import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelA
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.model.ModelB_CleanPealPosition
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frg_wd_overview.*

class FrgWDOverView : BaseFrg() {
    private val mMap by lazy { baidumap_overview.map }
    private val mBitmapCompass by lazy {
        BitmapFactory.decodeResource(
            context?.resources,
            R.drawable.u2262
        )
    }

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_wd_overview)
    }

    override fun initView() {
        iv_overview_zoom.setOnClickListener(this)
        val builder = MapStatus.Builder()
        builder.zoom(15.0f).target(LatLng(31.8233, 120.015008)).overlook(-10F)
        mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))

        mMap.mapType = BaiduMap.MAP_TYPE_NORMAL //地图普通
//        mMap.isTrafficEnabled = true  //交通
        mMap.isMyLocationEnabled = true  //是否显示定位点
        mMap.setMyLocationConfiguration( //定位点方向
            MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, //定位模式
                true,
                null
            )
        )

        mMap.compassPosition = Point(50, 50) //指南针位置
        mMap.setCompassIcon(mBitmapCompass)  //自定义指南针图标
        baidumap_overview.logoPosition = LogoPosition.logoPostionRightBottom //logo位置
        baidumap_overview.showScaleControl(false) //比例尺按钮隐藏
        baidumap_overview.showZoomControls(false) //缩放按钮隐藏
    }

    override fun loaddata() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_overview_zoom -> mMap.setMapStatus(MapStatusUpdateFactory.zoomIn())
        }
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            1111 -> {
                try {
                    val a = Gson().fromJson(obj.toString(), ModelA::class.java)
                    F.mModelStatus?.mModelB = a.cleanKingLiveStatus
                    val mCleanPealPosition = Gson().fromJson(a.cleanAppRealPosition, ModelB_CleanPealPosition::class.java)
                    val msu = MapStatusUpdateFactory.newLatLng(LatLng(mCleanPealPosition.lati, mCleanPealPosition.longti))
                    mMap.setMapStatus(msu)

                    val locData = MyLocationData.Builder()
                        .accuracy(0F)
                        .direction(0F)
                        .latitude(mCleanPealPosition.lati).longitude(mCleanPealPosition.longti).build()
                    mMap.setMyLocationData(locData)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}