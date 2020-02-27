package com.hefny.hady.bellmantask.util

/**
 * A generic class that holds a value with its loading status.
 *  Based on Android Architecture Guide
 **/
data class Resource<T>(
    var error: State<String>? = null,
    var loading: Boolean = false,
    var data: T? = null
) {
    companion object {
        fun <T> error(message: String): Resource<T> {
            return Resource(error = State.errorState(message), loading = false)
        }

        fun <T> loading(isLoading: Boolean): Resource<T> {
            return Resource(
                loading = isLoading
            )
        }

        fun <T> data(data: T? = null): Resource<T> {
            return Resource(data = data, loading = false)
        }
    }
}