package com.hefny.hady.bellmantask

import androidx.lifecycle.LiveData
import com.hefny.hady.bellmantask.api.HomeApi
import com.hefny.hady.bellmantask.models.HomeResponse
import com.hefny.hady.bellmantask.util.GenericApiResponse
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val homeApi: HomeApi
) {
    fun getHomeApi(): LiveData<GenericApiResponse<HomeResponse>> {
        return homeApi.getHomeApi()
    }
}