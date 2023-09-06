package com.example.rimoverse.viewmodel

import android.util.Log
import com.example.rimoverse.models.CharacterList
import com.example.rimoverse.models.CharacterModel
import com.example.rimoverse.network.NetworkLayer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(){

    fun getCharactersPage(pageIndex: Int) : List<CharacterModel> {
        var charList: List<CharacterModel> = listOf()
        val request = NetworkLayer.apiClient.getPage(pageIndex)

         request.enqueue (object: Callback<CharacterList> {
            override fun onResponse(call: Call<CharacterList>, response: Response<CharacterList>) {
                if (response.isSuccessful){
                    charList = response.body()?.results.orEmpty()
                }
            }
            override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                println("Response failed")
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
        return charList
    }
}
