package com.hefny.hady.bellmantask.util

class ErrorHandling {
    companion object {
        const val ERROR_UNKNOWN = "unknown error"
        const val EMPTY_RESPONSE = "empty response"
        const val UNABLE_TO_RESOLVE_HOST = "Unable to resolve host"
        const val ERROR_CHECK_NETWORK_CONNECTION = "Check network connection."

        fun isNetworkError(message: String): Boolean {
            return message.contains(UNABLE_TO_RESOLVE_HOST)
        }
    }
}