//
//  FrgLogin
//
//  Created by DELL on 2019-10-15 13:14:05
//  Copyright (c) DELL All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.frg

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.checked
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.ExpandableListviewAdapter
import com.deepblue.aidevicemanager.model.ModelBrokenXx
import com.deepblue.aidevicemanager.model.ModelTaskXx
import kotlinx.android.synthetic.main.frg_main.*
import kotlinx.android.synthetic.main.item_head.view.*


class FrgMain : BaseFrg() {

    var mFrgMainSon: FrgMainSon = FrgMainSon()
    lateinit var groupString: Array<String>
    lateinit var childString: Array<Array<String>>
    var mModelTaskXx: ModelTaskXx? = null
    var mModelWaringXx: ModelBrokenXx? = null
    var mModelBrokenXx: ModelBrokenXx? = null

    override fun initView() {
        checked = "-1"
        groupString = arrayOf<String>(
            getString(R.string.d_user),
            getString(R.string.d_xxzc),
            getString(R.string.d_help),
            getString(R.string.d_yhtk),
            getString(R.string.d_yszc),
            getString(R.string.d_about),
            getString(R.string.d_logout)
        )
        childString = arrayOf<Array<String>>(
            arrayOf<String>(getString(R.string.d_xxgg), getString(R.string.d_mmgg)),
            arrayOf<String>(
                getString(R.string.d_task_xx),
                getString(R.string.d_waring_xx),
                getString(R.string.d_broken_xx)
            )
        )
    }

    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_main)

    }

    override fun disposeMsg(type: Int, obj: Any?) {
        super.disposeMsg(type, obj)
        when (type) {
            0 -> {
//                mExpandableListView.visibility = View.INVISIBLE
//                mExpandableListView.animation = AnimationUtils.makeOutAnimation(context, false)
                checked = "-1"
                (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
                chageFrgment(mFrgMainSon)
            }
            1 -> {
                mTextView_name.text = F.mModellogin?.user?.name
            }
            2 -> {
                mModelTaskXx?.noReadCount = obj.toString()
                childString[1][0] = "${getString(R.string.d_task_xx) + "(" + obj.toString().toInt()})"
                (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
                doSomeThing(mModelTaskXx!!.noReadCount.toInt(), mModelWaringXx!!.noReadCount, mModelBrokenXx!!.noReadCount.toInt())
            }
            3 -> {
                mModelWaringXx!!.noReadCount = obj.toString().toInt()
                childString[1][1] = "${getString(R.string.d_waring_xx) + "(" + obj.toString()})"
                (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
                doSomeThing(mModelTaskXx!!.noReadCount.toInt(), mModelWaringXx!!.noReadCount, mModelBrokenXx!!.noReadCount.toInt())
            }
            4 -> {
                mModelBrokenXx!!.noReadCount = obj.toString().toInt()
                childString[1][2] = "${getString(R.string.d_broken_xx) + "(" + obj.toString()})"
                (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
                doSomeThing(mModelTaskXx!!.noReadCount.toInt(), mModelWaringXx!!.noReadCount, mModelBrokenXx!!.noReadCount.toInt())
            }
            110 -> {
                F.logOut(context)
            }
            120 -> {//消息
                load(gB().queryTaskListWithPage("1", "1"), "queryTaskListWithPage")
                load(gB().queryAlarmBreakdowns("1", "1"), "queryAlarmInfos")
                load(gB().queryBreakdowns("1", "1"), "queryBreakdowns")
            }
        }
    }


    override fun loaddata() {
        mTextView_gs.text = F.mModellogin?.merchant?.name + " >"
        mTextView_name.text = F.mModellogin?.user?.name
//        mMGridView.adapter = AdaMain(context, List<ModelMain>(5) { ModelMain() })
        chageFrgment(mFrgMainSon)
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setAdapter(ExpandableListviewAdapter(context, groupString, childString))
        mExpandableListView.setOnGroupClickListener { _, _, groupPosition, _ ->
            when (groupPosition) {
                2 -> chageWebFrgment(F.baseUrl + "configuration_help.html")
                3 -> chageWebFrgment(F.baseUrl + "user_item.html")
                4 -> chageWebFrgment(F.baseUrl + "privacy_policy.html")
                5 -> chageWebFrgment(F.baseUrl + "about.html")
                6 -> F.logOut(context, false)
            }
             checked = groupPosition.toString()
            false
        }
        mExpandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            when (groupPosition) {
                0 -> when (childPosition) {
                    0 -> chageFrgment(FrgInfoChange())
                    1 -> chageFrgment(FrgMimaChange())
                }
                1 -> chageXxFrgment(childPosition)
            }
            checked = groupPosition.toString() + childPosition.toString()
            (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
            true
        }
        load(gB().queryTaskListWithPage("1", "1"), "queryTaskListWithPage")
        load(gB().queryAlarmBreakdowns("1", "1"), "queryAlarmInfos")
        load(gB().queryBreakdowns("1", "1"), "queryBreakdowns")

//        mExpandableListView.setOnGroupClickListener { _, _, groupPosition, _ ->
//            if (groupPosition == 0) {
//                false
//            } else false
//        }
    }

    override fun onSuccess(data: String?, method: String) {
        if (method.equals("queryTaskListWithPage")) {
            mModelTaskXx = F.data2Model(data, ModelTaskXx::class.java)
            childString[1][0] = "${getString(R.string.d_task_xx) + "(" + mModelTaskXx!!.noReadCount.toDouble().toInt()})"
            if (mModelTaskXx!!.noReadCount.toInt() > 0) mHead.setXxIsShow(true)
            doSomeThing(mModelTaskXx!!.noReadCount.toInt(), 0, 0)
        } else if (method.equals("queryAlarmInfos")) {
            mModelWaringXx = F.data2Model(data, ModelBrokenXx::class.java)
            childString[1][1] =
                "${getString(R.string.d_waring_xx) + "(" + mModelWaringXx!!.noReadCount.toInt()})"
            if (mModelWaringXx!!.noReadCount.toInt() > 0) mHead.setXxIsShow(true)
        } else if (method.equals("queryBreakdowns")) {
            mModelBrokenXx = F.data2Model(data, ModelBrokenXx::class.java)
            childString[1][2] =
                "${getString(R.string.d_broken_xx) + "(" + mModelBrokenXx!!.noReadCount.toInt()})"
            if (mModelBrokenXx!!.noReadCount.toInt() > 0) mHead.setXxIsShow(true)
        }
        (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
    }

    fun doSomeThing(a: Int, b: Int, c: Int) {
        mHead.setXxIsShow((a + b + c) > 0)
    }

    fun chageWebFrgment(url: String) {
        var mFrgWebView = FrgWebView()
        val bundle = Bundle()
        bundle.putString("url", url)
        mFrgWebView.arguments = bundle
        chageFrgment(mFrgWebView)
    }

    private fun chageXxFrgment(type: Int) {
        var mFrgXx = FrgXx()
        val bundle = Bundle()
        bundle.putInt("type", type)
        bundle.putInt("count1", mModelTaskXx?.noReadCount?.toInt()?:0)
        bundle.putInt("count2", mModelWaringXx?.noReadCount?.toInt()?:0)
        bundle.putInt("count3", mModelBrokenXx?.noReadCount?.toInt()?:0)
        mFrgXx.arguments = bundle
        chageFrgment(mFrgXx)
    }

    private fun chageFrgment(fragment: Fragment) {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.mLinearLayout_content, fragment)
        transaction.commitAllowingStateLoss()
    }


    override fun setActionBar(actionBar: LinearLayout?) {
        super.setActionBar(actionBar)
        mHead.canGoBack(false)
        mHead.mRelativeLayout_user.visibility = View.VISIBLE
        mHead.mRelativeLayout_user.setOnClickListener {
            if (mExpandableListView.visibility == View.VISIBLE) {
                mExpandableListView.visibility = View.INVISIBLE
                mExpandableListView.animation = AnimationUtils.makeOutAnimation(context, false)
            } else {
                mExpandableListView.visibility = View.VISIBLE
                mExpandableListView.animation = AnimationUtils.makeInAnimation(context, true)
            }
        }
    }
}