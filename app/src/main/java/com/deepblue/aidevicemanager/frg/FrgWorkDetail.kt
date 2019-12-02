package com.deepblue.aidevicemanager.frg

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Toast
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.wsManager
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.view.TextSwitch
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_workdetail.*
import java.util.*

class FrgWorkDetail : BaseFrg(), TextSwitch.OnCheckedChangeListener {
    private lateinit var fragments: HashMap<Int, Fragment>
    private var WORKSTATE = 0  //工作状态 0：初始，1：运行，2：暂停
    private val PAGE_DIANYUN = 0
    private val PAGE_VEDIO = 1
    private val PAGE_OVERVIEW = 2
    private val PAGE_ROUTE = 3
    var url = "ws://192.168.123.209:8081/websocket/wg"

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_workdetail)
    }

    override fun initView() {
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
        reInitBtnView()
    }

    override fun loaddata() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_EMG -> {
                Toast.makeText(context, "123", Toast.LENGTH_SHORT).show()
            }
            R.id.iv_lefttop_switch -> switchFragment(0)
            R.id.iv_leftcenter_switch -> switchFragment(1)
            R.id.iv_leftbottom_switch -> switchFragment(2)
            R.id.btn_startwork ->  F.connectWSocket(context, url)/*changeWorkState(0)*/
            R.id.btn_stopwork -> changeWorkState(1)
            R.id.btn_endwork -> changeWorkState(2)
            R.id.btn_continuework -> changeWorkState(3)
        }
    }

    private fun changeWorkState(indexWork: Int) {
        when (indexWork) {
            0 -> {//自动作业
                WORKSTATE = 1
                reInitBtnView()
            }
            1 -> {//暂停作业
                WORKSTATE = 2
                reInitBtnView()
            }
            2 -> {//结束作业
                wsManager?.stopConnect()

                WORKSTATE = 0
                reInitBtnView()
            }
            3 -> {//继续作业
                WORKSTATE = 1
                reInitBtnView()
            }
        }
    }

    private fun reInitBtnView() {//WORKSTATE工作状态 0：初始，1：运行，2：暂停
        when (WORKSTATE) {
            0 -> {
                btn_startwork.visibility = View.VISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.INVISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
            1 -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.VISIBLE
                btn_endwork.visibility = View.VISIBLE
                btn_continuework.visibility = View.INVISIBLE
            }
            2 -> {
                btn_startwork.visibility = View.INVISIBLE
                btn_stopwork.visibility = View.INVISIBLE
                btn_endwork.visibility = View.VISIBLE
                btn_continuework.visibility = View.VISIBLE
            }
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
            }
            1112 -> {
                when (obj as Int) {
                    1 -> Helper.toast("WEBSOCKET CONNECTED")
                    0 -> Helper.toast("WEBSOCKET CONNECTING")
                    2 -> Helper.toast("WEBSOCKET RECONNECT")
                    -1 -> Helper.toast("WEBSOCKET DISCONNECTED")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        F.stopConnectWSocket()
    }
}