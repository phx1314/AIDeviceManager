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
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.gB
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.ada.AdaMain
import com.deepblue.aidevicemanager.ada.ExpandableListviewAdapter
import com.deepblue.aidevicemanager.model.ModelMain
import com.mdx.framework.activity.IndexAct
import com.mdx.framework.activity.TitleAct
import com.mdx.framework.util.Helper
import kotlinx.android.synthetic.main.frg_main.*
import android.R.attr.fragment
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ExpandableListView
import android.widget.LinearLayout
import cn.qqtheme.framework.picker.TimePicker
import cn.qqtheme.framework.util.ConvertUtils
import com.deepblue.aidevicemanager.model.ModelBrokenXx
import com.deepblue.aidevicemanager.model.ModelTaskXx
import com.deepblue.aidevicemanager.model.ModelWaringXx
import kotlinx.android.synthetic.main.item_head.view.*
import okhttp3.internal.notify
import timber.log.Timber
import java.util.*


class FrgMain : BaseFrg() {
    var mFrgMainSon: FrgMainSon = FrgMainSon()
    lateinit var groupString: Array<String>
    lateinit var childString: Array<Array<String>>
    lateinit var mModelTaskXx: ModelTaskXx
    lateinit var mModelWaringXx: ModelWaringXx
    lateinit var mModelBrokenXx: ModelBrokenXx
    override fun initView() {
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
        when (type) {
            0 -> {
//                mExpandableListView.visibility = View.INVISIBLE
//                mExpandableListView.animation = AnimationUtils.makeOutAnimation(context, false)
                chageFrgment(mFrgMainSon)
            }
            1 -> {
                mTextView_name.text = F.mModellogin?.user?.name
            }
            2 -> {
                childString[1][0] = "${getString(R.string.d_task_xx) + "(" + obj.toString()})"
            }
            3 -> {
                childString[1][1] = "${getString(R.string.d_waring_xx) + "(" + obj.toString()})"
            }
            4 -> {
                childString[1][2] = "${getString(R.string.d_broken_xx) + "(" + obj.toString()})"
            }
            110 -> {
                F.logOut(context)
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
                3 -> chageWebFrgment(F.baseUrl + "privacy_policy.html")
                4 -> chageWebFrgment(F.baseUrl + "user_item.html")
                5 -> chageWebFrgment(F.baseUrl + "about.html")
                6 -> F.logOut(context, false)
            }
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
            true
        }
        load(gB().queryTaskListWithPage("1", "1"), "queryTaskListWithPage")
        load(gB().queryAlarmInfos("1", "1"), "queryAlarmInfos")
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
            childString[1][0] =
                "${getString(R.string.d_task_xx) + "(" + mModelTaskXx.total.toInt()})"
        } else if (method.equals("queryAlarmInfos")) {
            mModelWaringXx = F.data2Model(data, ModelWaringXx::class.java)
            childString[1][1] = "${getString(R.string.d_waring_xx) + "(" + mModelWaringXx.total})"
        } else if (method.equals("queryBreakdowns")) {
            mModelBrokenXx = F.data2Model(data, ModelBrokenXx::class.java)
            childString[1][2] = "${getString(R.string.d_broken_xx) + "(" + mModelBrokenXx.total})"
        }
        (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
    }

    private fun chageWebFrgment(url: String) {
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
        bundle.putInt("count1", mModelTaskXx.total.toInt())
        bundle.putInt("count2", mModelWaringXx.total)
        bundle.putInt("count3", mModelBrokenXx.total)
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