package com.deepblue.aidevicemanager.frg

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import com.baidu.mapapi.model.LatLng
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.setViewValue
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.*
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORKING
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_DEFAUT
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_SHUTSTOP
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_STOP
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_WAITSTART
import com.deepblue.aidevicemanager.util.FrgWDManager
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_DIANYUN
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_OVERVIEW
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_ROUTE
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_VEDIO
import com.deepblue.aidevicemanager.ws.WsStatus
import com.mdx.framework.Frame
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_workdetail.*
import java.util.*


class FrgWorkDetail : BaseFrg() {
    companion object {
        var mModelDeviceDetail: ModelDeviceDetail? = null
        var polylines = ArrayList<LatLng>()
        var edgePolylines1 = ArrayList<LatLng>()
        var edgePolylines2 = ArrayList<LatLng>()
        var mWorkState = WORK_DEFAUT
    }

    private lateinit var fragments: HashMap<Int, Fragment>
    private var mTempWorkState = WORK_DEFAUT
    private val mFragmentManager: FrgWDManager by lazy { FrgWDManager(childFragmentManager, ll_lefttop.id, ll_leftcenter.id, ll_leftbottom.id, ll_right.id) }

    private var mFrom: String? = ""
    private var mapId: String? = ""
    private var mapName: String? = ""
    lateinit var data: ModelDevices.RowsBean
    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_workdetail)
    }

    override fun initView() {
        try {
            data = activity?.intent?.getSerializableExtra("data") as ModelDevices.RowsBean
            mModelDeviceDetail = activity.intent.getSerializableExtra("mModelDeviceDetail") as ModelDeviceDetail?
            mFrom = activity.intent.getStringExtra("from")
            mapId = activity.intent.getStringExtra("mapId").toDouble().toInt().toString()
            mapName = activity.intent.getStringExtra("mapTaskName")
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        if (mModelDeviceDetail == null || mModelDeviceDetail?.id == 0 || TextUtils.isEmpty(mFrom) || TextUtils.isEmpty(mapId)) {
            Helper.toast(getString(R.string.dataerror_please_retry))
            finish()
        }
        initListener()
    }

    override fun loaddata() {
        polylines.clear()
        edgePolylines1.clear()
        edgePolylines2.clear()
        F.hasRunPosints.clear()
        load(F.gB().getDevicePresetPositions(mModelDeviceDetail?.id.toString(), mapId), "getDevicePresetPositions", true)
        when (mFrom) {//0列表  1地图选择
            "0" -> {
                setWorkState(WORKING)
            }
            "1" -> {
                setWorkState(WORK_WAITSTART)
            }
            else -> {
            }
        }
        reInitBtnView()
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {// 0: 左上切换  1: 左中切换  2: 左下切换
            R.id.iv_lefttop_switch -> mFragmentManager.changeFragment("frgwdlaser", ll_lefttop.id, ll_right.id, 0)
            R.id.iv_leftcenter_switch -> mFragmentManager.changeFragment("frgwdvedio", ll_leftcenter.id, ll_right.id, 1)
            R.id.iv_leftbottom_switch -> mFragmentManager.changeFragment("frgwdoverview", ll_leftbottom.id, ll_right.id, 2)
            R.id.btn_startwork -> doWorkState(0)
            R.id.btn_stopwork -> doWorkState(1)
            R.id.btn_endwork -> doWorkState(2)
            R.id.btn_continuework -> doWorkState(3)
            R.id.btn_EMG -> doWorkState(if (btn_EMG.isSelected) 5 else 4)
        }
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            1111 -> {
                try {
                    F.sendDb2a(F.mModelStatus?.mModelB, F.data2Model(obj.toString(), ModelA::class.java)?.cleanKingLiveStatus)
                    mHead.set(F.mModelStatus?.mModelB!!)
                    if (isHeadInit()) mHead.setStatus(this.javaClass.simpleName)
                    setViewValue(F.mModelStatus?.mModelB?.data_high_beam_light, iv_high_light)
                    setViewValue(F.mModelStatus?.mModelB?.data_width_light, iv_width_light)
                    setViewValue(F.mModelStatus?.mModelB?.data_left_light, iv_left_light)
                    setViewValue(F.mModelStatus?.mModelB?.data_right_light, iv_right_light)

                    sweep_location.setChecked((F.mModelStatus?.mModelB?.data_brush_status ?: "") == "1")
                    sweep_location.setOpenText(if ((F.mModelStatus?.mModelB?.data_brush_position ?: "") == "0") "上位" else "下位")
                    sweep_location.setCloseText(if ((F.mModelStatus?.mModelB?.data_brush_position ?: "") == "0") "上位" else "下位")
                    sweep_state.setChecked((F.mModelStatus?.mModelB?.data_brush_status ?: "") == "1")
                    water_state.setChecked((F.mModelStatus?.mModelB?.data_spout_water ?: "") == "1")
                    wind_state.setChecked((F.mModelStatus?.mModelB?.data_suction_status ?: "") == "1")
                    wind_location.setChecked((F.mModelStatus?.mModelB?.data_suction_status ?: "") == "1")
                    wind_location.setOpenText(if ((F.mModelStatus?.mModelB?.data_suction_inlet_position ?: "") == "0") "上位" else "下位")
                    wind_location.setCloseText(if ((F.mModelStatus?.mModelB?.data_suction_inlet_position ?: "") == "0") "上位" else "下位")

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            1112 -> {
                when (obj as Int) {
                    WsStatus.CONNECTED -> Helper.toast("WEBSOCKET CONNECTED")
                    WsStatus.CONNECTING -> Helper.toast("WEBSOCKET CONNECTING")
                    WsStatus.RECONNECT -> Helper.toast("WEBSOCKET RECONNECT")
                    WsStatus.DISCONNECTED -> Helper.toast("WEBSOCKET DISCONNECTED")
                }
            }
        }
    }

    override fun onSuccess(data: String?, method: String) {
        when (method) {
            "getDevicePresetPositions" -> {
                try {
                    val mModelMapRoute = F.data2Model(data, ModelRoutePreset::class.java)
                    val routePresetYZ = F.data2Model(mModelMapRoute?.presetYZ, Array<ModelRoutePreset_Inter>::class.java)
                    val routePresetLY1 = F.data2Model(mModelMapRoute?.presetLY1, Array<ModelRoutePreset_Inter>::class.java)
                    val routePresetLY2 = F.data2Model(mModelMapRoute?.presetLY2, Array<ModelRoutePreset_Inter>::class.java)
                    polylines.clear()
                    edgePolylines1.clear()
                    edgePolylines2.clear()
                    routePresetYZ?.forEach {
                        polylines.add(F.getDesBaiduLatLng(it.point_y, it.point_x))
                    }
                    routePresetLY1?.forEach {
                        edgePolylines1.add(F.getDesBaiduLatLng(it.point_y, it.point_x))
                    }
                    routePresetLY2?.forEach {
                        edgePolylines2.add(F.getDesBaiduLatLng(it.point_y, it.point_x))
                    }
                    if (polylines.size < 1 && edgePolylines1.size < 1 && edgePolylines2.size < 1) {
                        Helper.toast(getString(R.string.dataerror_please_retry))
                        finish()
                    } else
                        initFragment()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
            "createOrder_start" -> {
                setWorkState(WORKING)
                reInitBtnView()
            }
            "createOrder_stop" -> {
                setWorkState(WORK_STOP)
                reInitBtnView()
            }
            "createOrder_end" -> {
                setWorkState(WORK_DEFAUT)
//                reInitBtnView()
                finish()
            }
            "createOrder_continue" -> {
                setWorkState(WORKING)
                reInitBtnView()
            }
            "createOrder_emg" -> {
                setWorkState(WORK_SHUTSTOP)
                reInitBtnView()
                reInitEMG(true)
            }
            "createOrder_emg_release" -> {
                setWorkState(mTempWorkState)
                reInitBtnView()
                reInitEMG(false)
            }
        }
    }

    override fun onError(code: String?, msg: String?, data: String?, method: String) {
        super.onError(code, msg, data, method)
        when (method) {
            "getDevicePresetPositions" -> {
                Helper.toast(getString(R.string.dataerror_please_retry))
                finish()
            }
            else -> Helper.toast(msg)
        }
    }

    /**
     * 工作状态修改时 发送消息到FrgWDRoute
     */
    private fun setWorkState(i: Int) {
        mWorkState = i
        Frame.HANDLES.sentAll(9999, i)
    }

    private fun initListener() {
        iv_leftbottom_switch.setOnClickListener(this)
        iv_lefttop_switch.setOnClickListener(this)
//        iv_leftcenter_switch.setOnClickListener(this)
        btn_startwork.setOnClickListener(this)
        btn_stopwork.setOnClickListener(this)
        btn_continuework.setOnClickListener(this)
        btn_endwork.setOnClickListener(this)
        btn_EMG.setOnClickListener(this)
    }

    /**
     * indexWork:
     *              0—>自动作业   1—>暂停作业   2—>结束作业
     *              3—>继续作业   4—>紧急停止   5—>松开急停
     */
    private fun doWorkState(indexWork: Int) {
        when (indexWork) {
            0 -> {
                load(F.gB(60).createOrder("14", mModelDeviceDetail?.id.toString()), "createOrder_start")
            }
            1 -> {
                load(F.gB(60).createOrder("7", mModelDeviceDetail?.id.toString()), "createOrder_stop")
            }
            2 -> {
                AlertDialog.Builder(context).setTitle(R.string.NOTICE)
                    .setMessage(R.string.EMG_NOTICE)
                    .setPositiveButton(R.string.ems_showbtn1) { _: DialogInterface, _: Int ->
                        run {
                            load(F.gB(60).createOrder("5", mModelDeviceDetail?.id.toString()), "createOrder_end")
                        }
                    }
                    .setNegativeButton(R.string.ems_showbtn2, null)
                    .show()
            }
            3 -> {
                load(F.gB(60).createOrder("8", mModelDeviceDetail?.id.toString()), "createOrder_continue")
            }
            4 -> {
                mTempWorkState = mWorkState
                load(F.gB(60).createOrder("13", mModelDeviceDetail?.id.toString()), "createOrder_emg")
            }
            5 -> {
                load(F.gB(60).createOrder("15", mModelDeviceDetail?.id.toString()), "createOrder_emg_release")
            }
        }
    }

    private fun reInitEMG(bool_: Boolean) {
        btn_EMG.isSelected = bool_
        btn_EMG.text =
            if (bool_) resources.getString(R.string.release_EMG) else resources.getString(R.string.EMG)
        tv_emg_show.visibility = if (bool_) View.VISIBLE else View.GONE
    }

    private fun reInitBtnView() {
        when (mWorkState) {
            WORK_WAITSTART -> {
                btn_startwork.visibility = View.VISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.INVISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
            WORKING -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.VISIBLE
                btn_endwork.visibility = View.VISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
            WORK_STOP -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.VISIBLE
                btn_continuework.visibility = View.VISIBLE
            }
            WORK_SHUTSTOP -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.INVISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
        }
    }

    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        try {
            mHead.setShowPop(data, "FrgWorkDetail")
            mHead.setTitle(data.deviceName)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun initFragment() {
        fragments = hashMapOf(
            PAGE_DIANYUN to FrgWDLaser(),
            PAGE_VEDIO to FrgWDVedio(),
            PAGE_OVERVIEW to FrgWDOverView(),
            PAGE_ROUTE to FrgWDRoute()
        )
        childFragmentManager.beginTransaction()
            .add(R.id.ll_lefttop, fragments[PAGE_DIANYUN], PAGE_DIANYUN.toString())
            .add(R.id.ll_leftcenter, fragments[PAGE_VEDIO], PAGE_VEDIO.toString())
            .add(R.id.ll_leftbottom, fragments[PAGE_OVERVIEW], PAGE_OVERVIEW.toString())
            .add(R.id.ll_right, fragments[PAGE_ROUTE], PAGE_ROUTE.toString())
            .commit()
    }

    override fun onDestroy() {
        F.hasRunPosints.clear()
        if (mFrom.equals("0"))
            F.stopConnectWSocket()
        super.onDestroy()
    }

}