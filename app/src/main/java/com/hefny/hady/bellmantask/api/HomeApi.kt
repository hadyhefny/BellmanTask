package com.hefny.hady.bellmantask.api

import androidx.lifecycle.LiveData
import com.hefny.hady.bellmantask.models.HomeResponse
import com.hefny.hady.bellmantask.util.GenericApiResponse
import retrofit2.http.GET

interface HomeApi {

    @GET("home")
    fun getHomeApi(): LiveData<GenericApiResponse<HomeResponse>>
}