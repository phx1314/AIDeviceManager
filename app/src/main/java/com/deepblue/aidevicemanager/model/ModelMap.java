package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelMap {


    /**
     * total : 456.0
     * rows : [{"id":1653,"mapId":60,"deviceId":252,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:38:59","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapName":"zhaopptestmap0517","merchantId":1,"merchantName":"深蓝科技"},{"id":1654,"mapId":60,"deviceId":141,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:38:59","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapName":"zhaopptestmap0517","merchantId":1,"merchantName":"深蓝科技"},{"id":1655,"mapId":60,"deviceId":217,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:38:59","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapName":"zhaopptestmap0517","merchantId":1,"merchantName":"深蓝科技"},{"id":1656,"mapId":60,"deviceId":218,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:38:59","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapName":"zhaopptestmap0517","merchantId":1,"merchantName":"深蓝科技"},{"id":1657,"mapId":60,"deviceId":219,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:38:59","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapName":"zhaopptestmap0517","merchantId":1,"merchantName":"深蓝科技"},{"id":1676,"mapId":47,"deviceId":246,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:43:11","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapOriginalId":143,"mapName":"444444","merchantId":1,"merchantName":"深蓝科技"},{"id":1677,"mapId":48,"deviceId":246,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:43:11","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapOriginalId":143,"mapName":"222222222222222222222222222222","merchantId":1,"merchantName":"深蓝科技"},{"id":1678,"mapId":49,"deviceId":246,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:43:11","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapOriginalId":143,"mapName":"常州工厂地图0430","merchantId":1,"merchantName":"深蓝科技"},{"id":1680,"mapId":51,"deviceId":246,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-27 14:43:11","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapOriginalId":143,"merchantId":1,"merchantName":"深蓝科技"},{"id":1685,"mapId":50,"deviceId":246,"downloadStaus":"0","isDelete":"1","createTime":"2019-05-28 16:29:25","createUser":"sys","midifyTime":"2019-06-15 18:32:17","modifyUser":"sys","mapName":"常州工厂地图0430--","merchantId":1,"merchantName":"深蓝科技"}]
     * pageNum : 1.0
     * pageSize : 10.0
     * pages : 46.0
     */

    public double total;
    public double pageNum;
    public double pageSize;
    public double pages;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * id : 1653.0
         * mapId : 60.0
         * deviceId : 252.0
         * downloadStaus : 0
         * isDelete : 1
         * createTime : 2019-05-27 14:38:59
         * createUser : sys
         * midifyTime : 2019-06-15 18:32:17
         * modifyUser : sys
         * mapName : zhaopptestmap0517
         * merchantId : 1.0
         * merchantName : 深蓝科技
         * mapOriginalId : 143.0
         */

        public double id;
        public double mapId;
        public double deviceId;
        public String downloadStaus;
        public String isDelete;
        public String createTime;
        public String createUser;
        public String midifyTime;
        public String modifyUser;
        public String mapName;
        public double merchantId;
        public String merchantName;
        public double mapOriginalId;
    }
}
