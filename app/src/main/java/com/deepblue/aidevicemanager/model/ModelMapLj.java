package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelMapLj {


    /**
     * msg : 操作成功
     * code : 1111
     * data : {"total":0,"rows":[],"pageNum":1,"pageSize":10,"pages":0}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * total : 0
         * rows : []
         * pageNum : 1
         * pageSize : 10
         * pages : 0
         */

        public int total;
        public int pageNum;
        public int pageSize;
        public int pages;
        public List<?> rows;
    }
}
