package com.deepblue.aidevicemanager.ws

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.mdx.framework.Frame
import okhttp3.*
import okio.ByteString
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class WsManager constructor(builder: Builder) : WBImpl {
    private var mWebSocket: WebSocket? = null
    private val mContext: Context? = builder.getContext()
    var wsUrl: String = builder.getWsurl()
    private var mOkHttpClient: OkHttpClient = builder.getOkHttpClient()
    private var mRequest: Request? = null
    private var mCurrentStatus = WsStatus.DISCONNECTED              //当前ws连接状态
    private val isNeedReconnect: Boolean = builder.getNeedReconnected() //是否需要断线自动重连
    private var isManualClose = false                                   //是否为手动关闭ws
    private val mLock: Lock = ReentrantLock()
    private val wsMainHandler = Handler(Looper.getMainLooper())
    private var reconnectCount = 0   //重连次数
    private val reconnectRunnable = Runnable {
        Log.e("websocket", "服务器重连接中...")
        buildConnect()
    }

    override fun getWebsocket(): WebSocket? {
        return mWebSocket
    }

    @Synchronized
    override fun isWSConnected(): Boolean {
        return mCurrentStatus == WsStatus.CONNECTED
    }

    @Synchronized
    override fun getCurrentState(): Int {
        return mCurrentStatus
    }

    @Synchronized
    override fun setCurrentState(currentStatus: Int) {
        Frame.HANDLES.sentAll(1112, currentStatus)
        mCurrentStatus = currentStatus
    }

    override fun startConnect() {
        isManualClose = false
        buildConnect()
    }

    override fun stopConnect() {
        isManualClose = true
        try {
            disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun sendMessage(message: String): Boolean {
        return send(message)
    }

    override fun sendMessage(byteMessage: ByteString): Boolean {
        return send(byteMessage)
    }

    private val mWebSocketListener = object : WebSocketListener() {

        override fun onOpen(webSocket: WebSocket, response: Response) {
            mWebSocket = webSocket
            setCurrentState(WsStatus.CONNECTED)
            connected()
            Log.e("websocket", "服务器连接成功")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Frame.HANDLES.sentAll(1111, text)
            Log.e("websocket", "websocketString-----onMessage----$text")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.e("websocket", "服务器连接关闭中")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            Log.e("websocket", "服务器连接已关闭")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//            try {
            tryReconnect()
//                Log.e("websocket retry", "[走的链接失败这里！！！！！！！！！！！！！！！！]")
//                if (Looper.myLooper() != Looper.getMainLooper()) {
//                    wsMainHandler.post { Log.e("websocket", "服务器连接失败") }
//                } else {
            Log.e("websocket", "服务器连接失败")
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }

        }
    }

    private fun send(msg: Any): Boolean {
        var isSend = false
        if (mWebSocket != null && mCurrentStatus == WsStatus.CONNECTED) {
            if (msg is String) {
                isSend = mWebSocket!!.send(msg)
            } else if (msg is ByteString) {
                isSend = mWebSocket!!.send(msg)
            }
            //发送消息失败，尝试重连
            if (!isSend) {
                tryReconnect()
            }
        }
        return isSend
    }

    private fun tryReconnect() {
        if (!isNeedReconnect or isManualClose) {
            return
        }
        Log.e("websocket retry", "reconnectCount2222222[$reconnectCount]")
        if (!isNetworkConnected(mContext)) {
            setCurrentState(WsStatus.DISCONNECTED)
            Log.e("websocket retry", "[请您检查网络，未连接]")
        }
        setCurrentState(WsStatus.RECONNECT)
        Log.e("websocket retry", "reconnectCount11111111[$reconnectCount]")
        wsMainHandler.postDelayed(reconnectRunnable, 10000)
        Log.e("websocket retry", "reconnectCount[$reconnectCount]")
        reconnectCount++
    }

    private fun connected() {
        wsMainHandler.removeCallbacks(reconnectRunnable)
        reconnectCount = 0
    }

    private fun disconnect() {
        if (mCurrentStatus == WsStatus.DISCONNECTED) {
            return
        }
        connected()
        if (mOkHttpClient != null) {
            mOkHttpClient.dispatcher.cancelAll()
        }
        if (mWebSocket != null) {
            val isClosed = mWebSocket!!.close(WsStatus.CODE.NORMAL_CLOSE, WsStatus.TIP.NORMAL_CLOSE)
            //非正常关闭连接
            if (!isClosed) {
                Log.e("websocket", "服务器连接失败")
            }
        }
        setCurrentState(WsStatus.DISCONNECTED)
    }

    @Synchronized
    private fun buildConnect() {
        if (!isNetworkConnected(mContext)) {
            setCurrentState(WsStatus.DISCONNECTED)
        }
        when (getCurrentState()) {
            WsStatus.CONNECTED -> {
            }
            WsStatus.CONNECTING -> {
            }
            else -> {
                setCurrentState(WsStatus.CONNECTING)
                initWebSocket()
            }
        }
    }

    private fun initWebSocket() {
        if (mOkHttpClient == null) {
            mOkHttpClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build()
        }
        mRequest = Request.Builder()
            .url(wsUrl)
            .build()
        mOkHttpClient.dispatcher.cancelAll()
        try {
            mLock.lockInterruptibly()
            try {
                mOkHttpClient.newWebSocket(mRequest!!, mWebSocketListener)
            } finally {
                mLock.unlock()
            }
        } catch (e: InterruptedException) {
        }

    }

    class Builder {
        private var context: Context? = null
        private lateinit var wsUrl: String
        private var needReconnect = true
        private lateinit var mOkHttpClient: OkHttpClient

        fun getContext(): Context? {
            return context
        }

        fun getWsurl(): String {
            return wsUrl
        }

        fun getNeedReconnected(): Boolean {
            return needReconnect
        }

        fun getOkHttpClient(): OkHttpClient {
            return mOkHttpClient
        }

        fun context(context_: Context): Builder {
            context = context_
            return this
        }

        fun wsUrl(wsUrl_: String): Builder {
            wsUrl = wsUrl_
            return this
        }

        fun client(mOkHttpClient_: OkHttpClient): Builder {
            mOkHttpClient = mOkHttpClient_
            return this
        }

        fun needReconnect(needReconnect_: Boolean): Builder {
            needReconnect = needReconnect_
            return this
        }

        fun build(): WsManager {
            return WsManager(this)
        }

    }

    private fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            @SuppressLint("MissingPermission") val mNetworkInfo =
                mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }
}