package com.deepblue.aidevicemanager.model;

import java.util.List;

public class ModelMapLj {

    /**
     * total : 2
     * rows : [{"id":2071,"mapId":270,"deviceId":467,"deviceGroupId":null,"downloadStaus":"1","isDelete":"1","createTime":"2019-08-29 19:44:18","createUser":"sys","midifyTime":"2019-12-03 15:12:11","modifyUser":"admin","mapOriginalId":394,"mapName":"zy_map","merchantId":110,"merchantName":"zy","mapUrl":"http://10.1.1.160:8081/robotos/file/地图1574659053062383930.png","mapThumbnailUrl":"http://10.1.1.160:8081/robotos/file/地图1574659053062383930th.png","mapOriginalUrl":"http://10.1.1.160:8081/robotos/file/467_1567079058_hwhwjwj-2019-08-29T19:44:141567079058328498997.png","mapOriginalThumbnailUrl":"http://10.1.1.160:8081/robotos/file/467_1567079058_hwhwjwj-2019-08-29T19:44:141567079058328498997th.png","mapOriginalFtpUrl":null,"address":"江苏省常州市天宁区","location":"120.0185388274294-31.741025741346487","country":"中国","province":"江苏省","city":"常州市","county":"天宁区","mapType":null,"pointYunData":"0","mapTaskName":null},{"id":2189,"mapId":270,"deviceId":846,"deviceGroupId":null,"downloadStaus":"0","isDelete":"1","createTime":"2019-11-27 17:47:06","createUser":"sys","midifyTime":"2019-12-03 15:12:11","modifyUser":"admin","mapOriginalId":394,"mapName":"zy_map","merchantId":110,"merchantName":"zy","mapUrl":"http://10.1.1.160:8081/robotos/file/地图1574659053062383930.png","mapThumbnailUrl":"http://10.1.1.160:8081/robotos/file/地图1574659053062383930th.png","mapOriginalUrl":"http://10.1.1.160:8081/robotos/file/467_1567079058_hwhwjwj-2019-08-29T19:44:141567079058328498997.png","mapOriginalThumbnailUrl":"http://10.1.1.160:8081/robotos/file/467_1567079058_hwhwjwj-2019-08-29T19:44:141567079058328498997th.png","mapOriginalFtpUrl":null,"address":"上海市奉贤区","location":"121.75850063582341-30.942934684556025","country":"中国","province":"上海市","city":"上海市","county":"奉贤区","mapType":null,"pointYunData":"0","mapTaskName":null}]
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
         * id : 2071
         * mapId : 270
         * deviceId : 467
         * deviceGroupId : null
         * downloadStaus : 1
         * isDelete : 1
         * createTime : 2019-08-29 19:44:18
         * createUser : sys
         * midifyTime : 2019-12-03 15:12:11
         * modifyUser : admin
         * mapOriginalId : 394
         * mapName : zy_map
         * merchantId : 110
         * merchantName : zy
         * mapUrl : http://10.1.1.160:8081/robotos/file/地图1574659053062383930.png
         * mapThumbnailUrl : http://10.1.1.160:8081/robotos/file/地图1574659053062383930th.png
         * mapOriginalUrl : http://10.1.1.160:8081/robotos/file/467_1567079058_hwhwjwj-2019-08-29T19:44:141567079058328498997.png
         * mapOriginalThumbnailUrl : http://10.1.1.160:8081/robotos/file/467_1567079058_hwhwjwj-2019-08-29T19:44:141567079058328498997th.png
         * mapOriginalFtpUrl : null
         * address : 江苏省常州市天宁区
         * location : 120.0185388274294-31.741025741346487
         * country : 中国
         * province : 江苏省
         * city : 常州市
         * county : 天宁区
         * mapType : null
         * pointYunData : 0
         * mapTaskName : null
         */

        public int id;
        public int mapId;
        public int deviceId;
        public Object deviceGroupId;
        public String downloadStaus;
        public String isDelete;
        public String createTime;
        public String createUser;
        public String midifyTime;
        public String modifyUser;
        public int mapOriginalId;
        public String mapName;
        public int merchantId;
        public String merchantName;
        public String mapUrl;
        public String mapThumbnailUrl;
        public String mapOriginalUrl;
        public String mapOriginalThumbnailUrl;
        public Object mapOriginalFtpUrl;
        public String address;
        public String location;
        public String country;
        public String province;
        public String city;
        public String county;
        public Object mapType;
        public String pointYunData;
        public Object mapTaskName;
    }
}
