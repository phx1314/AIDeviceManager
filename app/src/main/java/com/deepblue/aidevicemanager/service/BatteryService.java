package com.deepblue.aidevicemanager.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.deepblue.aidevicemanager.F;
import com.mdx.framework.Frame;

import java.text.SimpleDateFormat;

import timber.log.Timber;

public class BatteryService extends Service {

    private static final String TAG = "BatteryReceiver";
    TelephonyManager tm;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        IntentFilter batteryfilter = new IntentFilter();
        batteryfilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, batteryfilter);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY; //
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(batteryReceiver);
    }

    // 接收电池信息更新的广播
    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra("status", 0);
            int level = intent.getIntExtra("level", 0);
            int scale = intent.getIntExtra("scale", 0);
            int plugged = intent.getIntExtra("plugged", 0);
            int voltage = intent.getIntExtra("voltage", 0);

            String statusString = "";
            switch (status) {
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    statusString = "unknown";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    statusString = "charging";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    statusString = "discharging";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    statusString = "not charging";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    statusString = "full";
                    break;
            }
            String acString = "";

            switch (plugged) {
                case BatteryManager.BATTERY_PLUGGED_AC:
                    acString = "plugged ac";
                    break;
                case BatteryManager.BATTERY_PLUGGED_USB:
                    acString = "plugged usb";
                    break;
            }

            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS ");
            String date = sDateFormat.format(new java.util.Date());

            Log.i(TAG, "battery: date=" + date + ",status " + statusString + ",level=" + level +
                    ",scale=" + scale + ",voltage=" + voltage + ",acString=" + acString);
            F.INSTANCE.getMModelStatus().batteryLevel = level;
            tm.listen(mylistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
            Frame.HANDLES.sentAll(1110, "");
        }
    };

    PhoneStateListener mylistener = new PhoneStateListener() {
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            Timber.d("4g");
            super.onSignalStrengthsChanged(signalStrength);
            String signalInfo = signalStrength.toString();
            String[] params = signalInfo.split(" ");
            if (tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_UNKNOWN) {
                F.INSTANCE.getMModelStatus().g4Level = -1;
            } else if (tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE || tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_NR) {
                //4G网络 最佳范围   >-90dBm 越大越好
                int Itedbm = Integer.parseInt(params[9]);
//                    setDBM(Itedbm + "");
                F.INSTANCE.getMModelStatus().g4Level = Itedbm;
            } else if (tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA || tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPA || tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSUPA || tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS) {
//                    //3G网络最佳范围  >-90dBm  越大越好  ps:中国移动3G获取不到  返回的无效dbm值是正数（85dbm）
//                    //在这个范围的已经确定是3G，但不同运营商的3G有不同的获取方法，故在此需做判断 判断运营商与网络类型的工具类在最下方
//                    String yys = IntenetUtil.getYYS(getApplication());//获取当前运营商
//                    if (yys == "中国移动") {
//                        setDBM(0 + "");//中国移动3G不可获取，故在此返回0
//                    } else if (yys == "中国联通") {
//                        int cdmaDbm = signalStrength.getCdmaDbm();
//                        setDBM(cdmaDbm + "");
//                    } else if (yys == "中国电信") {
//                        int evdoDbm = signalStrength.getEvdoDbm();
//                        setDBM(evdoDbm + "");
//                    }

            } else {

            }
            Frame.HANDLES.sentAll(1110, "");
        }
    };
}