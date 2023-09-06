package com.example.rimoverse.network

import com.example.rimoverse.models.CharacterList
import com.example.rimoverse.models.CharacterModel
import retrofit2.Call
import java.lang.Exception

class ApiClient (private val service: Service) {

    fun getCharacterById(characterId: Int): Call<CharacterModel> {
        return safeApiCall { service.getCharacterById(characterId) }
    }

    fun getPage(pageIndex: Int) : Call<CharacterList>{
        return safeApiCall { service.getPage(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Call<T>) : Call<T> {
        return try {
            apiCall.invoke()
        } catch (e: Exception){
            throw e
        }
    }
}