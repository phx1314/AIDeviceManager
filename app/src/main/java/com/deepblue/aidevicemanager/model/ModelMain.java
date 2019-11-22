package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelMain {


    /**
     * id : 42.0
     * seriesCode : Panda_Cleaning_Robot
     * seriesName : 熊猫扫路王机器人
     * seriesRemark : 熊猫扫路王机器人
     * isDelete : 1
     * createTime : 2019-06-03 18:13:45
     * createUser : 郑亮亮
     * midifyTime : 2019-06-03 18:13:45
     * modifyUser : sys
     * deviceModelNum : 0.0
     * deviceVersionNum : 0.0
     * deviceNum : 3.0
     * deviceList : [{"id":355,"deviceCode":"00000043cAYW","deviceName":"熊猫1号","deviceProductionDate":"2019-11-11 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceTaskStatus":"1","deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31"},{"id":356,"deviceCode":"000000449naC","deviceName":"熊猫2号","deviceProductionDate":"2019-10-29 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceTaskStatus":"1","deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31"},{"id":357,"deviceCode":"00000045w2R7","deviceName":"熊猫3号","deviceProductionDate":"2019-11-19 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceTaskStatus":"1","deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31"}]
     * deviceStatusCount : {"noActive":3,"offLine":0,"standBy":0,"onTask":0,"recordRoute":0,"autoCharge":0,"warning":0,"error":0,"manualCharge":0,"relocate":0,"createMap":0}
     */

    public double id;
    public String seriesCode;
    public String seriesName;
    public String seriesRemark;
    public String isDelete;
    public String createTime;
    public String createUser;
    public String midifyTime;
    public String modifyUser;
    public double deviceModelNum;
    public double deviceVersionNum;
    public double deviceNum;
    public DeviceStatusCountBean deviceStatusCount;
    public List<DeviceListBean> deviceList;

    public static class DeviceStatusCountBean {
        /**
         * noActive : 3.0
         * offLine : 0.0
         * standBy : 0.0
         * onTask : 0.0
         * recordRoute : 0.0
         * autoCharge : 0.0
         * warning : 0.0
         * error : 0.0
         * manualCharge : 0.0
         * relocate : 0.0
         * createMap : 0.0
         */

        public double noActive;
        public double offLine;
        public double standBy;
        public double onTask;
        public double recordRoute;
        public double autoCharge;
        public double warning;
        public double error;
        public double manualCharge;
        public double relocate;
        public double createMap;
    }

    public static class DeviceListBean {
        /**
         * id : 355.0
         * deviceCode : 00000043cAYW
         * deviceName : 熊猫1号
         * deviceProductionDate : 2019-11-11 00:00:00
         * deviceMerchantId : 110.0
         * deviceMerchantName : 上海BUS
         * deviceSeriesId : 42.0
         * deviceModelId : 56.0
         * deviceVersionId : 259.0
         * deviceStatus : 0
         * isDelete : 1
         * createTime : 2019-11-01 13:44:22
         * createUser : admin
         * midifyTime : 2019-11-01 13:44:22
         * modifyUser : sys
         * deviceTaskStatus : 1
         * deviceMaintainStatus : 1
         * seriesCode : Panda_Cleaning_Robot
         * seriesName : 熊猫扫路王机器人
         * modelCode : 2019_10_31_model
         * modelName : 2019_10_31_model
         * versionNum : v_x_10_31
         */

        public double id;
        public String deviceCode;
        public String deviceName;
        public String deviceProductionDate;
        public double deviceMerchantId;
        public String deviceMerchantName;
        public double deviceSeriesId;
        public double deviceModelId;
        public double deviceVersionId;
        public String deviceStatus;
        public String isDelete;
        public String createTime;
        public String createUser;
        public String midifyTime;
        public String modifyUser;
        public String deviceTaskStatus;
        public String deviceMaintainStatus;
        public String seriesCode;
        public String seriesName;
        public String modelCode;
        public String modelName;
        public String versionNum;
    }
}
