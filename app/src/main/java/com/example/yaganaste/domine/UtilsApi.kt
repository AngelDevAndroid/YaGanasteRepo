package com.example.yaganaste.domine

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Retrofit
object UtilsApi {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.obtenmas.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
// https://dev.obtenmas.com/catom/api/challenge/banks