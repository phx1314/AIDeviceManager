package com.deepblue.aidevicemanager.service

import com.deepblue.aidevicemanager.F.mModellogin
import com.mdx.framework.service.subscriber.HttpResult
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService {

    @POST("user/sendSms")
    @FormUrlEncoded
    fun sendSms(@Field("mobile") mobile: String): Observable<HttpResult<Any>>

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("userName") userName: String, @Field("password") password: String, @Field("messageCode") messageCode: String): Observable<HttpResult<Any>>

    @GET("device/queryDeviceSeriesList")
    fun queryDeviceSeriesList(): Observable<HttpResult<Any>>

}