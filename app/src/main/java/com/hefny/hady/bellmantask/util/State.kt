package com.hefny.hady.bellmantask.util

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents a state.
 * to prevent its use once handled
 */
class State<out T>(private val content: T) {
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    companion object {
        // we don't want error state if the response is null
        fun errorState(message: String?): State<String>? {
            message?.let {
                return State(message)
            }
            return null
        }
    }
}