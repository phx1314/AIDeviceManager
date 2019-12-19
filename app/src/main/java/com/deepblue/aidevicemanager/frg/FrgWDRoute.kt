package com.deepblue.aidevicemanager.frg

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.model.LatLngBounds
import com.baidu.mapapi.utils.DistanceUtil
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelA
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORKING
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_DEFAUT
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frg_wd_route.*


class FrgWDRoute : BaseFrg() {
    private val mMap by lazy { baidumap_route.map }

    private var mPolyline: Polyline? = null
    private var mMoveMarker: Marker? = null
    private var mStartMarker: Marker? = null
    private var mEndMarker: Marker? = null
    private val mBitmapCar = BitmapDescriptorFactory.fromResource(R.drawable.u2274)
    private val mBitmapStart = BitmapDescriptorFactory.fromResource(R.drawable.startpoint)
    private val mBitmapEnd = BitmapDescriptorFactory.fromResource(R.drawable.endpoint)
    private val DISTANCE = 0.00002  //默认间隔移动距离
    private val mEdgePolylineWith = 6  //路沿宽度
    private val mPolylineWith = 9  //路线宽度
    private val mHasRunPolylineWith = 9   //已行驶路线宽度
    private val mEdgePolylineColor = Color.GRAY   //路沿颜色
    private val mPolylineColor = Color.BLUE  //路线颜色
    private val mHasRunPolylineColor = Color.RED //已行驶路线颜色
    private var mHasRunPolyline: Overlay? = null
    private val WSDuringTime: Long = 1000
    private val carMoveDuringTime: Long = 100
    private val distanceArr = intArrayOf(20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000)
    private val levelArr = intArrayOf(21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3)

    private val polyLines = java.util.ArrayList<LatLng>()
    private val edgePolyLines1 = java.util.ArrayList<LatLng>()
    private val edgePolyLines2 = java.util.ArrayList<LatLng>()
    private val distanceDataList = ArrayList<Double>()
    private var mWorkState = WORK_DEFAUT

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_wd_route)
    }

    override fun initView() {
        val builder = MapStatus.Builder()
        mMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
//        mMap.mapType = BaiduMap.MAP_TYPE_SATELLITE //地图卫星
//        mMap.isTrafficEnabled = true  //交通
        baidumap_route.showZoomControls(false)
        baidumap_route.logoPosition = LogoPosition.logoPostionRightBottom //logo位置
    }

    override fun loaddata() {
        if (FrgWorkDetail.polylines.size > 0) {
            polyLines.clear()
            polyLines.addAll(FrgWorkDetail.polylines)
        }
        if (FrgWorkDetail.edgePolylines1.size > 0) {
            edgePolyLines1.clear()
            edgePolyLines1.addAll(FrgWorkDetail.edgePolylines1)
        }
        if (FrgWorkDetail.edgePolylines2.size > 0) {
            edgePolyLines2.clear()
            edgePolyLines2.addAll(FrgWorkDetail.edgePolylines2)
        }
        if (polyLines.size > 1 && edgePolyLines1.size > 1 && edgePolyLines2.size > 1) {
            drawPolyLine()
        }
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            1111 -> {
                Log.e(
                    "实时经纬度",
                    "本体未处理过的实时经纬度====(" + F.mModelStatus?.mModelB?.data_longitude?.toDouble()!! + "," + F.mModelStatus?.mModelB?.data_latitude?.toDouble()!! + ")"
                )
                try {
                    if (FrgWorkDetail.mWorkState == WORKING) {
                        val mA = F.getDesBaiduLatLng(F.mModelStatus?.mModelB?.data_latitude?.toDouble()!!, F.mModelStatus?.mModelB?.data_longitude?.toDouble()!!)
                        if (F.hasRunPosints.size == 0)
                            F.hasRunPosints.add(mA)
                        moveLooper(F.hasRunPosints[F.hasRunPosints.size - 1], mA)
//                        moveLooper2(F.hasRunPosints[F.hasRunPosints.size - 1], mA)
                        F.hasRunPosints.add(mA)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun drawPolyLine() {
        //初始化小车位置
        var builder1 = LatLngBounds.Builder()
        distanceDataList.clear()
        for (p in polyLines) {
            builder1 = builder1.include(p)
            distanceDataList.add(DistanceUtil.getDistance(polyLines[0], p))
        }

        val mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(builder1.build())
        mMap.setMapStatus(mapStatusUpdate)
        val msu = MapStatusUpdateFactory.zoomTo(getLevel(distanceDataList.max()!!.toInt()).toFloat())
        mMap.setMapStatus(msu)

        //*************************************************************************************
        //路沿绘制
        val mEdgeOverlayOptions1 = PolylineOptions()
            .width(mEdgePolylineWith)
            .color(mEdgePolylineColor)
            .zIndex(8)
            .points(edgePolyLines1)
        mMap.addOverlay(mEdgeOverlayOptions1) as Polyline
        val mEdgeOverlayOptions2 = PolylineOptions()
            .width(mEdgePolylineWith)
            .color(mEdgePolylineColor)
            .zIndex(8)
            .points(edgePolyLines2)
        mMap.addOverlay(mEdgeOverlayOptions2) as Polyline
        //路径绘制
        val mOverlayOptions = PolylineOptions()
            .width(mPolylineWith)
            .color(mPolylineColor)
            .zIndex(8)
            .points(polyLines)
        mPolyline = mMap.addOverlay(mOverlayOptions) as Polyline
        // 实例化小车起点终点marker
        mEndMarker = mMap.addOverlay(
            MarkerOptions().flat(true)
                .anchor(0.5f, 0.5f)
                .icon(mBitmapEnd)
                .zIndex(10)
                .position(polyLines[polyLines.size - 1])
        ) as Marker
        mStartMarker = mMap.addOverlay(
            MarkerOptions().flat(true)
                .anchor(0.5f, 0.5f)
                .icon(mBitmapStart)
                .zIndex(10)
                .position(polyLines[0])
        ) as Marker
        mMoveMarker = mMap.addOverlay(
            MarkerOptions().flat(true)
                .anchor(0.5f, 0.5f)
                .icon(mBitmapCar)
                .zIndex(10)
                .position(polyLines[0])
                .rotate(getAngle(0).toFloat())
        ) as Marker
//        用movelooper2方法时使用
//        if (F.hasRunPosints.size != 0) {
//            mMap.addOverlay(
//                PolylineOptions()
//                    .width(mHasRunPolylineWith)
//                    .zIndex(8)
//                    .color(mHasRunPolylineColor).points(F.hasRunPosints)
//            )
//        }
    }

    private fun moveLooper(startPoint: LatLng, endPoint: LatLng) {
        object : Thread() {
            override fun run() {
                mMoveMarker?.position = endPoint
                activity?.runOnUiThread {
                    //更新小车方向
//                    mMoveMarker?.rotate = getAngle(startPoint, endPoint).toFloat()
                    try {
                        mMoveMarker?.rotate = F.mModelStatus?.mModelB?.data_yaw_angle?.toFloat()!!
                        //设置小车已行驶路径
                        mHasRunPolyline?.remove()
                        val s = PolylineOptions()
                            .width(mHasRunPolylineWith)
                            .zIndex(8)
                            .color(mHasRunPolylineColor).points(F.hasRunPosints)
                        mHasRunPolyline = mMap.addOverlay(s)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }.start()
    }

    fun moveLooper2(startPoint: LatLng, endPoint: LatLng) {
        object : Thread() {
            override fun run() {
                while (true) {
                    mMoveMarker?.position = startPoint
                    activity?.runOnUiThread {
                        //更新小车方向
                        mMoveMarker?.rotate = getAngle(startPoint, endPoint).toFloat()
                    }
                    val times = (WSDuringTime / carMoveDuringTime).toInt()

                    // 是不是正向的标示
                    val isXReverse = startPoint.latitude < endPoint.latitude
                    val isYReverse = startPoint.longitude < endPoint.longitude
                    val xMoveUnit = (endPoint.latitude - startPoint.latitude) / times
                    val yMoveUnit = (endPoint.longitude - startPoint.longitude) / times
                    for (i in 0 until times - 1) {
                        var latLng: LatLng =
                            LatLng(
                                if (isXReverse) startPoint.latitude + xMoveUnit else startPoint.latitude - xMoveUnit,
                                if (isYReverse) startPoint.longitude + yMoveUnit else startPoint.longitude - yMoveUnit
                            )
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
                                    .zIndex(8)
                                    .color(mHasRunPolylineColor).points(
                                        arrayListOf(
                                            startPoint,
                                            finalLatLng
                                        )
                                    )
                            )
                        }
                        try {
                            sleep(carMoveDuringTime)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
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

    /**
     * 计算自适应缩放比例
     */
    private fun getLevel(distance: Int): Int {
        var level = -1
        var min = 10000000
        for (i in distanceArr.indices) {
            if (distanceArr[i] - distance in 1 until min) {
                min = distanceArr[i] - distance
                level = i
            }
        }
        return levelArr[if (level == 0) 0 else level - 1]
    }

    override fun onResume() {
        super.onResume()
        baidumap_route?.onResume()
    }

    override fun onPause() {
        super.onPause()
        baidumap_route?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBitmapCar?.recycle()
        mBitmapStart?.recycle()
        mBitmapEnd?.recycle()
        mMap?.clear()
        baidumap_route?.onDestroy()
    }
}