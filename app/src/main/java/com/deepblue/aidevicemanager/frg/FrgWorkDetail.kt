package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.frg_workdetail.*
import java.util.HashMap

class FrgWorkDetail : BaseFrg() {
    private lateinit var fragments: HashMap<Int, Fragment>
    private var WORKSTATE = 0  //工作状态 0：初始，1：运行，2：暂停
    private val PAGE_DIANYUN = 0
    private val PAGE_VEDIO = 1
    private val PAGE_OVERVIEW = 2
    private val PAGE_ROUTE = 3

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
            R.id.iv_lefttop_switch -> switchFragment(0)
            R.id.iv_leftcenter_switch -> switchFragment(1)
            R.id.iv_leftbottom_switch -> switchFragment(2)
            R.id.btn_startwork -> changeWorkState(0)
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
        val frg1_new = getContainerFragment(frg2Old)
        val frg2_new = getContainerFragment(frg1Old)

        if (frg1_new != null && frg2_new != null && frg1Old != null && frg2Old != null) {
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
                .replace(a1, frg1_new)
                .replace(a2, frg2_new)
                .commit()
        }
    }

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

    //***********************************************************************8

    private fun initListener() {
        iv_leftbottom_switch.setOnClickListener(this)
        iv_lefttop_switch.setOnClickListener(this)
        iv_leftcenter_switch.setOnClickListener(this)
        btn_startwork.setOnClickListener(this)
        btn_stopwork.setOnClickListener(this)
        btn_continuework.setOnClickListener(this)
        btn_endwork.setOnClickListener(this)
    }

}