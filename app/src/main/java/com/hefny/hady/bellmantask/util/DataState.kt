package com.hefny.hady.bellmantask.util

data class DataState<T>(
    var error: Event<String>? = null,
    var loading: Boolean = false,
    var data: Event<T>? = null
) {
    companion object {
        fun <T> error(message: String): DataState<T> {
            return DataState(error = Event.responseEvent(message),loading = false)
        }

        fun <T> loading(isLoading: Boolean): DataState<T> {
            return DataState(
                loading = isLoading
            )
        }

        fun <T> data(data: T? = null): DataState<T> {
            return DataState(data = Event.dataEvent(data),loading = false)
        }
    }
}