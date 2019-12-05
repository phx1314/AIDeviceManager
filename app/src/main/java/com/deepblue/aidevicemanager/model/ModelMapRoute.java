package com.deepblue.aidevicemanager.model;

public class ModelMapRoute {


    /**
     * msg : 操作成功
     * code : 1111
     * data : {"presetYZ":"[]","presetLY2":"[]","presetLY1":"[]"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * presetYZ : []
         * presetLY2 : []
         * presetLY1 : []
         */

        private String presetYZ;
        private String presetLY2;
        private String presetLY1;

        public String getPresetYZ() {
            return presetYZ;
        }

        public void setPresetYZ(String presetYZ) {
            this.presetYZ = presetYZ;
        }

        public String getPresetLY2() {
            return presetLY2;
        }

        public void setPresetLY2(String presetLY2) {
            this.presetLY2 = presetLY2;
        }

        public String getPresetLY1() {
            return presetLY1;
        }

        public void setPresetLY1(String presetLY1) {
            this.presetLY1 = presetLY1;
        }
    }
}
