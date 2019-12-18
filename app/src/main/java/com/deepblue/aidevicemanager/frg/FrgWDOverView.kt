package com.deepblue.aidevicemanager.frg

import android.graphics.Point
import android.os.Bundle
import android.view.View
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelA
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frg_wd_overview.*

class FrgWDOverView : BaseFrg() {
    private val mMap by lazy { baidumap_overview.map }
    private var locData: MyLocationData? = null
//    private val mBitmapCompass by lazy {
//        BitmapFactory.decodeResource(
//            context?.resources,
//            R.drawable.u2262
//        )
//    }

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_wd_overview)
    }

    override fun initView() {
        iv_overview_zoom.setOnClickListener(this)
        val builder = MapStatus.Builder()
        builder.zoom(15.0f).target(
            LatLng(
                FrgWorkDetail.mModelDeviceDetail?.cleanKingLiveStatus?.data_latitude?.toDouble() ?: 31.219703,
                FrgWorkDetail.mModelDeviceDetail?.cleanKingLiveStatus?.data_longitude?.toDouble() ?: 121.391131
            )
        ).overlook(-10F)
        mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
        locData = MyLocationData.Builder()
            .accuracy(0F)
            .latitude(FrgWorkDetail.mModelDeviceDetail?.cleanKingLiveStatus?.data_latitude?.toDouble() ?: 31.219703)
            .longitude(FrgWorkDetail.mModelDeviceDetail?.cleanKingLiveStatus?.data_longitude?.toDouble() ?: 121.391131).build()
        mMap.setMyLocationData(locData)

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
//        mMap.setCompassIcon(mBitmapCompass)  //自定义指南针图标
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
                    val desBaiduLatLng = F.getDesBaiduLatLng(F.mModelStatus?.mModelB?.data_latitude?.toDouble()!!, F.mModelStatus?.mModelB?.data_longitude?.toDouble()!!)
                    val msu = MapStatusUpdateFactory.newLatLng(
                        LatLng(
                            desBaiduLatLng.latitude,
                            desBaiduLatLng.longitude
                        )
                    )

                    mMap.setMapStatus(msu)
                    locData = MyLocationData.Builder()
                        .direction(
                            if (F.mModelStatus?.mModelB?.data_yaw_angle?.toFloat()!! > 0) F.mModelStatus?.mModelB?.data_yaw_angle?.toFloat()!!
                            else F.mModelStatus?.mModelB?.data_yaw_angle?.toFloat()!! + 360
                        )
                        .latitude(desBaiduLatLng.latitude)
                        .longitude(desBaiduLatLng.longitude).build()
                    mMap.setMyLocationData(locData)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onResume() {
        baidumap_overview?.onResume()
        super.onResume()
    }

    override fun onPause() {
        baidumap_overview?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMap?.isMyLocationEnabled = false
        mMap?.clear()
        baidumap_overview?.onDestroy()
        super.onDestroy()
    }
}