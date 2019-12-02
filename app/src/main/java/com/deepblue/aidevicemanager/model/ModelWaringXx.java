package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelWaringXx {

    /**
     * total : 2
     * rows : [{"alarmRecordId":6628,"alarmTypeCode":"8120#3","modleName":"zy0001","alarmVideoUrl":"http://10.1.1.160:8081/robotos/file/null","alarmTypeChildName":"人员闯入","seriesName":"张阳的扫路王","versionNum":"zy_version0001","alarmTime":"2019-11-27 11:32:55","alarmTypeParentName":"人员安全报警","deviceCode":"00001044nYza","deviceId":821,"deviceName":"zy01"},{"alarmTypeCode":"8120#3","alarmTypeChildName":"人员闯入","seriesName":"张阳的扫路王","versionNum":"zy_version0001","alarmTime":"2019-11-27 11:32:55","alarmPointX":0,"alarmPointY":0,"deviceCode":"00001044nYza","alarmPointZ":0,"deviceId":821,"deviceName":"zy01","alarmFlag":"","alarmRecordId":6629,"modleName":"zy0001","alarmVideoUrl":"http://10.1.1.160:8081/robotos/file/","alarmTypeParentName":"人员安全报警","molStaffTel":""}]
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
         * alarmRecordId : 6628
         * alarmTypeCode : 8120#3
         * modleName : zy0001
         * alarmVideoUrl : http://10.1.1.160:8081/robotos/file/null
         * alarmTypeChildName : 人员闯入
         * seriesName : 张阳的扫路王
         * versionNum : zy_version0001
         * alarmTime : 2019-11-27 11:32:55
         * alarmTypeParentName : 人员安全报警
         * deviceCode : 00001044nYza
         * deviceId : 821
         * deviceName : zy01
         * alarmPointX : 0.0
         * alarmPointY : 0.0
         * alarmPointZ : 0.0
         * alarmFlag :
         * molStaffTel :
         */

        public int alarmRecordId;
        public String alarmTypeCode;
        public String modleName;
        public String alarmVideoUrl;
        public String alarmTypeChildName;
        public String seriesName;
        public String versionNum;
        public String alarmTime;
        public String alarmTypeParentName;
        public String deviceCode;
        public int deviceId;
        public String deviceName;
        public double alarmPointX;
        public double alarmPointY;
        public double alarmPointZ;
        public String alarmFlag;
        public String molStaffTel;
        public   boolean isRead;
    }
}
