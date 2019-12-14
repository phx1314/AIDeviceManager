package com.deepblue.aidevicemanager.model;

import java.io.Serializable;

public class ModelB implements Serializable {




    public String data_battery_voltage;  // 电压
    public String data_battery_remaining_capacity;//当前电量
    public String data_battery_capacity; //电容
    public String BatterySeriesNumber;//电池编号
    public String BatteryCurrent;  //电流
    public String data_battery_temperature;//温度
    public String data_reverse_light;//倒车灯
    public String data_brake_light;//刹车灯
    public String data_left_light;//左转向灯状态
    public String data_right_light; //右转向灯状态
    public String data_high_beam_light;//远光灯
    public String data_low_beam_light; //近光灯
    public String data_width_light; //示廓灯
    public String data_light;//工作灯, 0:关，1：开, rw
    public String data_emergency_light;//应急灯,0:关，1：开 , rw


    public String data_lidar_status;//激光雷达1

    public String data_millimeter_wave_status; //毫米波雷达1

    public String data_ultrasonic_wave_status;//超声波1

    public String data_zed_status; //ZED相机


    public String data_dtu_signal; //dtu状态信息(-1：offline， 0-100), ro
    public String data_imu_status;   //IMU状态：0:有问题，1：正常, ro
    public String data_rtk_status; //RTK信号强度
    public String data_gear;  //档位
    public String data_throttle_value; //油门值
    public String data_brake_value;//刹车状态
    public String data_manual_brake;//手刹状态
    public String data_velocity;//车速

    public String data_water_level;//清水箱液位: 0-100%
    public String data_trash_level; //垃圾箱位置
    public String data_brush_position;//扫刷位置
    public String data_brush_status;//扫刷状态
    public String data_suction_status;//吸风状态
    public String data_spout_water;//喷水状态
    public String data_suction_inlet_position;//吸风口位置 ,0:关，1：开

    public String data_gps_signal;//GPS信号
    public String data_wifi_signal;//WiFi信号
    public String data_telcom_signal;//4g/5g状态信息 （-1：offline， 0-100）

    public String data_latitude;  //纬度
    public String data_longitude; //经度
    public String data_yaw_angle;  //车头指向方向

    public String data_sweeper_state;   //系统状态： 0：待机， 1：自动作业中， 2：手动驾驶中，  3： 故障, ro
    public String data_software_status;  //自动驾驶算法加载状态： 0:有问题，1：正常

    public String data_fault_list;     //以\n分隔的字符串:"1-1-1\n2-1-1", ro
    public String data_car_state;   //ro
    public String data_module_state_changed;  //ro

    public String data_software_version;//软件版本， ro
    public String data_algorithm_version; //算法版本， ro
    public String data_vcu_version;//VCU版本： -1:N/A, ro
}
