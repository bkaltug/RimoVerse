package com.example.rimoverse.network

import com.example.rimoverse.models.Character
import com.example.rimoverse.models.CharacterList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Service {

    @GET("character/{character-id}")
    fun getCharacterById(@Path("character-id") characterIds: Int): Call<Character>

    @GET("character")
    fun getCharacterList(): Call<CharacterList>

    @GET("character")
    fun getPage(@Query("page") pageIndex : Int ) : Call<CharacterList>

}
