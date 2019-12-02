package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelMapLj {
    /**
     * total : 1
     * rows : [{"id":574,"areaPathName":"cccddd","mapId":1596,"originalMapId":189,"type":"2","wotkType":"1","calcType":"2","calcValue":"67","calcUnit":"米","pointPathInfo":"1961,1962,1963","pointPathCount":2,"robotShow":null,"remark":null,"isDelete":"1","createTime":"2019-12-02 17:31:06","createUser":"sys","midifyTime":"2019-12-02 17:31:37","modifyUser":"sys","graphType":"1","pointPathSerial":null,"isReal":"1","pathPicUrl":null}]
     * pageNum : 1
     * pageSize : 6
     * pages : 1
     */

    public int total;
    public int pageNum;
    public int pageSize;
    public int pages;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * id : 574
         * areaPathName : cccddd
         * mapId : 1596
         * originalMapId : 189
         * type : 2
         * wotkType : 1
         * calcType : 2
         * calcValue : 67
         * calcUnit : 米
         * pointPathInfo : 1961,1962,1963
         * pointPathCount : 2
         * robotShow : null
         * remark : null
         * isDelete : 1
         * createTime : 2019-12-02 17:31:06
         * createUser : sys
         * midifyTime : 2019-12-02 17:31:37
         * modifyUser : sys
         * graphType : 1
         * pointPathSerial : null
         * isReal : 1
         * pathPicUrl : null
         */

        public int id;
        public String areaPathName;
        public int mapId;
        public int originalMapId;
        public String type;
        public String wotkType;
        public String calcType;
        public String calcValue;
        public String calcUnit;
        public String pointPathInfo;
        public int pointPathCount;
        public Object robotShow;
        public Object remark;
        public String isDelete;
        public String createTime;
        public String createUser;
        public String midifyTime;
        public String modifyUser;
        public String graphType;
        public Object pointPathSerial;
        public String isReal;
        public String pathPicUrl;
        public boolean isChecked;
    }
}
