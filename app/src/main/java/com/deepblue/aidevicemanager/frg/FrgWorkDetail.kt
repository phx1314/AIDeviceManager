package com.deepblue.aidevicemanager.frg

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.view.View
import com.baidu.mapapi.model.LatLng
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelA
import com.deepblue.aidevicemanager.model.ModelRoutePreset
import com.deepblue.aidevicemanager.model.ModelRoutePreset_Inter
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORKING
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_DEFAUT
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_SHUTSTOP
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_STOP
import com.deepblue.aidevicemanager.util.CarWorkStateStatus.Companion.WORK_WAITSTART
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_DIANYUN
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_OVERVIEW
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_ROUTE
import com.deepblue.aidevicemanager.util.WorkDetailFrgIndex.Companion.PAGE_VEDIO
import com.deepblue.aidevicemanager.ws.WsStatus
import com.google.gson.Gson
import com.mdx.framework.Frame
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_workdetail.*
import java.util.*

class FrgWorkDetail : BaseFrg() {
    private lateinit var fragments: HashMap<Int, Fragment>
    private var mWorkState = WORK_DEFAUT
    private var mTempWorkState = WORK_DEFAUT

    private var mId: String? = ""
    private var mFrom: String? = ""
    private var mapId: String? = ""
    private var mapName: String? = ""

    val bundle = Bundle()
    val polylines = ArrayList<LatLng>()
    val edgePolylines1 = ArrayList<LatLng>()
    val edgePolylines2 = ArrayList<LatLng>()

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_workdetail)
    }

    override fun initView() {
        mId = activity.intent.getStringExtra("id")
        mFrom = activity.intent.getStringExtra("from")
        mapId = activity.intent.getStringExtra("mapId")
        mapName = activity.intent.getStringExtra("mapTaskName")
        if (TextUtils.isEmpty(mId) || TextUtils.isEmpty(mFrom) || TextUtils.isEmpty(mapId)) {
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
        load(F.gB().getDevicePresetPositions(mId, mapId), "getDevicePresetPositions", true)
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
        when (v.id) {
            R.id.iv_lefttop_switch -> switchFragment(0)
            R.id.iv_leftcenter_switch -> switchFragment(1)
            R.id.iv_leftbottom_switch -> switchFragment(2)
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
//                Helper.toast("实时数据：${obj.toString()}")
                try {
                    F.mModelStatus?.mModelB = Gson().fromJson(obj.toString(), ModelA::class.java).cleanKingLiveStatus
                    if (isHeadInit()) mHead.setStatus(this.javaClass.simpleName)
                    iv_high_light.isSelected = F.mModelStatus?.mModelB?.data_high_beam_light.equals("1")
                    iv_width_light.isSelected = F.mModelStatus?.mModelB?.data_width_light.equals("1")
                    iv_left_light.isSelected = F.mModelStatus?.mModelB?.data_left_light.equals("1")
                    iv_right_light.isSelected = F.mModelStatus?.mModelB?.data_right_light.equals("1")
                    sweep_location.setChecked(F.mModelStatus?.mModelB?.data_brush_status.equals("1"))
                    sweep_location.setOpenText(if (F.mModelStatus?.mModelB?.data_brush_position.equals("0")) "上位" else "下位")
                    sweep_location.setCloseText(if (F.mModelStatus?.mModelB?.data_brush_position.equals("0")) "上位" else "下位")
                    sweep_state.setChecked(F.mModelStatus?.mModelB?.data_brush_status.equals("1"))
                    water_state.setChecked(F.mModelStatus?.mModelB?.data_spout_water.equals("1"))
                    wind_state.setChecked(F.mModelStatus?.mModelB?.data_suction_status.equals("1"))
                    wind_location.setChecked(F.mModelStatus?.mModelB?.data_suction_status.equals("1"))
                    wind_location.setOpenText(if (F.mModelStatus?.mModelB?.data_suction_inlet_position.equals("0")) "上位" else "下位")
                    wind_location.setCloseText(if (F.mModelStatus?.mModelB?.data_suction_inlet_position.equals("0")) "上位" else "下位")

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
                        polylines.add(LatLng(it.point_x, it.point_y))
                    }
                    routePresetLY1?.forEach {
                        edgePolylines1.add(LatLng(it.point_x, it.point_y))
                    }
                    routePresetLY2?.forEach {
                        edgePolylines2.add(LatLng(it.point_x, it.point_y))
                    }
                    initFragment()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
            "autoWork" -> {
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
            "createOrder_continue_emg" -> {
                setWorkState(WORKING)
                reInitBtnView()
                reInitEMG(false)
            }
            "createOrder_stop_emg" -> {
                setWorkState(WORK_STOP)
                reInitBtnView()
                reInitEMG(false)
            }
        }
    }

    override fun onError(code: String?, msg: String?, data: String?, method: String) {
        super.onError(code, msg, data, method)
        when (method) {
            "" -> {
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

    /**
     * 切换fragment
     */
    private fun switchFragment(typeIndex: Int) { // 0: 左上切换  1: 左中切换  2: 左下切换
        when (typeIndex) {
            0 -> doSwitchFrg(ll_lefttop.id, ll_right.id)
            1 -> doSwitchFrg(ll_leftcenter.id, ll_right.id)
            2 -> doSwitchFrg(ll_leftbottom.id, ll_right.id)
        }
    }

    private fun doSwitchFrg(a1: Int, a2: Int) {
        val frg1Old = childFragmentManager.findFragmentById(a1)
        val frg2Old = childFragmentManager.findFragmentById(a2)
        val frgNew1 = getContainerFragment(frg2Old)
        val frgNew2 = getContainerFragment(frg1Old)

        if (frgNew1 != null && frgNew2 != null && frg1Old != null && frg2Old != null) {
            frgNew1.arguments = bundle
            frgNew2.arguments = bundle

            childFragmentManager.popBackStackImmediate(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            childFragmentManager.beginTransaction()
                .remove(frg1Old)
                .remove(frg2Old)
                .commit()
            childFragmentManager.executePendingTransactions()
            childFragmentManager.beginTransaction()
                .replace(a1, frgNew1)
                .replace(a2, frgNew2)
                .commit()
        }
    }

    @SuppressLint("DefaultLocale")
    private fun getContainerFragment(frg: Fragment?): Fragment? {
        if (frg.toString().toLowerCase().contains("frgwdlaser"))
            return FrgWDLaser()
        if (frg.toString().toLowerCase().contains("frgwdvedio"))
            return FrgWDVedio()
        if (frg.toString().toLowerCase().contains("frgwdoverview"))
            return FrgWDOverView()
        if (frg.toString().toLowerCase().contains("frgwdroute"))
            return FrgWDRoute()
        return null
    }

    private fun initListener() {
        iv_leftbottom_switch.setOnClickListener(this)
        iv_lefttop_switch.setOnClickListener(this)
        iv_leftcenter_switch.setOnClickListener(this)
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
                load(F.gB(60).autoWork(mId, mapName), "autoWork")
            }
            1 -> {
                load(F.gB(60).createOrder("7", mId!!), "createOrder_stop")
            }
            2 -> {
                AlertDialog.Builder(context).setTitle(R.string.NOTICE)
                    .setMessage(R.string.EMG_NOTICE)
                    .setPositiveButton(R.string.ems_showbtn1) { _: DialogInterface, _: Int ->
                        run {
                            load(F.gB(60).createOrder("5", mId!!), "createOrder_end")
                        }
                    }
                    .setNegativeButton(R.string.ems_showbtn2, null)
                    .show()
            }
            3 -> {
                load(F.gB(60).createOrder("8", mId!!), "createOrder_continue")
            }
            4 -> {
                mTempWorkState = mWorkState
                load(F.gB(60).createOrder("13", mId!!), "createOrder_emg")
            }
            5 -> {
                F
                when (mTempWorkState) {
                    WORKING -> {
                        load(F.gB(60).createOrder("8", mId!!), "createOrder_continue_emg")
                    }
                    WORK_STOP -> {
                        load(F.gB(60).createOrder("7", mId!!), "createOrder_stop_emg")
                    }
                    WORK_WAITSTART -> {
                        setWorkState(WORK_WAITSTART)
                        reInitBtnView()
                        reInitEMG(false)
                    }
                }
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

    private fun initFragment() {
        if (polylines.size > 0) {
            F.hasRunPosints.add(polylines[0])
            bundle.putParcelableArrayList("polylines", polylines)
        }
        if (edgePolylines1.size > 0) {
            bundle.putParcelableArrayList("edgePolylines1", edgePolylines1)
        }
        if (edgePolylines2.size > 0) {
            bundle.putParcelableArrayList("edgePolylines2", edgePolylines2)
        }
        fragments = hashMapOf(
            PAGE_DIANYUN to FrgWDLaser(),
            PAGE_VEDIO to FrgWDVedio(),
            PAGE_OVERVIEW to FrgWDOverView(),
            PAGE_ROUTE to FrgWDRoute()
        )
        fragments[PAGE_ROUTE]?.arguments = bundle
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