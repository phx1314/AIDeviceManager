package com.deepblue.aidevicemanager.ws

class WsStatus {
    companion object {
        val CONNECTED: Int = 1
        val CONNECTING: Int = 0
        val RECONNECT: Int = 2
        val DISCONNECTED: Int = -1
    }

    class CODE {
        companion object {
            val NORMAL_CLOSE = 1000
            val ABNORMAL_CLOSE = 1001
        }
    }

    class TIP {
        companion object {
            val NORMAL_CLOSE: String = "normal close"
            val ABNORMAL_CLOSE: String = "abnormal close"
        }
    }

}