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


class FrgMain : BaseFrg() {
    var mFrgMainSon: FrgMainSon = FrgMainSon()
    var groupString = arrayOf<String>("用户", "消息中心", "帮助", "用户条款", "隐私政策", "关于", "退出登录")
    var childString = arrayOf<Array<String>>(arrayOf<String>("信息更改", "密码更改"), arrayOf<String>("作业任务消息", "报警消息", "故障消息"))
    override fun create(savedInstanceState: Bundle?) {
        setContentView(R.layout.frg_main)
    }

    override fun initView() {
    }


    override fun loaddata() {
        mTextView_gs.text = F.mModellogin?.merchant?.contactName + " >"
        mTextView_name.text = F.mModellogin?.merchant?.name
//        mMGridView.adapter = AdaMain(context, List<ModelMain>(5) { ModelMain() })
        chageFrgment(mFrgMainSon)
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setAdapter(ExpandableListviewAdapter(context, groupString, childString))
    }

    override fun onSuccess(data: String?, method: String) {

    }

    private fun chageFrgment(fragment: Fragment) {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.mLinearLayout_content, fragment)
        transaction.commit()
    }
}