package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelBrokenXx {


    /**
     * total : 1
     * rows : [{"seriesCode":"22","seriesName":"张阳的扫路王","versionNum":"zy_version0001","breakdownType":"2","breakdownDesc":"有人拍打机器人","deviceCode":"00001044nYza","investigationSuggestion":"建议人员选择死亡","deviceId":821,"deviceName":"zy01","breakdownStatus":"3","molStaff":"","breakdownTime":"2019-11-08 10:35:20","modleName":"zy0001","createTime":"2019-11-27 13:27:47","breakdownId":8951,"breakdownCode":"85624#5"}]
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     */

    public int total;
    public double noReadCount;
    public int pageNum;
    public int pageSize;
    public int pages;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * seriesCode : 22
         * seriesName : 张阳的扫路王
         * versionNum : zy_version0001
         * breakdownType : 2
         * breakdownDesc : 有人拍打机器人
         * deviceCode : 00001044nYza
         * investigationSuggestion : 建议人员选择死亡
         * deviceId : 821
         * deviceName : zy01
         * breakdownStatus : 3
         * molStaff :
         * breakdownTime : 2019-11-08 10:35:20
         * modleName : zy0001
         * createTime : 2019-11-27 13:27:47
         * breakdownId : 8951
         * breakdownCode : 85624#5
         */

        public String seriesCode;
        public String seriesName;
        public String versionNum;
        public String breakdownType;
        public String breakdownDesc;
        public String deviceCode;
        public String investigationSuggestion;
        public int deviceId;
        public String deviceName;
        public String breakdownStatus;
        public String molStaff;
        public String breakdownTime;
        public String modleName;
        public String createTime;
        public int breakdownId;
        public String breakdownCode;
        public   boolean isRead;
    }
}
