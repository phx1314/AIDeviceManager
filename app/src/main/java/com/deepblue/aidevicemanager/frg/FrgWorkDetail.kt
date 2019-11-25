package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.FrameLayout
import com.deepblue.aidevicemanager.R
import kotlinx.android.synthetic.main.frg_workdetail.*
import java.util.HashMap

class FrgWorkDetail : BaseFrg() {
    private lateinit var fragments: HashMap<Int, Fragment>
    private val PAGE_DIANYUN = 0
    private val PAGE_VEDIO = 1
    private val PAGE_OVERVIEW = 2
    private val PAGE_ROUTE = 3

    override fun create(var1: Bundle?) {
        setContentView(R.layout.frg_workdetail)
    }

    override fun initView() {
        iv_leftbottom_switch.setOnClickListener(this)
        fragments = hashMapOf(
//            PAGE_DIANYUN to FrgWDOverView(),
//            PAGE_VEDIO to FrgWDOverView(),
            PAGE_OVERVIEW to FrgWDOverView(),
            PAGE_ROUTE to FrgWDRoute()
        )
        childFragmentManager.beginTransaction()
//            .add(R.id.ll_lefttop, fragments[PAGE_OVERVIEW]!!, PAGE_OVERVIEW.toString())
//            .add(R.id.ll_leftcenter, fragments[PAGE_ROUTE]!!, PAGE_ROUTE.toString())
            .add(R.id.ll_leftbottom, fragments[PAGE_OVERVIEW]!!, PAGE_OVERVIEW.toString())
            .add(R.id.ll_right, fragments[PAGE_OVERVIEW]!!, PAGE_ROUTE.toString())
            .commit()

//        childFragmentManager.tag
    }

    override fun loaddata() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_lefttop_switch -> {
                switchFragment(0)
            }
            R.id.iv_leftcenter_switch -> {
                switchFragment(1)
            }
            R.id.iv_leftbottom_switch -> {
                switchFragment(2)
            }
        }
    }

    private fun switchFragment(typeIndex: Int) { // 0: 左上切换  1: 左中切换  2: 左下切换
        when (typeIndex) {
            0 -> {
                doSwitchFrg(ll_lefttop.id, ll_right.id)
            }
            1 -> {
                doSwitchFrg(ll_leftcenter.id, ll_right.id)
            }
            2 -> {
                doSwitchFrg(ll_leftbottom.id, ll_right.id)
            }
        }
    }

    private fun doSwitchFrg(a1: Int, a2: Int) {
        val frg1Old = childFragmentManager.findFragmentById(a1)
        val frg2Old = childFragmentManager.findFragmentById(a2)
        val containerID1 = (frg1Old?.view?.parent as FrameLayout).id
        val containerID2 = (frg2Old?.view?.parent as FrameLayout).id
        val frg1_new = getContainerFragment(a1)
        val frg2_new = getContainerFragment(a2)

        if (frg1_new != null && frg2_new != null && containerID1 != -1 && containerID2 != -1) {
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
                .replace(containerID1, frg1_new)
                .replace(containerID2, frg2_new)
                .commit()
        }
    }

    private fun getContainerFragment(aaa: Int): Fragment? {
//        val dianyunContainerID = (fragments[PAGE_DIANYUN]!!.view!!.parent as FrameLayout).id
//        val vedioContainerID = (fragments[PAGE_VEDIO]!!.view!!.parent as FrameLayout).id
        val overviewContainerID = (fragments[PAGE_OVERVIEW]!!.view!!.parent as FrameLayout).id
        val routeContainerID = (fragments[PAGE_ROUTE]!!.view!!.parent as FrameLayout).id
//        if (aaa == dianyunContainerID)
//            return PAGE_DIANYUN
//        if (aaa == vedioContainerID)
//            return PAGE_VEDIO
        if (aaa == overviewContainerID)
            return FrgWDRoute()
        if (aaa == routeContainerID)
            return FrgWDOverView()
        return null
    }
    //***********************************************************************8


//                    childFragmentManager.beginTransaction().


}