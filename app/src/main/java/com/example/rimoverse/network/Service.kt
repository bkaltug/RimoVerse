package com.example.rimoverse.network

import com.example.rimoverse.models.Character
import com.example.rimoverse.models.PageModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Service {

    @GET("character/{character-id}")
    fun getCharacterById(@Path("character-id") characterId: Int): Call<Character>

    @GET("character/?page={page-index}")
    suspend fun getCharactersPage(@Path("page-index") pageIndex : Int ) : Call<PageModel>

}
