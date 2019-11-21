package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.frg_wd_overview.*

class FrgWDOverView : BaseFrg() {
    private val mMap by lazy { baidumap_overview.map }

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_wd_overview)
    }

    override fun initView() {
        val builder = MapStatus.Builder()
        builder.zoom(16.0f).target(LatLng(31.8233, 120.021009)).overlook(-10F)
        mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))

        mMap.mapType = BaiduMap.MAP_TYPE_NORMAL //地图普通
        mMap.isTrafficEnabled = true  //交通
        mMap.isMyLocationEnabled = true  //是否显示定位点
        mMap.setMyLocationConfiguration( //定位点方向
            MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, //定位模式
                true,
                null
            )
        )

        val locData = MyLocationData.Builder()
            .accuracy(0F)
            .direction(0F)
            .latitude(31.8233).longitude(120.015008).build()
        mMap.setMyLocationData(locData)

    }

    override fun loaddata() {
    }
}