package com.deepblue.aidevicemanager.ws;

/**
 * 实时状态
 * @author zhangkl
 *
 */
public class LiveStatus {

	private String BStart; // 启动自动驾驶标识：0停止，1启动自动驾驶
	private String RunState; // 车辆状态：0不可行驶，1可以行驶
	private String NavState; // 组合导航状态
	private String GpsState; // GPS状态
	private Double Longtitude; // 经度（°）
	private Double Latitude; // 维度（°）
	private Float Altitude; // 海拔(m, 高程)
	private Float Speed; // 水平速度（m/s）
	private String LightStatus; // 转向灯状态：0直行，1左转，2（-1）右转
	private String SpoutWater; // 喷水状态：0停止碰水，1启动喷水
	private String CleanStatus; // 清扫状态：0停止清扫，1启动清扫
	private String CleanAsh; // 清灰状态：0停止清灰，1启动清灰
	private String RoadStatus; // 路径规划状态 5（-2）：定位无效，4（-1）为异常状态：规划失败，0：正常规划状态，1：超声波雷达有效，3:停车
	private String Error; //故障码
//	char Error[LONG_DOUBLE];   //故障码

	private String GPSStatus;  // GPS状态
	private String Map;  //地图ID
	private String NavAngle;//惯导状态
	private String X;//实时位置，可能是经度或相对X坐标+末尾'\0'
	private String Y; //实时位置，可能是纬度或相对Y坐标+末尾'\0'
	private String batteryLevel; //剩余电量,0-100
	private String chargingStatus;//充电状态，1-充电中，2-已充满，3-未充电
	private String laserStatus;    //激光雷达状态;1-正常；2-故障
	private String roadHual;//已行驶里程，单位米
	private String workStatus;//工作状态，工作中时填写任务ID，未工作时填0
	private String ultrasonicStatus; //超声波传感器状态 ;1-正常；2-故障
	private String cameraStatus;//视觉波传感器状态 ;1-正常；2-故障
	
    //洗地机参数
	private String pureWater;  // 净水量（1-高水量、2-低水量、3-正常水量）
	private String wasteWater;  //废水量（1-高水量、2-低水量、3-正常水量）
	private String lastLoginUser;//最后登录用户ID
	private String lastStartBegin;//最后启动时间
	private String batteryStatus; //电量状态（1-电量百分比，2-正常电量、3-低电量、4-危险电量）
	private String internetStatus; //4G/WiFi网络状态（1-连接、2-断开）
	private String sensorStatus;//传感器状态 1-正常；2-故障
	
	//高铁小宝
	private String railwayWorkStatus;//工作状态
	private String totalDistance; //累计行驶里程
	private String totalTime; //设备累计使用时间
	private String totalOpenDoors; //累计开关门次数
	private String totalVisionCameraTime; //视觉摄像头累计使用时间
	private String totalCameraTime; //摄像头累计使用时间
	private String totalUltraSonic; //超声波累计使用时间
	private String currentCoach; //当前车厢
	
	//消防机器人  剩余电量、水平速度、GPS状态、路径规划状态 用之前的
	private String workMode;//工作模式 1-自动 0-手动
	private String brakeSignalFeedback; //制动信号反馈
	private String lampStatus; //照明灯状态
	private String waterVolume; //水箱水量（液位）
	private String temperatureSensorFront; //温度传感器（前方位）
	private String temperatureSensorRear; //温度传感器（后方位）
	private String temperatureSensorLeft; //温度传感器（左侧）
	private String temperatureSensorRight; //温度传感器（右侧）
	private String lightInfo; //灯光信息
	private String waterCannonStatus; //水炮功能状态（喷射、自保护喷淋）
	private String windDirectionSensor; //风向传感器
	private String windSpeedSensor; //风速传感器
	private String autoProtect; //自动保护

	
	
	

}
