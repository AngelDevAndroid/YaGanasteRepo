package com.example.yaganaste.domine

    // Response api
    sealed class ApiResource<T>(
        val data: T? = null,
        val message: String? = null,
        val loading: Boolean? = null) {

        class Success<T>(data: T?) : ApiResource<T>(data)
        class Error<T>(message: String?, data: T? = null) : ApiResource<T>(data, message)
        class Loading<T>(loading: T? = null) : ApiResource<T>(loading)
    }
