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
import android.widget.ExpandableListView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_head.view.*
import okhttp3.internal.notify


class FrgMain : BaseFrg() {
    var mFrgMainSon: FrgMainSon = FrgMainSon()
    var groupString = arrayOf<String>("用户", "消息中心", "帮助", "用户条款", "隐私政策", "关于", "退出登录")
    var childString = arrayOf<Array<String>>(
        arrayOf<String>("信息更改", "密码更改"),
        arrayOf<String>("作业任务消息", "报警消息", "故障消息")
    )

    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_main)
    }


    override fun initView() {
        mHead.mImageView_user.setOnClickListener {
            if (mExpandableListView.visibility == View.VISIBLE) mExpandableListView.visibility =
                View.INVISIBLE else mExpandableListView.visibility = View.VISIBLE
        }

//        Handler().postDelayed({
//            childString[1][0] = "作业任务消息(1)"
//            (mExpandableListView.expandableListAdapter as ExpandableListviewAdapter).notifyDataSetChanged()
//        }, 3000)
    }


    override fun loaddata() {
        mTextView_gs.text = F.mModellogin?.merchant?.contactName + " >"
        mTextView_name.text = F.mModellogin?.merchant?.name
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
                6 -> F.logOut(context)
            }
            false
        }

        mExpandableListView.setOnGroupClickListener { _, _, groupPosition, _ ->
            if (groupPosition == 0) {
                false
            } else false
        }
    }

    override fun onSuccess(data: String?, method: String) {
    }

    private fun chageWebFrgment(url: String) {
        var mFrgWebView = FrgWebView()
        val bundle = Bundle()
        bundle.putString("url", url)
        mFrgWebView.arguments = bundle
        chageFrgment(mFrgWebView)
    }

    private fun chageFrgment(fragment: Fragment) {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.mLinearLayout_content, fragment)
        transaction.commit()
    }

}