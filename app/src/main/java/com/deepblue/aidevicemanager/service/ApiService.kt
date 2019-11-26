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
    fun login(@Field("mobile") mobile: String, @Field("password") password: String, @Field("messageCode") messageCode: String): Observable<HttpResult<Any>>

    @GET("device/queryDeviceSeriesList")
    fun queryDeviceSeriesList(): Observable<HttpResult<Any>>

    @POST("device/queryAllModelBySeries")
    @FormUrlEncoded
    fun queryAllModelBySeries(@Field("deviceSeriesId") deviceSeriesId: Int): Observable<HttpResult<Any>>

    @POST("user/forgetPassword")
    @FormUrlEncoded
    fun forgetPassword(
        @Field("newPassword") newPassword: String, @Field("mobile") mobile: String, @Field(
            "smsCode"
        ) smsCode: String
    ): Observable<HttpResult<Any>>

    @POST("user/modifyUser")
    @FormUrlEncoded
    fun modifyUser(@Field("name") name: String, @Field("mobile") mobile: String, @Field("smsCode") smsCode: String): Observable<HttpResult<Any>>

    @POST("user/modifyPassword")
    @FormUrlEncoded
    fun modifyPassword(@Field("oldPassword") oldPassword: String, @Field("newPassword") newPassword: String): Observable<HttpResult<Any>>

//    @POST("task/queryAlarmInfos")
//    @FormUrlEncoded
//    fun queryAlarmInfos(): Observable<HttpResult<Any>>
//
//    @POST("task/queryBreakdowns")
//    @FormUrlEncoded
//    fun queryBreakdowns(): Observable<HttpResult<Any>>

}