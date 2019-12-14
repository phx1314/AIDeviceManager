package com.deepblue.aidevicemanager.util

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.deepblue.aidevicemanager.frg.FrgWDLaser
import com.deepblue.aidevicemanager.frg.FrgWDOverView
import com.deepblue.aidevicemanager.frg.FrgWDRoute
import com.deepblue.aidevicemanager.frg.FrgWDVedio

class FrgWDManager(fragmentManager: FragmentManager, a1: Int, a2: Int, a3: Int, a4: Int) {
    var mFragmentManager: FragmentManager = fragmentManager
    val mA1 = a1
    val mA2 = a2
    val mA3 = a3
    val mA4 = a4

    init {
        mFragmentManager.popBackStackImmediate(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    fun changeFragment(frgName: String, llId1: Int, llId2: Int, typeIndex: Int) {
        if (mFragmentManager.findFragmentById(mA4).toString().toLowerCase().contains("frgwdroute")
            || mFragmentManager.findFragmentById(mA4).toString().toLowerCase().contains(frgName)
        )
            doTwoSwitchFrg(llId1, llId2)
        else
            doFourSwitchFrg(typeIndex)
    }

    private fun doFourSwitchFrg(typeIndex: Int) {
        mFragmentManager.beginTransaction()
            .remove(mFragmentManager.findFragmentById(mA1)).remove(mFragmentManager.findFragmentById(mA2))
            .remove(mFragmentManager.findFragmentById(mA3)).remove(mFragmentManager.findFragmentById(mA4))
            .commit()
        mFragmentManager.executePendingTransactions()
        when (typeIndex) {// 0: 左上切换  1: 左中切换  2: 左下切换
            0 -> {
                mFragmentManager.beginTransaction()
                    .replace(mA1, FrgWDRoute())
                    .replace(mA2, FrgWDVedio())
                    .replace(mA3, FrgWDOverView())
                    .replace(mA4, FrgWDLaser())
                    .commit()
            }
            1 -> {
                mFragmentManager.beginTransaction()
                    .replace(mA1, FrgWDLaser())
                    .replace(mA2, FrgWDRoute())
                    .replace(mA3, FrgWDOverView())
                    .replace(mA4, FrgWDVedio())
                    .commit()
            }
            2 -> {
                mFragmentManager.beginTransaction()
                    .replace(mA1, FrgWDLaser())
                    .replace(mA2, FrgWDVedio())
                    .replace(mA3, FrgWDRoute())
                    .replace(mA4, FrgWDOverView())
                    .commit()
            }
        }
    }

    private fun doTwoSwitchFrg(a1: Int, a2: Int) {
        val frg1Old = mFragmentManager.findFragmentById(a1)
        val frg2Old = mFragmentManager.findFragmentById(a2)
        val frgNew1 = getContainerFragment(frg2Old)
        val frgNew2 = getContainerFragment(frg1Old)

        if (frgNew1 != null && frgNew2 != null && frg1Old != null && frg2Old != null) {
            mFragmentManager.beginTransaction()
                .remove(frg1Old)
                .remove(frg2Old)
                .commit()
            mFragmentManager.executePendingTransactions()
            mFragmentManager.beginTransaction()
                .replace(a1, frgNew1)
                .replace(a2, frgNew2)
                .commit()
        }
    }

    @SuppressLint("DefaultLocale")
    private fun getContainerFragment(frg: Fragment?): Fragment? {
        return when {
            frg.toString().toLowerCase().contains("frgwdlaser") -> FrgWDLaser()
            frg.toString().toLowerCase().contains("frgwdvedio") -> FrgWDVedio()
            frg.toString().toLowerCase().contains("frgwdoverview") -> FrgWDOverView()
            frg.toString().toLowerCase().contains("frgwdroute") -> FrgWDRoute()
            else -> null
        }
    }
}