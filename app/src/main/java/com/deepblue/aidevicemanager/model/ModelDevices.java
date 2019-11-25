package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelDevices {


    /**
     * msg : 操作成功
     * code : 1111
     * data : {"total":3,"rows":[{"id":355,"deviceCode":"00000043cAYW","deviceName":"熊猫1号","deviceMac":null,"deviceProductionDate":"2019-11-11 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceWorkMap":null,"deviceBatchNo":null,"deviceFloor":null,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceOnlineStatus":null,"deviceTaskStatus":"1","offlineTime":null,"activateTime":null,"guaranteeBaseLocation":null,"deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31","deviceOnlineStatusName":null,"deviceTaskStatusName":null,"supportMap":null,"mapId":null,"mapName":null,"mapUrl":null,"mapGroupId":null,"mapGroupName":null,"cardStatus":null,"cardStatusName":null,"warningType":null,"warningTypeName":null,"province":null,"city":null,"isShow":null,"mapType":null,"liveStatus":null,"deviceMovedPositionDto":null,"frameNum":null},{"id":356,"deviceCode":"000000449naC","deviceName":"熊猫2号","deviceMac":null,"deviceProductionDate":"2019-10-29 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceWorkMap":null,"deviceBatchNo":null,"deviceFloor":null,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceOnlineStatus":null,"deviceTaskStatus":"1","offlineTime":null,"activateTime":null,"guaranteeBaseLocation":null,"deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31","deviceOnlineStatusName":null,"deviceTaskStatusName":null,"supportMap":null,"mapId":null,"mapName":null,"mapUrl":null,"mapGroupId":null,"mapGroupName":null,"cardStatus":null,"cardStatusName":null,"warningType":null,"warningTypeName":null,"province":null,"city":null,"isShow":null,"mapType":null,"liveStatus":null,"deviceMovedPositionDto":null,"frameNum":null},{"id":357,"deviceCode":"00000045w2R7","deviceName":"熊猫3号","deviceMac":null,"deviceProductionDate":"2019-11-19 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceWorkMap":null,"deviceBatchNo":null,"deviceFloor":null,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceOnlineStatus":null,"deviceTaskStatus":"1","offlineTime":null,"activateTime":null,"guaranteeBaseLocation":null,"deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31","deviceOnlineStatusName":null,"deviceTaskStatusName":null,"supportMap":null,"mapId":null,"mapName":null,"mapUrl":null,"mapGroupId":null,"mapGroupName":null,"cardStatus":null,"cardStatusName":null,"warningType":null,"warningTypeName":null,"province":null,"city":null,"isShow":null,"mapType":null,"liveStatus":null,"deviceMovedPositionDto":null,"frameNum":null}],"pageNum":1,"pageSize":20,"pages":1}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * total : 3
         * rows : [{"id":355,"deviceCode":"00000043cAYW","deviceName":"熊猫1号","deviceMac":null,"deviceProductionDate":"2019-11-11 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceWorkMap":null,"deviceBatchNo":null,"deviceFloor":null,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceOnlineStatus":null,"deviceTaskStatus":"1","offlineTime":null,"activateTime":null,"guaranteeBaseLocation":null,"deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31","deviceOnlineStatusName":null,"deviceTaskStatusName":null,"supportMap":null,"mapId":null,"mapName":null,"mapUrl":null,"mapGroupId":null,"mapGroupName":null,"cardStatus":null,"cardStatusName":null,"warningType":null,"warningTypeName":null,"province":null,"city":null,"isShow":null,"mapType":null,"liveStatus":null,"deviceMovedPositionDto":null,"frameNum":null},{"id":356,"deviceCode":"000000449naC","deviceName":"熊猫2号","deviceMac":null,"deviceProductionDate":"2019-10-29 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceWorkMap":null,"deviceBatchNo":null,"deviceFloor":null,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceOnlineStatus":null,"deviceTaskStatus":"1","offlineTime":null,"activateTime":null,"guaranteeBaseLocation":null,"deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31","deviceOnlineStatusName":null,"deviceTaskStatusName":null,"supportMap":null,"mapId":null,"mapName":null,"mapUrl":null,"mapGroupId":null,"mapGroupName":null,"cardStatus":null,"cardStatusName":null,"warningType":null,"warningTypeName":null,"province":null,"city":null,"isShow":null,"mapType":null,"liveStatus":null,"deviceMovedPositionDto":null,"frameNum":null},{"id":357,"deviceCode":"00000045w2R7","deviceName":"熊猫3号","deviceMac":null,"deviceProductionDate":"2019-11-19 00:00:00","deviceMerchantId":110,"deviceMerchantName":"上海BUS","deviceSeriesId":42,"deviceModelId":56,"deviceVersionId":259,"deviceWorkMap":null,"deviceBatchNo":null,"deviceFloor":null,"deviceStatus":"0","isDelete":"1","createTime":"2019-11-01 13:44:22","createUser":"admin","midifyTime":"2019-11-01 13:44:22","modifyUser":"sys","deviceOnlineStatus":null,"deviceTaskStatus":"1","offlineTime":null,"activateTime":null,"guaranteeBaseLocation":null,"deviceMaintainStatus":"1","seriesCode":"Panda_Cleaning_Robot","seriesName":"熊猫扫路王机器人","modelCode":"2019_10_31_model","modelName":"2019_10_31_model","versionNum":"v_x_10_31","deviceOnlineStatusName":null,"deviceTaskStatusName":null,"supportMap":null,"mapId":null,"mapName":null,"mapUrl":null,"mapGroupId":null,"mapGroupName":null,"cardStatus":null,"cardStatusName":null,"warningType":null,"warningTypeName":null,"province":null,"city":null,"isShow":null,"mapType":null,"liveStatus":null,"deviceMovedPositionDto":null,"frameNum":null}]
         * pageNum : 1
         * pageSize : 20
         * pages : 1
         */

        public int total;
        public int pageNum;
        public int pageSize;
        public int pages;
        public List<RowsBean> rows;

        public static class RowsBean {
            /**
             * id : 355
             * deviceCode : 00000043cAYW
             * deviceName : 熊猫1号
             * deviceMac : null
             * deviceProductionDate : 2019-11-11 00:00:00
             * deviceMerchantId : 110
             * deviceMerchantName : 上海BUS
             * deviceSeriesId : 42
             * deviceModelId : 56
             * deviceVersionId : 259
             * deviceWorkMap : null
             * deviceBatchNo : null
             * deviceFloor : null
             * deviceStatus : 0
             * isDelete : 1
             * createTime : 2019-11-01 13:44:22
             * createUser : admin
             * midifyTime : 2019-11-01 13:44:22
             * modifyUser : sys
             * deviceOnlineStatus : null
             * deviceTaskStatus : 1
             * offlineTime : null
             * activateTime : null
             * guaranteeBaseLocation : null
             * deviceMaintainStatus : 1
             * seriesCode : Panda_Cleaning_Robot
             * seriesName : 熊猫扫路王机器人
             * modelCode : 2019_10_31_model
             * modelName : 2019_10_31_model
             * versionNum : v_x_10_31
             * deviceOnlineStatusName : null
             * deviceTaskStatusName : null
             * supportMap : null
             * mapId : null
             * mapName : null
             * mapUrl : null
             * mapGroupId : null
             * mapGroupName : null
             * cardStatus : null
             * cardStatusName : null
             * warningType : null
             * warningTypeName : null
             * province : null
             * city : null
             * isShow : null
             * mapType : null
             * liveStatus : null
             * deviceMovedPositionDto : null
             * frameNum : null
             */

            public int id;
            public String deviceCode;
            public String deviceName;
            public Object deviceMac;
            public String deviceProductionDate;
            public int deviceMerchantId;
            public String deviceMerchantName;
            public int deviceSeriesId;
            public int deviceModelId;
            public int deviceVersionId;
            public Object deviceWorkMap;
            public Object deviceBatchNo;
            public Object deviceFloor;
            public String deviceStatus;
            public String isDelete;
            public String createTime;
            public String createUser;
            public String midifyTime;
            public String modifyUser;
            public Object deviceOnlineStatus;
            public String deviceTaskStatus;
            public Object offlineTime;
            public Object activateTime;
            public Object guaranteeBaseLocation;
            public String deviceMaintainStatus;
            public String seriesCode;
            public String seriesName;
            public String modelCode;
            public String modelName;
            public String versionNum;
            public Object deviceOnlineStatusName;
            public Object deviceTaskStatusName;
            public Object supportMap;
            public Object mapId;
            public Object mapName;
            public Object mapUrl;
            public Object mapGroupId;
            public Object mapGroupName;
            public Object cardStatus;
            public Object cardStatusName;
            public Object warningType;
            public Object warningTypeName;
            public Object province;
            public Object city;
            public Object isShow;
            public Object mapType;
            public Object liveStatus;
            public Object deviceMovedPositionDto;
            public Object frameNum;
        }
    }
}
