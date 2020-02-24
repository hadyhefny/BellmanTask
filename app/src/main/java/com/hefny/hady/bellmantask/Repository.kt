package com.hefny.hady.bellmantask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import com.hefny.hady.bellmantask.api.HomeApi
import com.hefny.hady.bellmantask.models.HomeResponse
import com.hefny.hady.bellmantask.util.*
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val homeApi: HomeApi
) {
    val TAG = "AppDebug"
    fun getHomeApi(): LiveData<DataState<HomeResponse>> {
        return switchMap(homeApi.getHomeApi()) { respone ->
            object : LiveData<DataState<HomeResponse>>() {
                override fun onActive() {
                    super.onActive()
                    value = DataState.loading(isLoading = true)
                    when (respone) {
                        is ApiSuccessResponse -> {
                            value = DataState.data(data = respone.body)
                        }
                        is ApiErrorResponse -> {
                            Log.e(TAG, "Repository: ${respone.errorMessage}")
                            value = DataState.error(message = respone.errorMessage)
                        }
                        is ApiEmptyResponse -> {
                            Log.d(TAG, "Repository: $respone")
                            value = DataState.error(message = Constants.EMPTY_RESPONSE)
                        }
                    }
                }
            }
        }
    }
}