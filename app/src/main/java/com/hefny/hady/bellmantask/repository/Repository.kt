package com.hefny.hady.bellmantask.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.hefny.hady.bellmantask.api.HomeApi
import com.hefny.hady.bellmantask.models.HomeResponse
import com.hefny.hady.bellmantask.util.ApiSuccessResponse
import com.hefny.hady.bellmantask.util.ConnectivityUtil
import com.hefny.hady.bellmantask.util.DataState
import com.hefny.hady.bellmantask.util.GenericApiResponse
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val homeApi: HomeApi,
    private val connectivityUtil: ConnectivityUtil
) {
    val TAG = "AppDebug"
    fun getHomeApi(): LiveData<DataState<HomeResponse>> {
        return object : NetworkBoundResource<HomeResponse>(
            connectivityUtil.isConnectedToTheInternet()
        ) {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<HomeResponse>) {
                Log.d(TAG, "handleApiSuccessResponse: ${response.body}")
                result.value = DataState.data(response.body)
            }

            override fun createCall(): LiveData<GenericApiResponse<HomeResponse>> {
                return homeApi.getHomeApi()
            }
        }.asLiveData()
    }
}