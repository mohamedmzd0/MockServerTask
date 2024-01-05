package com.example.data

sealed class NetWorkState {
    data class Success<out T>(val data: T) : NetWorkState()
    data class Error(val th: Throwable) : NetWorkState()
    object Loading : NetWorkState()
    object DismissLoading : NetWorkState()

}

class ErrorAPI {
    companion object {
        const val BAD_REQUEST = "bad-request"
        const val EMPTY_RESPONSE = "empty-response"
        const val UN_AUTHRIZED = "un-authrized"
        const val SERVER_ERROR = "server error"
    }

}