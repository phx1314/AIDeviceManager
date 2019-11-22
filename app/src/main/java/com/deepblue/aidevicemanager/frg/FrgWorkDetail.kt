package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
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
            PAGE_DIANYUN to FrgWDOverView(),
            PAGE_VEDIO to FrgWDOverView(),
            PAGE_OVERVIEW to FrgWDOverView(),
            PAGE_ROUTE to FrgWDRoute()
        )
        childFragmentManager.beginTransaction()
            .add(R.id.ll_quanjing, fragments[PAGE_OVERVIEW]!!, PAGE_OVERVIEW.toString())
            .add(R.id.ll_route, fragments[PAGE_ROUTE]!!, PAGE_ROUTE.toString())
            .commit()

//        childFragmentManager.tag
    }

    override fun loaddata() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_leftbottom_switch -> {
                val a = (fragments[PAGE_DIANYUN]!!.view!!.parent as FrameLayout).id
                val b = (fragments[PAGE_VEDIO]!!.view!!.parent as FrameLayout).id
                val c = (fragments[PAGE_OVERVIEW]!!.view!!.parent as FrameLayout).id
                val d = (fragments[PAGE_ROUTE]!!.view!!.parent as FrameLayout).id
//                childFragmentManager.popBackStackImmediate(
//                    null,
//                    FragmentManager.POP_BACK_STACK_INCLUSIVE
//                )
//                childFragmentManager.beginTransaction()
//                    .remove(fragments[PAGE_OVERVIEW]!!)
//                    .remove(fragments[PAGE_ROUTE]!!)
//                    .commit()
//                childFragmentManager.executePendingTransactions()
//                childFragmentManager.beginTransaction()
//                    .replace(R.id.ll_quanjing, fragments[PAGE_ROUTE]!!)
//                    .replace(R.id.ll_route, fragments[PAGE_OVERVIEW]!!)
//                    .commit()
//
//                childFragmentManager.beginTransaction().
            }
            else -> {

            }
        }
    }

    private fun switchFragment(typeIndex: Int) {
        when (typeIndex) {
            0 -> {

            }
            else -> {

            }
        }
    }
}