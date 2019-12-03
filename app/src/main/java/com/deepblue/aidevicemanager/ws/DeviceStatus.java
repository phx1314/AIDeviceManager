package com.deepblue.aidevicemanager.ws;

import java.io.Serializable;

/**
 * 设备展示参数
 *
 * @author zhangkl
 */
public class DeviceStatus implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8134199149334603872L;

    private LiveStatus Status;

    private CleanKingLiveStatus cleanKingLiveStatus;

    private Long id;

}
