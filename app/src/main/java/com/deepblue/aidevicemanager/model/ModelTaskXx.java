package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelTaskXx {


    /**
     * total : 1.0
     * rows : [{"id":1173,"taskName":"201911251722清扫","taskType":"1","taskLoop":"0","taskTiming":"","taskTimingTime":"2019-11-25 17:23:40","taskPriority":"4","taskLoopStartTime":"","taskLoopEndTime":"","taskLoopStartDate":"2019-11-27 00:00:00","taskLoopEndDate":"2019-11-27 00:00:00","taskAllDay":"","taskFinishType":"","taskLoopType":"","taskLoopDay":"","taskTimelyExecutionType":"1","taskTimelyExecutionTime":"2019-11-25 17:23:40","taskCronStart":"","taskCronEnd":"","taskMapGroupId":118,"taskMapId":2162,"taskOriginalMapId":391,"taskMapAreaId":2,"taskStatus":"1","isDelete":"1","createTime":"2019-11-25 17:23:40","createUser":"15510255253","midifyTime":"2019-11-25 17:23:40","modifyUser":"sys","taskPointAreaSeq":"[{\"id\":870,\"type\":\"2\"}]","workScope":"","taskTypeName":"清扫","taskLoopName":"一次性","priorityName":"紧急","taskStatusName":"未开始","deviceNames":"zy01","deviceNum":1,"deviceStatus":"1","seriesCode":"22"}]
     * pageNum : 1.0
     * pageSize : 1.0
     * pages : 1.0
     */

    public double total;
    public double noReadCount;
    public double pageNum;
    public double pageSize;
    public double pages;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * id : 1173.0
         * taskName : 201911251722清扫
         * taskType : 1
         * taskLoop : 0
         * taskTiming :
         * taskTimingTime : 2019-11-25 17:23:40
         * taskPriority : 4
         * taskLoopStartTime :
         * taskLoopEndTime :
         * taskLoopStartDate : 2019-11-27 00:00:00
         * taskLoopEndDate : 2019-11-27 00:00:00
         * taskAllDay :
         * taskFinishType :
         * taskLoopType :
         * taskLoopDay :
         * taskTimelyExecutionType : 1
         * taskTimelyExecutionTime : 2019-11-25 17:23:40
         * taskCronStart :
         * taskCronEnd :
         * taskMapGroupId : 118.0
         * taskMapId : 2162.0
         * taskOriginalMapId : 391.0
         * taskMapAreaId : 2.0
         * taskStatus : 1
         * isDelete : 1
         * createTime : 2019-11-25 17:23:40
         * createUser : 15510255253
         * midifyTime : 2019-11-25 17:23:40
         * modifyUser : sys
         * taskPointAreaSeq : [{"id":870,"type":"2"}]
         * workScope :
         * taskTypeName : 清扫
         * taskLoopName : 一次性
         * priorityName : 紧急
         * taskStatusName : 未开始
         * deviceNames : zy01
         * deviceNum : 1.0
         * deviceStatus : 1
         * seriesCode : 22
         */

        public double id;
        public String taskName;
        public String taskType;
        public String taskLoop;
        public String taskTiming;
        public String taskTimingTime;
        public String taskPriority;
        public String taskLoopStartTime;
        public String taskLoopEndTime;
        public String taskLoopStartDate;
        public String taskLoopEndDate;
        public String taskAllDay;
        public String taskFinishType;
        public String taskLoopType;
        public String taskLoopDay;
        public String taskTimelyExecutionType;
        public String taskTimelyExecutionTime;
        public String taskCronStart;
        public String taskCronEnd;
        public double taskMapGroupId;
        public double taskMapId;
        public double taskOriginalMapId;
        public double taskMapAreaId;
        public String taskStatus;
        public String isDelete;
        public String createTime;
        public String createUser;
        public String midifyTime;
        public String modifyUser;
        public String taskPointAreaSeq;
        public String workScope;
        public String taskTypeName;
        public String taskLoopName;
        public String priorityName;
        public String taskStatusName;
        public String deviceNames;
        public double deviceNum;
        public String deviceStatus;
        public String seriesCode;
        public boolean isRead;
    }
}
