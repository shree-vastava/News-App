package com.suki.newsapp.remote

import com.suki.newsapp.util.Resource
import retrofit2.Response

abstract class BaseDataSource {

    /**
     * Generic class for handdling the response
     * The function is a High Order Function by kotlin,
     * which accepts a function as a parameter. So the
     * parameter is executed and and then the response
     * is checked for Success or Error.
     * Based on success or error, we return our resource.
     *
     * So if we have multiple API calls, this function will
     * help with handling it in one place.
     */
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }

}