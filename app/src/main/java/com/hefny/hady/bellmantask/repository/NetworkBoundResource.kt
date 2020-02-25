package com.hefny.hady.bellmantask.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hefny.hady.bellmantask.util.*

abstract class NetworkBoundResource<ResponseObject>(
    isNetworkAvailable: Boolean
) {
    private val TAG = "AppDebug"
    protected val result = MediatorLiveData<DataState<ResponseObject>>()

    init {
        setValue(DataState.loading(true))
        if (isNetworkAvailable) {
            val apiResponse = createCall()
            result.addSource(apiResponse) { response ->
                result.removeSource(apiResponse)
                handleNetworkApiCall(response)
            }
        } else {
            onErrorReturn(ErrorHandling.ERROR_CHECK_NETWORK_CONNECTION)
        }
    }

    private fun handleNetworkApiCall(response: GenericApiResponse<ResponseObject>) {
        when (response) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response = response)
            }
            is ApiErrorResponse -> {
                Log.e(TAG, "NetworkBoundResource: ${response.errorMessage}")
                onErrorReturn(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                Log.e(TAG, "NetworkBoundResource: Request Returned nothing (http 204)")
                onErrorReturn(ErrorHandling.EMPTY_RESPONSE)
            }
        }
    }

    fun setValue(dataState: DataState<ResponseObject>) {
        result.value = dataState
    }

    private fun onErrorReturn(errorMessage: String?) {
        var msg = errorMessage

        if (msg == null) {
            msg = ErrorHandling.ERROR_UNKNOWN
        } else if (ErrorHandling.isNetworkError(msg)) {
            msg = ErrorHandling.ERROR_CHECK_NETWORK_CONNECTION
        }
        setValue(DataState.error(msg))
    }

    fun asLiveData() = result as LiveData<DataState<ResponseObject>>
    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)
    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>
}