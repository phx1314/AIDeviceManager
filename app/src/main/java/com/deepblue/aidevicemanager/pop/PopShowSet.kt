package com.deepblue.aidevicemanager.pop

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout.LayoutParams
import android.widget.PopupWindow
import com.mdx.framework.activity.BaseActivity


class PopShowSet(var context: Context, private val view: View, var popview: View) {
    private val popwindow: PopupWindow

    val isShow: Boolean
        get() = popwindow.isShowing

    init {
        val flater = LayoutInflater.from(context)
        popwindow = PopupWindow(popview, (context as BaseActivity).getWindowManager().getDefaultDisplay().getWidth() / 3 * 2, LayoutParams.WRAP_CONTENT)
        popwindow.setBackgroundDrawable(BitmapDrawable(context
                .resources))
        popwindow.isTouchable = true
        popwindow.isOutsideTouchable = true
        popwindow.isFocusable = true
    }


    @SuppressLint("NewApi")
    fun show() {
        popwindow.showAsDropDown(view, 0, 0)
    }

    fun hide() {
        popwindow.dismiss()
    }


}
