package com.deepblue.aidevicemanager.frg

import android.graphics.Color
import android.os.Bundle
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.model.LatLngBounds
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.frg_wd_route.*
import java.util.*
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapStatusUpdate


class FrgWDRoute : BaseFrg() {
    private val mMap by lazy { baidumap_route.map }

    private var mPolyline: Polyline? = null
    private var mMoveMarker: Marker? = null
    private val mBitmapCar = BitmapDescriptorFactory.fromResource(R.drawable.u2274)
    private val DISTANCE = 0.00002  //默认间隔移动距离
    private val mPolylineWith = 18  //路线宽度
    private val mHasRunPolylineWith = 17    //已行驶路线宽度
    private val mPolylineColor = Color.parseColor("#FFF72D05")    //路线颜色
    private val mHasRunPolylineColor = Color.parseColor("#FF0082FA")  //已行驶路线颜色

    //模拟数据
    private val latlngs = arrayOf(
        LatLng(31.8233, 120.021009),
        LatLng(31.823331, 120.021799),
        LatLng(31.823362, 120.025392),
        LatLng(31.822533, 120.02992),
        LatLng(31.822042, 120.030998),
        LatLng(31.820478, 120.03071),
        LatLng(31.817777, 120.030387),
        LatLng(31.812899, 120.02956),
        LatLng(31.81296, 120.022051),
        LatLng(31.813022, 120.014685),
        LatLng(31.817624, 120.014397),
        LatLng(31.82327, 120.013678),
        LatLng(31.8233, 120.015008),
        LatLng(31.8233, 120.021009)
    )

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_wd_route)
    }

    override fun initView() {
        val builder = MapStatus.Builder()
//        builder.zoom(19.0f).target(LatLng(31.818067, 120.022194))
        mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))

        mMap.mapType = BaiduMap.MAP_TYPE_SATELLITE //地图卫星
        mMap.isTrafficEnabled = true  //交通
        baidumap_route.showZoomControls(false)
        baidumap_route.logoPosition = LogoPosition.logoPostionRightBottom //logo位置

        drawPolyLine()
        moveLooper()
    }

    override fun loaddata() {
    }

    private fun drawPolyLine() {
        val polylines = ArrayList<LatLng>()
        for (index in latlngs.indices) {
            polylines.add(latlngs[index])
        }
        //*************************************************************************************
        var builder1 = LatLngBounds.Builder()
        for (p in polylines) {
            builder1 = builder1.include(p)
        }
        val mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(builder1.build())
        mMap.setMapStatus(mapStatusUpdate)
        val msu = MapStatusUpdateFactory.zoomBy(3f)
        mMap.setMapStatus(msu)
//        mMap.getZoomToBound()
        //*************************************************************************************
        polylines.add(latlngs[0])
        val mOverlayOptions = PolylineOptions()
            .width(mPolylineWith)
            .color(mPolylineColor)
            .points(polylines)
        mPolyline = mMap.addOverlay(mOverlayOptions) as Polyline

        // 添加小车marker
        mMoveMarker = mMap.addOverlay(
            MarkerOptions().flat(true).anchor(0.5f, 0.5f)
                .icon(mBitmapCar).zIndex(10).position(
                    polylines[0]
                ).rotate(getAngle(0).toFloat())
        ) as Marker
    }

    fun moveLooper() {
        object : Thread() {
            override fun run() {
                while (true) {
                    for (i in 0 until latlngs.size - 1) {
                        val startPoint = latlngs[i]
                        val endPoint = latlngs[i + 1]
                        mMoveMarker?.position = startPoint
                        activity?.runOnUiThread {
                            //更新小车方向
                            mMoveMarker?.rotate = getAngle(startPoint, endPoint).toFloat()
                        }
                        val slope = getSlope(startPoint, endPoint)
                        // 是不是正向的标示
                        val isYReverse = startPoint.latitude > endPoint.latitude
                        val isXReverse = startPoint.longitude > endPoint.longitude
                        val intercept = getInterception(slope, startPoint)
                        val xMoveDistance =
                            if (isXReverse) getXMoveDistance(slope) else -1 * getXMoveDistance(slope)
                        val yMoveDistance =
                            if (isYReverse) getYMoveDistance(slope) else -1 * getYMoveDistance(slope)

                        var j = startPoint.latitude
                        var k = startPoint.longitude
                        while (j > endPoint.latitude == isYReverse && k > endPoint.longitude == isXReverse) {
                            var latLng: LatLng?

                            when (slope) {
                                java.lang.Double.MAX_VALUE -> {
                                    latLng = LatLng(j, k)
                                    j -= yMoveDistance
                                }
                                0.0 -> {
                                    latLng = LatLng(j, k - xMoveDistance)
                                    k -= xMoveDistance
                                }
                                else -> {
                                    latLng = LatLng(j, (j - intercept) / slope)
                                    j -= yMoveDistance
                                }
                            }

                            val finalLatLng = latLng
                            if (finalLatLng.latitude == 0.0 && finalLatLng.longitude == 0.0) {
                                continue
                            }
                            activity?.runOnUiThread {
                                //设置小车位置，这里为了实现小车平滑移动
                                mMoveMarker?.position = finalLatLng
                                //设置小车已行驶路径
                                mMap.addOverlay(
                                    PolylineOptions()
                                        .width(mHasRunPolylineWith)
                                        .color(mHasRunPolylineColor).points(
                                            arrayListOf(
                                                startPoint,
                                                finalLatLng
                                            )
                                        )
                                )
                            }
                            try {
                                sleep(80.toLong())
                            } catch (e: InterruptedException) {
                                e.printStackTrace()
                            }

                        }
                    }
                }
            }

        }.start()
    }


    /**
     * 根据点获取图标转的角度
     */
    private fun getAngle(startIndex: Int): Double {
        if (startIndex + 1 >= mPolyline!!.points.size) {
            throw RuntimeException("index out of bonds")
        }
        val startPoint = mPolyline!!.points[startIndex]
        val endPoint = mPolyline!!.points[startIndex + 1]
        return getAngle(startPoint, endPoint)
    }

    /**
     * 根据两点算取图标转的角度
     */
    private fun getAngle(fromPoint: LatLng, toPoint: LatLng): Double {
        val slope = getSlope(fromPoint, toPoint)
        if (slope == java.lang.Double.MAX_VALUE) {
            return if (toPoint.latitude > fromPoint.latitude) {
                0.0
            } else {
                180.0
            }
        } else if (slope == 0.0) {
            return if (toPoint.longitude > fromPoint.longitude) {
                -90.0
            } else {
                90.0
            }
        }
        var deltAngle = 0f
        if ((toPoint.latitude - fromPoint.latitude) * slope < 0) {
            deltAngle = 180f
        }
        val radio = Math.atan(slope)
        return 180 * (radio / Math.PI) + deltAngle - 90
    }

    /**
     * 根据点和斜率算取截距
     */
    private fun getInterception(slope: Double, point: LatLng): Double {
        return point.latitude - slope * point.longitude
    }

    /**
     * 算斜率
     */
    private fun getSlope(fromPoint: LatLng, toPoint: LatLng): Double {
        return if (toPoint.longitude == fromPoint.longitude) {
            java.lang.Double.MAX_VALUE
        } else (toPoint.latitude - fromPoint.latitude) / (toPoint.longitude - fromPoint.longitude)
    }

    /**
     * 计算x方向每次移动的距离
     */
    private fun getXMoveDistance(slope: Double): Double {
        return if (slope == java.lang.Double.MAX_VALUE || slope == 0.0) {
            DISTANCE
        } else Math.abs(DISTANCE * 1 / slope / Math.sqrt(1 + 1 / (slope * slope)))
    }

    /**
     * 计算y方向每次移动的距离
     */
    private fun getYMoveDistance(slope: Double): Double {
        return if (slope == java.lang.Double.MAX_VALUE || slope == 0.0) {
            DISTANCE
        } else Math.abs(DISTANCE * slope / Math.sqrt(1 + slope * slope))
    }


}