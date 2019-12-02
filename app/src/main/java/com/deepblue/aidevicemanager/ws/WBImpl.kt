package com.deepblue.aidevicemanager.ws

import okhttp3.WebSocket
import okio.ByteString

interface WBImpl {
    fun getWebsocket(): WebSocket?
    fun isWSConnected(): Boolean
    fun getCurrentState(): Int
    fun setCurrentState(currentStatus: Int)
    fun startConnect()
    fun stopConnect()
    fun sendMessage(message: String): Boolean
    fun sendMessage(byteMessage: ByteString): Boolean
}