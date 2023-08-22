package com.example.rimoverse.network

import com.example.rimoverse.models.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Service {

    @GET("character/{character-id}")
    fun getCharacterById(@Path("character-id") characterIds: Int): Call<Character>

    @GET("character/1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20")
    fun getCharacterListById(): Call<MutableList<Character>>

    @GET("character/character-ids")
    fun getCharacterList(@Path("character-ids") characterIds : String): Call<MutableList<Character>>

}
