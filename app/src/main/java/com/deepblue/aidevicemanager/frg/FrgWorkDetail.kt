package com.deepblue.aidevicemanager.frg

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Toast
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.model.ModelB
import com.deepblue.aidevicemanager.util.CarWorkStateStatus
import com.deepblue.aidevicemanager.view.TextSwitch
import com.deepblue.aidevicemanager.ws.WsStatus
import com.google.gson.Gson
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_workdetail.*
import java.util.*

class FrgWorkDetail : BaseFrg(), TextSwitch.OnCheckedChangeListener {
    private lateinit var fragments: HashMap<Int, Fragment>
    private var WORKSTATE = CarWorkStateStatus.WORK_DEFAUT
    private var TempWorkstate = CarWorkStateStatus.WORK_DEFAUT

    private val PAGE_DIANYUN = 0
    private val PAGE_VEDIO = 1
    private val PAGE_OVERVIEW = 2
    private val PAGE_ROUTE = 3
    private var id_: String? = ""
    private var from_: String? = ""
    var url = "ws://192.168.123.209:8081/websocket/wg"

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_workdetail)
    }

    override fun initView() {
        id_ = activity.intent.getStringExtra("id")
        from_ = activity.intent.getStringExtra("from")
        initListener()
        fragments = hashMapOf(
            PAGE_DIANYUN to FrgWDLaser(),
            PAGE_VEDIO to FrgWDVedio(),
            PAGE_OVERVIEW to FrgWDOverView(),
            PAGE_ROUTE to FrgWDRoute()
        )
        childFragmentManager.beginTransaction()
            .add(R.id.ll_lefttop, fragments[PAGE_DIANYUN]!!, PAGE_DIANYUN.toString())
            .add(R.id.ll_leftcenter, fragments[PAGE_VEDIO]!!, PAGE_VEDIO.toString())
            .add(R.id.ll_leftbottom, fragments[PAGE_OVERVIEW]!!, PAGE_OVERVIEW.toString())
            .add(R.id.ll_right, fragments[PAGE_ROUTE]!!, PAGE_ROUTE.toString())
            .commit()
        //初始化工作状态
        WORKSTATE = 0
        reInitBtnView()

        F.connectWSocket(context,url)
    }

    override fun loaddata() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_lefttop_switch -> switchFragment(0)
            R.id.iv_leftcenter_switch -> switchFragment(1)
            R.id.iv_leftbottom_switch -> switchFragment(2)
            R.id.btn_startwork -> {
                doWorkState(0)
            }
            R.id.btn_stopwork -> {
                doWorkState(1)
            }
            R.id.btn_endwork -> {
                doWorkState(2)
            }
            R.id.btn_continuework -> {
                doWorkState(3)
            }
            R.id.btn_EMG -> {
                doWorkState(if (btn_EMG.isSelected) 5 else 4)
            }
        }
    }

    override fun onCheckedChanged(switch_id: String?, isChecked: Boolean) {
        when (switch_id) {
            "1" -> Toast.makeText(context, "345$switch_id$isChecked", Toast.LENGTH_SHORT).show()
            "2" -> Toast.makeText(context, "345$switch_id$isChecked", Toast.LENGTH_SHORT).show()
            "3" -> Toast.makeText(context, "345$switch_id$isChecked", Toast.LENGTH_SHORT).show()
            "4" -> Toast.makeText(context, "345$switch_id$isChecked", Toast.LENGTH_SHORT).show()
            "5" -> Toast.makeText(context, "345$switch_id$isChecked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            1111 -> {
                Helper.toast("实时数据：${obj.toString()}")
                F.mModelStatus?.mModelB = Gson().fromJson(obj.toString(), ModelB::class.java)
                if (isHeadInit()) mHead.setStatus()
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
        hideProgressDialog()
        when (method) {
            "createOrder_start" -> {
                WORKSTATE = CarWorkStateStatus.WORKING
                reInitBtnView()
            }
            "createOrder_stop" -> {
                WORKSTATE = CarWorkStateStatus.WORK_STOP
                reInitBtnView()
            }
            "createOrder_end" -> {
                WORKSTATE = CarWorkStateStatus.WORK_WAITSTART
                reInitBtnView()
            }
            "createOrder_continue" -> {
                WORKSTATE = CarWorkStateStatus.WORKING
                reInitBtnView()
            }
            "createOrder_emg" -> {
                WORKSTATE = CarWorkStateStatus.WORK_SHUTSTOP
                reInitBtnView()
                reInitEMG(true)
            }
            "createOrder_continue_emg" -> {
                WORKSTATE = CarWorkStateStatus.WORKING
                reInitBtnView()
                reInitEMG(false)
            }
            "createOrder_stop_emg" -> {
                WORKSTATE = CarWorkStateStatus.WORK_STOP
                reInitBtnView()
                reInitEMG(false)
            }
        }
    }

    override fun onError(code: String?, msg: String?, data: String?, method: String) {
        super.onError(code, msg, data, method)
        when (method) {
            "createOrder_start", "createOrder_stop", "createOrder_end", "createOrder_continue"
            -> Helper.toast(msg)
        }
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
        sweep_location.setOnCheckedChangeListener(this)
        sweep_state.setOnCheckedChangeListener(this)
        water_state.setOnCheckedChangeListener(this)
        wind_location.setOnCheckedChangeListener(this)
        wind_state.setOnCheckedChangeListener(this)
    }

    /**
     * indexWork:
     *              0—>自动作业   1—>暂停作业   2—>结束作业
     *              3—>继续作业   4—>紧急停止   5—>松开急停
     */
    private fun doWorkState(indexWork: Int) {
        showProgressDialog(resources.getString(R.string.NOTICE), "请稍后...")
        when (indexWork) {
            0 -> {
//                load(F.gB(60).createOrder("14", id_), "createOrder_start")
                onSuccess("", "createOrder_start")
            }
            1 -> {
//                load(F.gB(60).createOrder("7", id_), "createOrder_stop")
                onSuccess("", "createOrder_stop")
            }
            2 -> {
                AlertDialog.Builder(context).setTitle(R.string.NOTICE)
                    .setMessage(R.string.EMG_NOTICE)
                    .setPositiveButton(R.string.ems_showbtn1) { _: DialogInterface, _: Int ->
                        run {
                            //   load(F.gB(60).createOrder("5", id_), "createOrder_end")
                            onSuccess("", "createOrder_end")
                        }
                    }
                    .setNegativeButton(R.string.ems_showbtn2) { _: DialogInterface, _: Int ->
                        run {
                            hideProgressDialog()
                        }
                    }
                    .show()
            }
            3 -> {
//                load(F.gB(60).createOrder("8", id_), "createOrder_continue")
                onSuccess("", "createOrder_continue")
            }
            4 -> {
                TempWorkstate = WORKSTATE
                load(F.gB(60).createOrder("13", "12"), "createOrder_emg")
                onSuccess("", "createOrder_emg")
            }
            5 -> {
                when (TempWorkstate) {
                    CarWorkStateStatus.WORKING -> {
//                        load(F.gB(60).createOrder("8", id_), "createOrder_continue_emg")
                        onSuccess("", "createOrder_continue_emg")
                    }
                    CarWorkStateStatus.WORK_STOP -> {
//                        load(F.gB(60).createOrder("7", id_), "createOrder_stop_emg")
                        onSuccess("", "createOrder_stop_emg")
                    }
                    CarWorkStateStatus.WORK_WAITSTART -> {
                        hideProgressDialog()
                        WORKSTATE = 0
                        reInitBtnView()
                        reInitEMG(false)
                    }
                    else -> hideProgressDialog()
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
        when (WORKSTATE) {
            CarWorkStateStatus.WORK_WAITSTART -> {
                btn_startwork.visibility = View.VISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.INVISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
            CarWorkStateStatus.WORKING -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.VISIBLE
                btn_endwork.visibility = View.VISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
            CarWorkStateStatus.WORK_STOP -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.VISIBLE
                btn_continuework.visibility = View.VISIBLE
            }
            CarWorkStateStatus.WORK_SHUTSTOP -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.INVISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        F.stopConnectWSocket()
    }
}