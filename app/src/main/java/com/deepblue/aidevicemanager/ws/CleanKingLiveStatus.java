package com.deepblue.aidevicemanager.ws;

/**
 * 扫路王APP实时状态
 */
public class CleanKingLiveStatus {

	private String data_battery_voltage;  // 电压
	private String BatteryCurrent;  //电流
	private String data_battery_temperature;//温度
	private String data_battery_remaining_capacity;//当前电量
	private String data_battery_capacity; //电容
	private String BatteryCycle; //循环次数
	private String BatterySeriesNumber;//电池编号
	private String BatteryLowWarning;    //低电量报警
	private String BatteryLowCritical;//严重低电量报警

	private String data_reverse_light;//倒车灯
	private String data_brake_light;//刹车灯
	private String data_left_light;//左转向灯状态
	private String data_right_light; //右转向灯状态
	private String data_high_beam_light;//远光灯
	private String data_low_beam_light; //近光灯
	private String data_width_light; //示廓灯


	private String data_lidar1_status;//激光雷达1
	private String data_lidar2_status; //激光雷达2
	private String data_lidar3_status;//激光雷达3
	private String data_lidar4_status;  // 激光雷达4
	private String data_lidar5_status;  // 激光雷达5
	private String data_lidar6_status;  //激光雷达6
	private String data_lidar7_status;//激光雷达7
	private String data_lidar8_status;//激光雷达8

	private String data_millimeter_wave1_status; //毫米波雷达1
	private String data_millimeter_wave2_status; //毫米波雷达2
	private String data_millimeter_wave3_status;//毫米波雷达3
	private String data_millimeter_wave4_status;//毫米波雷达4
	private String data_millimeter_wave5_status; //毫米波雷达5
	private String data_millimeter_wave6_status; //毫米波雷达6
	private String data_millimeter_wave7_status; //毫米波雷达7
	private String data_millimeter_wave8_status; //毫米波雷达8

	private String data_ultrasonic_wave1_status;//超声波1
	private String data_ultrasonic_wave2_status; //超声波2
	private String data_ultrasonic_wave3_status; //超声波3
	private String data_ultrasonic_wave4_status; //超声波4
	private String data_ultrasonic_wave5_status; //超声波5
	private String data_ultrasonic_wave6_status; //超声波6
	private String data_ultrasonic_wave7_status; //超声波7
	private String data_ultrasonic_wave8_status; //超声波8

	private String data_zed_status; //ZED相机
	private String IMUStatus; //IMU
	private String data_rtk_status; //RTK信号强度
	private String data_gear;  //档位
	private String data_throttle_value; //油门值
	private String data_brake_value;//刹车状态
	private String data_manual_brake;//手刹状态
	private String data_velocity;//车速

	private String data_water_level;//清水箱液位: 0-100%
	private String data_trash_level; //垃圾箱位置
	private String data_brush_position;//扫刷位置
	private String data_brush_status;//扫刷状态
	private String data_suction_status;//吸风状态
	private String data_spout_water;//喷水状态
	private String data_suction_inlet_position;//吸风口位置 ,0:关，1：开

	private String data_gps_signal ;//GPS信号
	private String data_wifi_signal;//WiFi信号
	private String data_telcom_signal;//4g/5g状态信息 （-1：offline， 0-100）

	private String data_latitude;  //纬度
	private String data_longitude; //经度
	private String data_yaw_angle;  //车头指向方向

	private String data_system_status;  //自动驾驶算法加载状态： 0:有问题，1：正常
	private String data_software_status;  //系统状态： 0：待机， 1：自动作业中， 2：手动驾驶中，  3： 故障

}
