package com.hefny.hady.bellmantask.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hefny.hady.bellmantask.util.*

/**
 * A generic class that is responsible for handling network calls
 *  Based on Android Architecture Guide
 **/
abstract class NetworkBoundResource<ResponseObject>(
    isNetworkAvailable: Boolean
) {
    private val TAG = "AppDebug"
    protected val result = MediatorLiveData<Resource<ResponseObject>>()

    init {
        setValue(Resource.loading(true))
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

    fun setValue(resource: Resource<ResponseObject>) {
        result.value = resource
    }

    private fun onErrorReturn(errorMessage: String?) {
        var msg = errorMessage

        if (msg == null) {
            msg = ErrorHandling.ERROR_UNKNOWN
        } else if (ErrorHandling.isNetworkError(msg)) {
            msg = ErrorHandling.ERROR_CHECK_NETWORK_CONNECTION
        }
        setValue(Resource.error(msg))
    }

    fun asLiveData() = result as LiveData<Resource<ResponseObject>>
    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)
    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>
}