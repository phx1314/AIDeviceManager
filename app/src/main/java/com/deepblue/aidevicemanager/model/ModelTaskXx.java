package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelTaskXx {


    /**
     * pageInfo : {"total":4,"rows":[{"id":1189,"taskName":"201912021738巡逻","taskType":"3","taskLoop":"0","taskTimingTime":"2019-12-02 17:39:07","taskPriority":"4","taskTimelyExecutionType":"1","taskTimelyExecutionTime":"2019-12-02 17:39:07","taskMapGroupId":124,"taskMapId":2195,"taskOriginalMapId":450,"taskMapAreaId":1,"taskStatus":"1","isDelete":"1","isRead":"1","createTime":"2019-12-02 17:39:07","createUser":"Tina","midifyTime":"2019-12-02 17:39:07","modifyUser":"sys","taskPointAreaSeq":"[{\"id\":882,\"type\":\"1\"}]","taskTypeName":"巡逻","taskLoopName":"一次性","priorityName":"紧急","taskStatusName":"未开始","deviceNames":"zy01,10","deviceNum":2,"deviceStatus":"1","seriesCode":"22"}],"pageNum":1,"pageSize":1,"pages":4}
     * noReadCount : 3
     */

    public PageInfoBean pageInfo;
    public String noReadCount;

    public static class PageInfoBean {
        /**
         * total : 4.0
         * rows : [{"id":1189,"taskName":"201912021738巡逻","taskType":"3","taskLoop":"0","taskTimingTime":"2019-12-02 17:39:07","taskPriority":"4","taskTimelyExecutionType":"1","taskTimelyExecutionTime":"2019-12-02 17:39:07","taskMapGroupId":124,"taskMapId":2195,"taskOriginalMapId":450,"taskMapAreaId":1,"taskStatus":"1","isDelete":"1","isRead":"1","createTime":"2019-12-02 17:39:07","createUser":"Tina","midifyTime":"2019-12-02 17:39:07","modifyUser":"sys","taskPointAreaSeq":"[{\"id\":882,\"type\":\"1\"}]","taskTypeName":"巡逻","taskLoopName":"一次性","priorityName":"紧急","taskStatusName":"未开始","deviceNames":"zy01,10","deviceNum":2,"deviceStatus":"1","seriesCode":"22"}]
         * pageNum : 1.0
         * pageSize : 1.0
         * pages : 4.0
         */

        public double total;
        public double pageNum;
        public double pageSize;
        public double pages;
        public List<RowsBean> rows;

        public static class RowsBean {
            /**
             * id : 1189.0
             * taskName : 201912021738巡逻
             * taskType : 3
             * taskLoop : 0
             * taskTimingTime : 2019-12-02 17:39:07
             * taskPriority : 4
             * taskTimelyExecutionType : 1
             * taskTimelyExecutionTime : 2019-12-02 17:39:07
             * taskMapGroupId : 124.0
             * taskMapId : 2195.0
             * taskOriginalMapId : 450.0
             * taskMapAreaId : 1.0
             * taskStatus : 1
             * isDelete : 1
             * isRead : 1
             * createTime : 2019-12-02 17:39:07
             * createUser : Tina
             * midifyTime : 2019-12-02 17:39:07
             * modifyUser : sys
             * taskPointAreaSeq : [{"id":882,"type":"1"}]
             * taskTypeName : 巡逻
             * taskLoopName : 一次性
             * priorityName : 紧急
             * taskStatusName : 未开始
             * deviceNames : zy01,10
             * deviceNum : 2.0
             * deviceStatus : 1
             * seriesCode : 22
             */

            public double id;
            public String taskName;
            public String taskType;
            public String taskLoop;
            public String taskTimingTime;
            public String taskPriority;
            public String taskTimelyExecutionType;
            public String taskTimelyExecutionTime;
            public double taskMapGroupId;
            public double taskMapId;
            public double taskOriginalMapId;
            public double taskMapAreaId;
            public String taskStatus;
            public String isDelete;
            public String isRead;
            public String createTime;
            public String createUser;
            public String midifyTime;
            public String modifyUser;
            public String taskPointAreaSeq;
            public String taskTypeName;
            public String taskLoopName;
            public String priorityName;
            public String taskStatusName;
            public String deviceNames;
            public double deviceNum;
            public String deviceStatus;
            public String seriesCode;
        }
    }
}
