package com.deepblue.aidevicemanager.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

import com.deepblue.aidevicemanager.F;
import com.mdx.framework.Frame;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BatteryService extends Service {

    private static final String TAG = "BatteryReceiver";
    TelephonyManager tm;
    ScheduledExecutorService mScheduledExecutorService;

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
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                tm.listen(mylistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
            }
        }, 0, 3, TimeUnit.SECONDS);
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
        this.unregisterReceiver(batteryReceiver);
        mScheduledExecutorService.shutdown();
        super.onDestroy();
    }

    // 接收电池信息更新的广播
    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            int status = intent.getIntExtra("status", 0);
            int level = intent.getIntExtra("level", 0);
//            int scale = intent.getIntExtra("scale", 0);
//            int plugged = intent.getIntExtra("plugged", 0);
//            int voltage = intent.getIntExtra("voltage", 0);
//
//            String statusString = "";
//            switch (status) {
//                case BatteryManager.BATTERY_STATUS_UNKNOWN:
//                    statusString = "unknown";
//                    break;
//                case BatteryManager.BATTERY_STATUS_CHARGING:
//                    statusString = "charging";
//                    break;
//                case BatteryManager.BATTERY_STATUS_DISCHARGING:
//                    statusString = "discharging";
//                    break;
//                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
//                    statusString = "not charging";
//                    break;
//                case BatteryManager.BATTERY_STATUS_FULL:
//                    statusString = "full";
//                    break;
//            }
//            String acString = "";
//
//            switch (plugged) {
//                case BatteryManager.BATTERY_PLUGGED_AC:
//                    acString = "plugged ac";
//                    break;
//                case BatteryManager.BATTERY_PLUGGED_USB:
//                    acString = "plugged usb";
//                    break;
//            }
//
//            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS ");
//            String date = sDateFormat.format(new java.util.Date());
            try {
                F.INSTANCE.getMModelStatus().batteryLevel = level;
                Frame.HANDLES.sentAll(1110, "");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

    PhoneStateListener mylistener = new PhoneStateListener() {
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            F.INSTANCE.getMModelStatus().g4Level = signalStrength.getLevel();
            Frame.HANDLES.sentAll(1110, "");
        }
    };
}