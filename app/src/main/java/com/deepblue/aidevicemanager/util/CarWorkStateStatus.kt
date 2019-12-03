package com.deepblue.aidevicemanager.util

/**
 * 工作状态 0：初始，1：运行，2：暂停 3急停  4故障
 */
class CarWorkStateStatus {
    companion object {
        val WORK_DEFAUT: Int = -1
        val WORK_WAITSTART: Int = 0
        val WORKING: Int = 1
        val WORK_STOP: Int = 2
        val WORK_SHUTSTOP: Int = 3
        val WORK_ERROR: Int = 4
    }
}