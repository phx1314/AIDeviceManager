package com.deepblue.aidevicemanager.service

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

    @POST("device/queryDeviceDetail")
    @FormUrlEncoded
    fun queryDeviceDetail(@Field("deviceId") deviceId: String): Observable<HttpResult<Any>>

    @POST("task/queryAlarmInfos")
    @FormUrlEncoded
    fun queryAlarmInfos(@Field("page") page: String, @Field("size") size: String): Observable<HttpResult<Any>>

    @POST("task/queryBreakdowns")
    @FormUrlEncoded
    fun queryBreakdowns(@Field("page") page: String, @Field("size") size: String): Observable<HttpResult<Any>>

    @POST("task/queryTaskListWithPage")
    @FormUrlEncoded
    fun queryTaskListWithPage(@Field("page") page: String, @Field("size") size: String): Observable<HttpResult<Any>>

    @POST("map/queryMapListByDevice")
    @FormUrlEncoded
    fun queryMapListByDevice(
            @Field("deviceId") deviceId: String, @Field("page") page: String, @Field(
                    "size"
            ) size: String
    ): Observable<HttpResult<Any>>

    @GET("device/queryDeviceParamList")
    fun queryDeviceParamList(@Query("deviceId") deviceId: String): Observable<HttpResult<Any>>

    @GET("device/queryDeviceLiveData")
    fun queryDeviceLiveData(@Query("deviceId") deviceId: String): Observable<HttpResult<Any>>

    @POST("device/configDeviceParamBatch")
    @FormUrlEncoded
    fun configDeviceParamBatch(@Field("deviceVersionId") deviceVersionId: String, @Field("deviceIds") deviceIds: String, @Field("deviceParamJson") deviceParamJson: String): Observable<HttpResult<Any>>

    @POST("command/createOrder")
    @FormUrlEncoded
    fun createOrder(@Field("orderType") orderType: String, @Field("deviceId") deviceId: String): Observable<HttpResult<Any>>

    @POST("task/updateAlarmIsRead")
    @FormUrlEncoded
    fun updateAlarmIsRead(@Field("id") id: String, @Field("isRead") isRead: String): Observable<HttpResult<Any>>

    @POST("task/updateBreakdownIsRead")
    @FormUrlEncoded
    fun updateBreakdownIsRead(@Field("id") id: String, @Field("isRead") isRead: String): Observable<HttpResult<Any>>

    @POST("task/updateTaskIsRead")
    @FormUrlEncoded
    fun updateTaskIsRead(@Field("id") id: String, @Field("isRead") isRead: String): Observable<HttpResult<Any>>

    @POST("user/queryContentConfigForAbout")
    fun queryContentConfigForAbout(): Observable<HttpResult<Any>>

    @POST("user/queryContentConfigForHelp")
    fun queryContentConfigForHelp(): Observable<HttpResult<Any>>

    @POST("user/queryContentConfigForPrivacyPolicy")
    fun queryContentConfigForPrivacyPolicy(): Observable<HttpResult<Any>>

    @POST("user/queryContentConfigForUserTerm")
    fun queryContentConfigForUserTerm(): Observable<HttpResult<Any>>


}