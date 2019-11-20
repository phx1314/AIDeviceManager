package com.deepblue.aidevicemanager.service

import com.mdx.framework.service.subscriber.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import retrofit2.http.Streaming


interface ApiService {
    @GET("user/logout/json")
    fun logout(): Observable<HttpResult<Any>>

}