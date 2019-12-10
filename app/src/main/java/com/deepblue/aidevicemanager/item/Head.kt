//
//  Head
//
//  Created by 86139 on 2019-11-22 08:25:55
//  Copyright (c) 86139 All rights reserved.


/**
 *
 */

package com.deepblue.aidevicemanager.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.deepblue.aidevicemanager.F
import com.deepblue.aidevicemanager.F.mModelStatus
import com.deepblue.aidevicemanager.R
import com.deepblue.aidevicemanager.pop.PopShowSet
import com.mdx.framework.Frame
import com.mdx.framework.activity.BaseActivity
import com.mdx.framework.util.AbDateUtil
import kotlinx.android.synthetic.main.item_head.view.*


class Head(context: Context?) : LinearLayout(context) {
    init {
        val flater = LayoutInflater.from(context)
        flater.inflate(R.layout.item_head, this)

        mImageView_logo.setOnClickListener {
            Frame.HANDLES.closeWidthOut("FrgMain,FrgMainSon,FrgInfoChange,FrgMimaChange,FrgXx,FrgWebView")
            Frame.HANDLES.sentAll("FrgMain", 0, null)
        }
    }

    fun set(item: String) {
    }

    fun setShowPop(view: View) {
        mImageButton_set.visibility = View.VISIBLE
        mImageButton_set.setOnClickListener {
            var mPopShowSet = PopShowSet(getContext(), mImageButton_set, view)
            view.tag = mPopShowSet
            mPopShowSet.show()
        }
    }

    fun canGoBack(b: Boolean = true) {
        if (b) mImageButton_back.visibility = View.VISIBLE else mImageButton_back.visibility =
            View.GONE
        mImageButton_back.setOnClickListener {
            (context as BaseActivity).finish()
        }
    }

    fun setXxIsShow(isShow: Boolean) {
        if (isShow) mImageView_dot.visibility = View.VISIBLE else mImageView_dot.visibility =
            View.GONE
    }

    fun setTitle(s: String) {
        mTextView_title.visibility = View.VISIBLE
        mTextView_title.text = s
    }

    fun setBatteryStatus(level: Int, type: Int) {
        if (level < 0) mTextView_dc.text = "N/A" else mTextView_dc.text = level.toString() + "%"

        when {
            level in 0..5 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_alert_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_alert_black_24dp else 0, 0
                )
            }
            level in 6..20 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_20_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_20_black_24dp else 0, 0
                )
            }
            level in 21..30 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_30_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_30_black_24dp else 0, 0
                )
            }
            level in 31..50 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_50_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_50_black_24dp else 0, 0
                )
            }
            level in 51..60 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_60_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_60_black_24dp else 0, 0
                )
            }
            level in 61..80 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_80_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_80_black_24dp else 0, 0
                )
            }
            level in 81..90 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_90_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_90_black_24dp else 0, 0
                )
            }
            level in 91..100 -> {
                mTextView_dc.setCompoundDrawablesWithIntrinsicBounds(
                    if (type == 0) R.drawable.ic_battery_full_black_24dp else 0, 0, if (type == 1) R.drawable.ic_battery_full_black_24dp else 0, 0
                )
            }
        }
    }

    fun setStatus(from: String = "") {
        if (mModelStatus?.mModelB != null && (from.equals("FrgDetailDj") || from.equals("FrgWorkDetail") || from.equals("FrgWorkChoose"))) {
            when {
                mModelStatus?.mModelB?.data_gps_signal?.toInt() in 0..25 -> {
                    mRelativeLayout_gps.visibility = View.VISIBLE
                    mImageView_gps.setImageResource(R.drawable.ic_signal_cellular_1_bar_black_24dp)
                }
                mModelStatus?.mModelB?.data_gps_signal?.toInt() in 26..50 -> {
                    mRelativeLayout_gps.visibility = View.VISIBLE
                    mImageView_gps.setImageResource(R.drawable.ic_signal_cellular_2_bar_black_24dp)
                }
                mModelStatus?.mModelB?.data_gps_signal?.toInt() in 51..75 -> {
                    mRelativeLayout_gps.visibility = View.VISIBLE
                    mImageView_gps.setImageResource(R.drawable.ic_signal_cellular_3_bar_black_24dp)
                }
                mModelStatus?.mModelB?.data_gps_signal?.toInt() in 76..100 -> {
                    mRelativeLayout_gps.visibility = View.VISIBLE
                    mImageView_gps.setImageResource(R.drawable.ic_signal_cellular_4_bar_black_24dp)
                }
                else -> {
                    mRelativeLayout_gps.visibility = View.GONE
                }

            }
            when {
                mModelStatus?.mModelB?.data_wifi_signal?.toInt() in 0..25 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_2_bar_black_24dp)
                }
                mModelStatus?.mModelB?.data_wifi_signal?.toInt() in 26..50 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_3_bar_black_24dp)
                }
                mModelStatus?.mModelB?.data_wifi_signal?.toInt() in 51..75 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_4_bar_black_24dp)
                }
                mModelStatus?.mModelB?.data_wifi_signal?.toInt() in 76..100 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_4_bar_black_24dp)
                }
                else -> {
                    mImageView_wifi.visibility = View.GONE
                }

            }

            when {
                mModelStatus?.mModelB?.data_telcom_signal?.toInt() in 0..25 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_1_bar_black_24dp)

                }
                mModelStatus?.mModelB?.data_telcom_signal?.toInt() in 26..50 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_2_bar_black_24dp)

                }
                mModelStatus?.mModelB?.data_telcom_signal?.toInt() in 51..75 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_3_bar_black_24dp)

                }
                mModelStatus?.mModelB?.data_telcom_signal?.toInt() in 76..100 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_4_bar_black_24dp)

                }
                else -> {
                    mRelativeLayout_4g.visibility = View.GONE
                }

            }
            setBatteryStatus(mModelStatus?.mModelB?.data_battery_remaining_capacity?.toInt() ?: -1, 0)
            mTextView_time.visibility = View.GONE
        } else {
            mTextView_time.visibility = View.VISIBLE
            mTextView_time.text = AbDateUtil.getCurrentDate("HH:mm")
            setBatteryStatus(mModelStatus?.batteryLevel ?: -1, 1)
            mRelativeLayout_gps.visibility = View.GONE
            when (F.checkWifiState(context)) {
                -1 -> {
                    mImageView_wifi.visibility = View.GONE
                }
                0 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_1_bar_black_24dp)
                }
                1 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_2_bar_black_24dp)
                }
                2 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_3_bar_black_24dp)
                }
                3 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_4_bar_black_24dp)
                }
                4 -> {
                    mImageView_wifi.visibility = View.VISIBLE
                    mImageView_wifi.setImageResource(R.drawable.ic_signal_wifi_4_bar_black_24dp)
                }
            }
            when (mModelStatus?.g4Level) {
                -1 -> {
                    mRelativeLayout_4g.visibility = View.GONE
                }
                0 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_0_bar_black_24dp)
                }
                1 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_1_bar_black_24dp)
                }
                2 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_2_bar_black_24dp)
                }
                3 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_3_bar_black_24dp)
                }
                4 -> {
                    mRelativeLayout_4g.visibility = View.VISIBLE
                    mImageView_4g.setImageResource(R.drawable.ic_signal_cellular_4_bar_black_24dp)
                }
                else -> {
                    mRelativeLayout_4g.visibility = View.GONE
                }
            }

        }
    }

}