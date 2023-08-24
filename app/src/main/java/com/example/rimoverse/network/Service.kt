package com.example.rimoverse.network

import com.example.rimoverse.models.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Service {

    @GET("character/{character-id}")
    fun getCharacterById(@Path("character-id") characterIds: Int): Call<Character>

    @GET("character/{character-ids}")
    fun getCharacterListById(@Path("character-ids") characterIdNumbers: MutableList<Int>): Call<MutableList<Character>>

}
