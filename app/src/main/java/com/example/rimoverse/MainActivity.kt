package com.example.rimoverse


import android.os.Bundle
import android.util.Log

import androidx.fragment.app.FragmentActivity
import com.example.rimoverse.models.Character
import com.example.rimoverse.network.Service
import com.example.rimoverse.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceGenerator = ServiceGenerator.buildService(Service :: class.java)
        val call = serviceGenerator.getCharacterById(1)

        call.enqueue(object : Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful){
                    println(response.body()?.name)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
              t.printStackTrace()
                Log.e("error",t.message.toString())
            }

        })
    }


}