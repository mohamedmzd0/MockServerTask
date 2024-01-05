package com.example.domain


import com.example.data.ErrorAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.transform
import retrofit2.Response

inline fun <T, R> Flow<Response<T>>.transformResponseData(
    crossinline onSuccess: suspend FlowCollector<R>.(T) -> Unit
): Flow<R> {
    return transform { response ->
        when {
            response.isSuccessful && response.body() != null ->
                onSuccess(response.body()!!)

            response.isSuccessful && response.body() == null ->
                throw Throwable(ErrorAPI.EMPTY_RESPONSE)

            response.code() == 401 -> throw Throwable(ErrorAPI.UN_AUTHRIZED)
            response.code() in 402..499 && response.errorBody() == null ->
                throw Throwable(ErrorAPI.BAD_REQUEST)

            response.code() in 500..599 -> throw Throwable(ErrorAPI.SERVER_ERROR)
            else -> throw Exception()
        }
    }
}

