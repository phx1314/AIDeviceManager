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
//    String data_lidar_status = "0";

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
//                if (data_lidar_status.equals("0")) {
//                    data_lidar_status = "1";
//                } else {
//                    data_lidar_status = "0";
//                }
//                Frame.HANDLES.sentAll("FrgDetailDj", 1111, "{\"cleanKingLiveStatus\":{\"data_lidar_status\":\"" + data_lidar_status + "\"}}");
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
            int level = intent.getIntExtra("level", 0);
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