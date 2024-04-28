package com.example.yaganaste.repository

import com.example.yaganaste.data.BancsModel
import com.example.yaganaste.domine.ApiResource
import com.example.yaganaste.domine.CallApiRest
import com.example.yaganaste.domine.UtilsApi.getRetrofit
import javax.inject.Inject

class BancsRepository @Inject constructor(private val service: CallApiRest)  {

    // Object
    var listPokes: List<BancsModel>? = null
    var getMessage: String? = null
    var setMessageIsNull: String = "Error de Api"

    suspend fun getPokemonListRepo(): ApiResource<List<BancsModel>> {

        try {

            val response = service.getBancs()
            if (response.isSuccessful && response.body() != null) {

                val getList = response.body()!!

                if (getList.isNotEmpty()) {
                    listPokes = getList
                }else {
                    listPokes = emptyList()
                }
                println("RESPONSE_API ${listPokes.toString()}")
            }else{
                return ApiResource.Error("Error de Api, ${response.message()}")
            }

        }catch (error: Exception) {
            getMessage = error.message.toString()
            println("RESPONSE_API ${getMessage?: setMessageIsNull}")
            return ApiResource.Error("Error de Api, ${getMessage?: setMessageIsNull}")
        }
        return ApiResource.Success(listPokes?: emptyList())
    }
}