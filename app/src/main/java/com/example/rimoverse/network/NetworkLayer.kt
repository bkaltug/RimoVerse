package com.example.rimoverse.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkLayer {

    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").addConverterFactory(
        GsonConverterFactory.create()).client(
        client).build()

    val service: Service by lazy {
        retrofit.create(Service ::class.java)
    }

    val apiClient = ApiClient(service)
}